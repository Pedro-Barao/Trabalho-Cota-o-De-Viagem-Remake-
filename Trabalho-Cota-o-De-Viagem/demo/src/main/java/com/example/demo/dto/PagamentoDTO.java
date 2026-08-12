package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagamentoDTO {
    
    private Long id;

    @NotNull(message = "O id da cotação é obrigatória")
    private Long cotacaoId;

    @NotNull(message = "O valor pago é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor do pagamento deve ser no mínimo 0.01")
    @Digits(integer = 8, fraction = 2, message = "O valor do pagamento deve ter no máximo 8 casas inteiras e 2 decimais")
    private BigDecimal valorPago;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    @DateTimeFormat
    @NotNull(message = "A data de pagamento é obrigatória")
    private LocalDateTime dataPagamento;
    
}
