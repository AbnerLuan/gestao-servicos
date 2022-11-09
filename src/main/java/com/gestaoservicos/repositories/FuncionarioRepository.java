package com.gestaoservicos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaoservicos.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

}
