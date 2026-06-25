package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entities.TipoDesconto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
public class DescontoDTO {

    private Long id;
    
/*

    @NotNull(message = "O id de cotação é obrigatório")
    private Long cotacaoId;

*/

    @NotNull(message = "O valor do desconto é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor do desconto deve ser no mínimo 0.01")
    @Digits(integer = 8, fraction = 2, message = "O valor do desconto deve ter no máximo 8 casas inteiras e 2 decimais")
    private BigDecimal valorDesconto;

    @NotBlank(message = "A descrição do desconto é obrigatória")
    @Size(max = 500, message = "Valor de carateres maior do que a quantidade permitida")
    private String descricao;

    @DateTimeFormat
    private LocalDateTime dataAplicacao;

    @NotNull(message = "O tipo do desconto é obrigatório")
    private TipoDesconto tipoDesconto;
    
}