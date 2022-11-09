package com.gestaoservicos.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestaoservicos.domain.Funcionario;
import com.gestaoservicos.domain.Pessoa;
import com.gestaoservicos.domain.dtos.FuncionarioDTO;
import com.gestaoservicos.repositories.FuncionarioRepository;
import com.gestaoservicos.repositories.PessoaRepository;
import com.gestaoservicos.services.exceptions.DataIntegrityViolationException;
import com.gestaoservicos.services.exceptions.ObjectnotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder; 

	public Funcionario findById(Integer id) {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objecto não encontrado! ID: " + id));
	}

	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	public Funcionario create(FuncionarioDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Funcionario newObj = new Funcionario(objDTO);
		return repository.save(newObj);
	}

	public Funcionario update(Integer id, @Valid FuncionarioDTO objDTO) {
		objDTO.setId(id);
		Funcionario oldObj = findById(id);
		
		if(!objDTO.getSenha().equals(oldObj.getSenha())) {
			objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		}
		
		validaPorCpfEEmail(objDTO);
		oldObj = new Funcionario(objDTO);
		return repository.save(oldObj);

	}

	public void delete(Integer id) {
		Funcionario obj = findById(id);
		if (obj.getOrdemServico().size() > 0) {
			throw new DataIntegrityViolationException("Funcionario possui ordens de serviço e não pode ser deletado!");
		} else {
			repository.deleteById(id);
		}

	}

	private void validaPorCpfEEmail(FuncionarioDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF ja cadastrado no sistema");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail ja cadastrado no sistema");
		}

	}

}
