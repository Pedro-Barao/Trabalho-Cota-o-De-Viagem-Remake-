package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.dto.PagamentoDTO;
import com.example.demo.entities.Pagamento;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
    
    PagamentoDTO toDTO(Pagamento pagamento);

    @Mapping(target = "cotação", ignore = true)
    Pagamento toEntity(PagamentoDTO pagamentoDTO);

    List<PagamentoDTO> toDTOList(List<Pagamento> pagamentos);

}
