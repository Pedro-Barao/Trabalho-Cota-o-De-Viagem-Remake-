package com.example.demo.dto.DTO_Clientes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {
    
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 80, message = "O número de caractéres no nome não pode ser superior a 80 dígitos")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Size(max = 254, message = "O número de caractéres no email não pode ser superior a 254 dígitos")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Size(max = 15, message = "O número de telefone não pode superar 15 dígitos")
    private String telefone;

}
