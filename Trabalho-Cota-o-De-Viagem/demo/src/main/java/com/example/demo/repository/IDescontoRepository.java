package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Desconto;

public interface IDescontoRepository extends JpaRepository<Desconto, Long> {

/*

    Optional<Desconto> findByCotacaoId(Long cotacaoId);

*/

}