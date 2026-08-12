package com.example.demo.mapper;

import com.example.demo.dto.PagamentoDTO;
import com.example.demo.entities.Pagamento;
import com.example.demo.entities.TipoPagamento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-12T08:15:21-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class PagamentoMapperImpl implements PagamentoMapper {

    @Override
    public PagamentoDTO toDTO(Pagamento pagamento) {
        if ( pagamento == null ) {
            return null;
        }

        PagamentoDTO pagamentoDTO = new PagamentoDTO();

        pagamentoDTO.setDataPagamento( pagamento.getDataPagamento() );
        pagamentoDTO.setId( pagamento.getId() );
        if ( pagamento.getStatus() != null ) {
            pagamentoDTO.setStatus( pagamento.getStatus().name() );
        }
        pagamentoDTO.setValorPago( pagamento.getValorPago() );

        return pagamentoDTO;
    }

    @Override
    public Pagamento toEntity(PagamentoDTO pagamentoDTO) {
        if ( pagamentoDTO == null ) {
            return null;
        }

        Pagamento pagamento = new Pagamento();

        pagamento.setDataPagamento( pagamentoDTO.getDataPagamento() );
        pagamento.setId( pagamentoDTO.getId() );
        if ( pagamentoDTO.getStatus() != null ) {
            pagamento.setStatus( Enum.valueOf( TipoPagamento.class, pagamentoDTO.getStatus() ) );
        }
        pagamento.setValorPago( pagamentoDTO.getValorPago() );

        return pagamento;
    }

    @Override
    public List<PagamentoDTO> toDTOList(List<Pagamento> pagamentos) {
        if ( pagamentos == null ) {
            return null;
        }

        List<PagamentoDTO> list = new ArrayList<PagamentoDTO>( pagamentos.size() );
        for ( Pagamento pagamento : pagamentos ) {
            list.add( toDTO( pagamento ) );
        }

        return list;
    }
}
