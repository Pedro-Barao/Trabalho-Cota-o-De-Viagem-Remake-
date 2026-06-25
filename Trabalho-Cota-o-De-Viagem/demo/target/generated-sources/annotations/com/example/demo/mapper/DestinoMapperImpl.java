package com.example.demo.mapper;

import com.example.demo.dto.DestinoDTO;
import com.example.demo.entities.Destino;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-25T15:08:45-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class DestinoMapperImpl implements DestinoMapper {

    @Override
    public DestinoDTO toDTO(Destino destino) {
        if ( destino == null ) {
            return null;
        }

        DestinoDTO destinoDTO = new DestinoDTO();

        destinoDTO.setDescrição( destino.getDescrição() );
        destinoDTO.setId( destino.getId() );
        destinoDTO.setLocalização( destino.getLocalização() );
        destinoDTO.setNome( destino.getNome() );
        destinoDTO.setPreçoPorPessoa( destino.getPreçoPorPessoa() );

        return destinoDTO;
    }

    @Override
    public Destino toEntity(DestinoDTO destinoDTO) {
        if ( destinoDTO == null ) {
            return null;
        }

        Destino destino = new Destino();

        destino.setDescrição( destinoDTO.getDescrição() );
        destino.setId( destinoDTO.getId() );
        destino.setLocalização( destinoDTO.getLocalização() );
        destino.setNome( destinoDTO.getNome() );
        destino.setPreçoPorPessoa( destinoDTO.getPreçoPorPessoa() );

        return destino;
    }

    @Override
    public List<DestinoDTO> toDTOList(List<Destino> destinos) {
        if ( destinos == null ) {
            return null;
        }

        List<DestinoDTO> list = new ArrayList<DestinoDTO>( destinos.size() );
        for ( Destino destino : destinos ) {
            list.add( toDTO( destino ) );
        }

        return list;
    }
}
