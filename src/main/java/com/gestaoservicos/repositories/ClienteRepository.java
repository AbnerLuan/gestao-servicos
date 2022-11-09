package com.gestaoservicos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaoservicos.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
