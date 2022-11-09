package com.gestaoservicos.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestaoservicos.domain.dtos.FuncionarioDTO;
import com.gestaoservicos.domain.enums.Perfil;

@Entity
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "funcionario")
	private List<OrdemServico> ordemServico = new ArrayList<>();

	public Funcionario() {
		super();
		addPerfil(Perfil.FUNCIONARIO);

	}

	public Funcionario(Integer id, String nome, String cpf, String email, String senha, String telefone,
			String veiculo) {
		super(id, nome, cpf, email, senha, telefone, veiculo);
		addPerfil(Perfil.FUNCIONARIO);
	}

	public Funcionario(FuncionarioDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.telefone = obj.getTelefone();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
	}

	public List<OrdemServico> getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(List<OrdemServico> ordemServico) {
		this.ordemServico = ordemServico;
	}

}
