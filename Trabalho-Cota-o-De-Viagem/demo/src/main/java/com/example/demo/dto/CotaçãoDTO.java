package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entities.TipoCotação;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CotaçãoDTO {
    
    private Long id;

    @NotNull(message = "O id do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "O id do destino é obrigatório")
    private Long destinoId;

    @DateTimeFormat
    private LocalDateTime dataCotação;

    @NotNull(message = "O número de pessoas é obrigatório")
    private Integer númeroDePessoas;

    @NotNull(message = "O valor total é  obrigatório")
    @DecimalMin(value = "0.01", message = "O valor da cotação deve ser no mínimo 0.01")
    @Digits(integer = 8, fraction = 2, message = "O valor da cotação deve ter no máximo 8 casas inteiras e 2 decimais")
    private BigDecimal valorTotal;

    @NotNull(message = "O status é obrigatório")
    private TipoCotação status;
    
}
