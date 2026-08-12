package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.dto.CotaçãoDTO;
import com.example.demo.entities.Cotação;

@Mapper(componentModel = "spring")
public interface CotaçãoMapper {
    
    CotaçãoDTO toDTO(Cotação cotação);

    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "destino", ignore = true)
    Cotação toEntity(CotaçãoDTO cotaçãoDTO);

    List<CotaçãoDTO> toDTOList(List<Cotação> cotações);

}
