package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByEmail(String email);

}
