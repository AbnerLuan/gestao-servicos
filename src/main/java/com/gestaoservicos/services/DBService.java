package com.gestaoservicos.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestaoservicos.domain.Cliente;
import com.gestaoservicos.domain.Funcionario;
import com.gestaoservicos.domain.OrdemServico;
import com.gestaoservicos.domain.enums.Perfil;
import com.gestaoservicos.domain.enums.Prioridade;
import com.gestaoservicos.domain.enums.Status;
import com.gestaoservicos.repositories.OrdemServicoRepository;
import com.gestaoservicos.repositories.PessoaRepository;

@Service
public class DBService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() {

		Funcionario tec1 = new Funcionario(null, "Voce Mais Tech", "550.482.150-95", "vocemaistech@mail.com",
				encoder.encode("1234"), "11999999999", "");
		tec1.addPerfil(Perfil.ADMIN);
		Funcionario tec2 = new Funcionario(null, "Abner Luan", "903.347.070-56", "abner@mail.com",
				encoder.encode("1234"), "11975070230", "");
		tec2.addPerfil(Perfil.ADMIN);
		Funcionario tec3 = new Funcionario(null, "Andrey Sepini", "271.068.470-54", "andrey@mail.com",
				encoder.encode("1234"), "11975070231", "");
		tec3.addPerfil(Perfil.ADMIN);
		Funcionario tec4 = new Funcionario(null, "Rogerinho Almeida", "162.720.120-39", "rogerinho@mail.com",
				encoder.encode("1234"), "11975070232", "");
		Funcionario tec5 = new Funcionario(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com",
				encoder.encode("1234"), "11975070233", "");

		Cliente cli1 = new Cliente(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com",
				encoder.encode("1234"), "11975070234", "onix");
		Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("1234"),
				"11975070235", "palio");
		Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("1234"),
				"11975070236", "gol");
		Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("1234"),
				"11975070237", "KA");
		Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("1234"),
				"11975070238", "punto");

		OrdemServico c1 = new OrdemServico(null, Prioridade.MEDIA, Status.ANDAMENTO, "OS 1", "Teste os 1", tec1, cli1);
		OrdemServico c2 = new OrdemServico(null, Prioridade.ALTA, Status.ABERTO, "OS 2", "Teste os 2", tec1, cli2);
		OrdemServico c3 = new OrdemServico(null, Prioridade.BAIXA, Status.ENCERRADO, "OS 3", "Teste os 3", tec2, cli3);
		OrdemServico c4 = new OrdemServico(null, Prioridade.ALTA, Status.ABERTO, "OS 4", "Teste os 4", tec3, cli3);
		OrdemServico c5 = new OrdemServico(null, Prioridade.MEDIA, Status.ANDAMENTO, "OS 5", "Teste os 5", tec2, cli1);
		OrdemServico c6 = new OrdemServico(null, Prioridade.BAIXA, Status.ENCERRADO, "OS 7", "Teste os 6", tec1, cli5);

		pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
		ordemServicoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

	}

}
