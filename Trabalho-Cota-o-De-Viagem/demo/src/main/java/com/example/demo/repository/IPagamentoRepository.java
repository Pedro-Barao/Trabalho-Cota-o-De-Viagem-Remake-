package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Pagamento;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {
    
}
