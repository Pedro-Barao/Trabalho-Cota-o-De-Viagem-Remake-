package com.example.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cotação")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Cotação {
    
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clienteid", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "destinoid", nullable = false)
    private Destino destino;

    @Column(nullable = false)
    private LocalDateTime dataCotação;

    @Column(nullable = false)
    private Integer númeroDePessoas;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCotação status;

}
