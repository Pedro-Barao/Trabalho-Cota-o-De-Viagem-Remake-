package com.example.demo.mapper;

import com.example.demo.dto.DescontoDTO;
import com.example.demo.entities.Desconto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-07T07:57:38-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class DescontoMapperImpl implements DescontoMapper {

    @Override
    public DescontoDTO toDTO(Desconto desconto) {
        if ( desconto == null ) {
            return null;
        }

        DescontoDTO descontoDTO = new DescontoDTO();

        descontoDTO.setId( desconto.getId() );
        descontoDTO.setCotacaoId( desconto.getCotacaoId() );
        descontoDTO.setValorDesconto( desconto.getValorDesconto() );
        descontoDTO.setDescricao( desconto.getDescricao() );
        descontoDTO.setDataAplicacao( desconto.getDataAplicacao() );
        descontoDTO.setTipoDesconto( desconto.getTipoDesconto() );

        return descontoDTO;
    }

    @Override
    public Desconto toEntity(DescontoDTO descontoDTO) {
        if ( descontoDTO == null ) {
            return null;
        }

        Desconto desconto = new Desconto();

        desconto.setId( descontoDTO.getId() );
        desconto.setCotacaoId( descontoDTO.getCotacaoId() );
        desconto.setValorDesconto( descontoDTO.getValorDesconto() );
        desconto.setDescricao( descontoDTO.getDescricao() );
        desconto.setDataAplicacao( descontoDTO.getDataAplicacao() );
        desconto.setTipoDesconto( descontoDTO.getTipoDesconto() );

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
