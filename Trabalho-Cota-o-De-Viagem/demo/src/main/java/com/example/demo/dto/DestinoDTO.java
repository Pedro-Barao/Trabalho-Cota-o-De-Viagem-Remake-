package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DestinoDTO {
    
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 80, message = "O número de caractéres no nome não pode ser superior a 80 dígitos")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 500, message = "O número de caratéres na descrição não pode ser superior a 500 dígitos")
    private String descrição;

    @NotBlank(message = "A localização é obrigatória")
    @Size(max = 50, message = "O número de caratéres na descrição não pode ser superior a 50 dígitos")
    private String localização;

    @NotNull(message = "O preço por pessoa é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço por pessoa deve ser no mínimo 0.01")
    @Digits(integer = 10, fraction = 2, message = "O preço por pessoa deve ter no máximo 10 casas inteiras e 2 casas decimais")
    private BigDecimal preçoPorPessoa;
    
}
