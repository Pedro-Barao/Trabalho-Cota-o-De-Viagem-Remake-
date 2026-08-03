package com.example.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagamento")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Pagamento {
    
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cotaçãoId", nullable = false)
    private Cotação cotação;

    @JoinColumn(nullable = false)
    private BigDecimal valorPago;

    @JoinColumn(nullable = false)
    private TipoPagamento status;

    @JoinColumn(nullable = false)
    private LocalDateTime dataPagamento;

}
