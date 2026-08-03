package com.example.demo.mapper;

import com.example.demo.dto.CotaçãoDTO;
import com.example.demo.entities.Cotação;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-27T11:15:55-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class CotaçãoMapperImpl implements CotaçãoMapper {

    @Override
    public CotaçãoDTO toDTO(Cotação cotação) {
        if ( cotação == null ) {
            return null;
        }

        CotaçãoDTO cotaçãoDTO = new CotaçãoDTO();

        cotaçãoDTO.setDataCotação( cotação.getDataCotação() );
        cotaçãoDTO.setId( cotação.getId() );
        cotaçãoDTO.setNúmeroDePessoas( cotação.getNúmeroDePessoas() );
        cotaçãoDTO.setStatus( cotação.getStatus() );
        cotaçãoDTO.setValorTotal( cotação.getValorTotal() );

        return cotaçãoDTO;
    }

    @Override
    public Cotação toEntity(CotaçãoDTO cotaçãoDTO) {
        if ( cotaçãoDTO == null ) {
            return null;
        }

        Cotação cotação = new Cotação();

        cotação.setDataCotação( cotaçãoDTO.getDataCotação() );
        cotação.setId( cotaçãoDTO.getId() );
        cotação.setNúmeroDePessoas( cotaçãoDTO.getNúmeroDePessoas() );
        cotação.setStatus( cotaçãoDTO.getStatus() );
        cotação.setValorTotal( cotaçãoDTO.getValorTotal() );

        return cotação;
    }

    @Override
    public List<CotaçãoDTO> toDTOList(List<Cotação> cotações) {
        if ( cotações == null ) {
            return null;
        }

        List<CotaçãoDTO> list = new ArrayList<CotaçãoDTO>( cotações.size() );
        for ( Cotação cotação : cotações ) {
            list.add( toDTO( cotação ) );
        }

        return list;
    }
}
