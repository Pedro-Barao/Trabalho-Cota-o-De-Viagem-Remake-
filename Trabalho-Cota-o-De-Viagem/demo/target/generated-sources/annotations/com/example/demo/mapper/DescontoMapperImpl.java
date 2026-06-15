package com.example.demo.mapper;

import com.example.demo.dto.DescontoDTO;
import com.example.demo.entities.Desconto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T14:01:14-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DescontoMapperImpl implements DescontoMapper {

    @Override
    public DescontoDTO toDTO(Desconto desconto) {
        if ( desconto == null ) {
            return null;
        }

        DescontoDTO descontoDTO = new DescontoDTO();

        descontoDTO.setDataAplicacao( desconto.getDataAplicacao() );
        descontoDTO.setDescricao( desconto.getDescricao() );
        descontoDTO.setId( desconto.getId() );
        descontoDTO.setTipoDesconto( desconto.getTipoDesconto() );
        descontoDTO.setValorDesconto( desconto.getValorDesconto() );

        return descontoDTO;
    }

    @Override
    public Desconto toEntity(DescontoDTO descontoDTO) {
        if ( descontoDTO == null ) {
            return null;
        }

        Desconto desconto = new Desconto();

        desconto.setDataAplicacao( descontoDTO.getDataAplicacao() );
        desconto.setDescricao( descontoDTO.getDescricao() );
        desconto.setId( descontoDTO.getId() );
        desconto.setTipoDesconto( descontoDTO.getTipoDesconto() );
        desconto.setValorDesconto( descontoDTO.getValorDesconto() );

        return desconto;
    }

    @Override
    public List<DescontoDTO> toDTOList(List<Desconto> descontos) {
        if ( descontos == null ) {
            return null;
        }

        List<DescontoDTO> list = new ArrayList<DescontoDTO>( descontos.size() );
        for ( Desconto desconto : descontos ) {
            list.add( toDTO( desconto ) );
        }

        return list;
    }
}
