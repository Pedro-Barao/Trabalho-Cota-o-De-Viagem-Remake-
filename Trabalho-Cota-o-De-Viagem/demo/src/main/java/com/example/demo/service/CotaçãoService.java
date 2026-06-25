package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CotaçãoDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Cotação;
import com.example.demo.entities.Destino;
import com.example.demo.mapper.CotaçãoMapper;
import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.ICotaçãoRepository;
import com.example.demo.repository.IDestinoRepository;

@Service
public class CotaçãoService {
    
    private final ICotaçãoRepository cotaçãoRepository;
    private final IClienteRepository clienteRepository;
    private final IDestinoRepository destinoRepository;
    private final CotaçãoMapper cotaçãoMapper;

    public CotaçãoService(ICotaçãoRepository cotaçãoRepository, IClienteRepository clienteRepository, IDestinoRepository destinoRepository, CotaçãoMapper cotaçãoMapper)
    {

        this.cotaçãoRepository = cotaçãoRepository;
        this.clienteRepository = clienteRepository;
        this.destinoRepository = destinoRepository;
        this.cotaçãoMapper = cotaçãoMapper;

    }

    public List<CotaçãoDTO> listarTodos()
    {

        return cotaçãoMapper.toDTOList(cotaçãoRepository.findAll());

    }

    @SuppressWarnings("null")
    public CotaçãoDTO salvar(CotaçãoDTO cotaçãoDTO)
    {

        Cotação cotação = cotaçãoMapper.toEntity(cotaçãoDTO);

        Cliente clienteReal = clienteRepository.findById(cotaçãoDTO.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cotação.setCliente(clienteReal);

        Destino destinoReal = destinoRepository.findById(cotaçãoDTO.getDestinoId()).orElseThrow(() -> new RuntimeException("Destino não encontrado"));
        cotação.setDestino(destinoReal);

        return cotaçãoMapper.toDTO(cotaçãoRepository.save(cotação));

    }

}
