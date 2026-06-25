package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.dto.DestinoDTO;
import com.example.demo.entities.Destino;

@Mapper(componentModel = "spring")
public interface DestinoMapper {
    
    DestinoDTO toDTO(Destino destino);

    Destino toEntity(DestinoDTO destinoDTO);

    List<DestinoDTO> toDTOList(List<Destino> destinos);

}
