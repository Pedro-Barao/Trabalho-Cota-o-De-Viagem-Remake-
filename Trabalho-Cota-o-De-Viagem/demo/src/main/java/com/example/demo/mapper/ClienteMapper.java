package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.entities.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    
    ClienteDTO toDTO(Cliente cliente);

    Cliente toEntity(ClienteDTO clienteDTO);

    List<ClienteDTO> toDTOList(List<Cliente> clientes);
    
}
