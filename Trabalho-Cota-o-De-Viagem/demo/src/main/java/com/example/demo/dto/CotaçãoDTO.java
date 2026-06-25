package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entities.TipoCotação;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CotaçãoDTO {
    
    private Long id;

    @NotNull(message = "O id do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "O id do destino é obrigatório")
    private Long destinoId;

    @DateTimeFormat
    private LocalDateTime dataAplicacao;

    @NotNull(message = "O número de pessoas é obrigatório")
    private Integer númeroDePessoas;

    @NotNull(message = "O valor total é  obrigatório")
    private BigDecimal valorTotal;

    @NotNull(message = "O status é obrigatório")
    private TipoCotação status;
    
}
