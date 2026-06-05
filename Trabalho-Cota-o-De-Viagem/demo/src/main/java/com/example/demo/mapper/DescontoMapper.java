package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.dto.DescontoDTO;
import com.example.demo.entities.Desconto;

@Mapper(componentModel = "spring")
public interface DescontoMapper {
    
    DescontoDTO toDTO(Desconto desconto);

    Desconto toEntity(DescontoDTO descontoDTO);

    List<DescontoDTO> toDTOList(List<Desconto> descontos);

}
