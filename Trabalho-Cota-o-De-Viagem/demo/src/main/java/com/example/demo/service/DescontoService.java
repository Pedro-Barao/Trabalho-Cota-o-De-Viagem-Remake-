package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DescontoDTO;
import com.example.demo.entities.Desconto;
import com.example.demo.entities.TipoDesconto;
import com.example.demo.mapper.DescontoMapper;
import com.example.demo.repository.IDescontoRepository;

@Service
public class DescontoService {
    
    private final IDescontoRepository descontoRepository;
    private final DescontoMapper descontoMapper;

    public DescontoService(IDescontoRepository descontoRepository, DescontoMapper descontoMapper) {

        this.descontoRepository = descontoRepository;
        this.descontoMapper = descontoMapper;

    }

    public List<DescontoDTO> listarTodos() 
    {

        return descontoMapper.toDTOList(descontoRepository.findAll());
        
    }

    public Optional<DescontoDTO> buscarPorId(Long id) 
    {

        return descontoRepository.findById(id).map(descontoMapper::toDTO);
        
    }

    public DescontoDTO salvar(DescontoDTO descontoDTO)
    {

        // validação extra
        if (descontoDTO.getValorDesconto() != null 
            && descontoDTO.getValorDesconto().compareTo(BigDecimal.ZERO) <= 0) 
        {

            throw new IllegalArgumentException("O valor do desconto deve ser maior que zero");

        }

        if (descontoDTO.getTipoDesconto() == TipoDesconto.PERCENTUAL 
            && descontoDTO.getTipoDesconto() != null 
            && descontoDTO.getValorDesconto().compareTo(new BigDecimal("100")) > 0)
        {

            throw new IllegalArgumentException("Desconto percentual não pode ser maior que 100%");
        }

        Desconto desconto = descontoMapper.toEntity(descontoDTO);

        desconto.setDataAplicacao(LocalDateTime.now());

        if(desconto.getDataAplicacao() == null)
        {

            desconto.setDataAplicacao(LocalDateTime.now());

        }
        
        return descontoMapper.toDTO(descontoRepository.save(desconto));
        
    }

    public DescontoDTO atualizar(Long id, DescontoDTO descontoDTO)
    {

        Desconto descontoNovo = descontoRepository.findById(id).orElseThrow(() -> new RuntimeException("Desconto não encontrado com o id: " + id));

        descontoNovo.setValorDesconto(descontoDTO.getValorDesconto());
        descontoNovo.setDescricao(descontoDTO.getDescricao());
        descontoNovo.setTipoDesconto(descontoDTO.getTipoDesconto());
        descontoNovo.setDataAplicacao(LocalDateTime.now());

        return descontoMapper.toDTO(descontoRepository.save(descontoNovo));

    }

    public DescontoDTO atualizarParcialmente(Long id, DescontoDTO descontoDTO)
    {

        Desconto descontoNovo = descontoRepository.findById(id).orElseThrow(() -> new RuntimeException("Desconto não encontrado com o id: " + id));

        if(descontoDTO.getValorDesconto() != null) { descontoNovo.setValorDesconto(descontoDTO.getValorDesconto()); }
        if(descontoDTO.getDescricao() != null) { descontoNovo.setDescricao(descontoDTO.getDescricao()); }
        if(descontoDTO.getDataAplicacao() == null) { descontoNovo.setDataAplicacao(LocalDateTime.now()); }
        if(descontoDTO.getTipoDesconto() != null) { descontoNovo.setTipoDesconto(descontoDTO.getTipoDesconto()); }

        return descontoMapper.toDTO(descontoRepository.save(descontoNovo));

    }

    public void deletar(Long id)
    {

        Desconto descontoNovo = descontoRepository.findById(id).orElseThrow(() -> new RuntimeException("Desconto não encontrado com o id: " + id));

        descontoRepository.delete(descontoNovo);
        
    }

}