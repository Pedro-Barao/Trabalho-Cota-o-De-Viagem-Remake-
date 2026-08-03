package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<CotaçãoDTO> buscarPorId(Long id)
    {

        return cotaçãoRepository.findById(id).map(cotaçãoMapper::toDTO);

    }

    public CotaçãoDTO salvar(CotaçãoDTO cotaçãoDTO)
    {

        Cotação cotação = cotaçãoMapper.toEntity(cotaçãoDTO);

        Cliente clienteReal = clienteRepository.findById(cotaçãoDTO.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cotação.setCliente(clienteReal);

        Destino destinoReal = destinoRepository.findById(cotaçãoDTO.getDestinoId()).orElseThrow(() -> new RuntimeException("Destino não encontrado"));
        cotação.setDestino(destinoReal);

        return cotaçãoMapper.toDTO(cotaçãoRepository.save(cotação));

    }

    public CotaçãoDTO atualizar(Long id, CotaçãoDTO cotaçãoDTO)
    {

        Cotação cotaçãoNova = cotaçãoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cotação não encontrada com o id: " + id));

        Cliente clienteReal = clienteRepository.findById(cotaçãoDTO.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Destino destinoReal = destinoRepository.findById(cotaçãoDTO.getDestinoId()).orElseThrow(() -> new RuntimeException("Destino não encontrado"));

        cotaçãoNova.setCliente(clienteReal);
        cotaçãoNova.setDestino(destinoReal);
        cotaçãoNova.setDataCotação(cotaçãoDTO.getDataCotação());
        cotaçãoNova.setNúmeroDePessoas(cotaçãoDTO.getNúmeroDePessoas());
        cotaçãoNova.setValorTotal(cotaçãoDTO.getValorTotal());
        cotaçãoNova.setStatus(cotaçãoDTO.getStatus());

        return cotaçãoMapper.toDTO(cotaçãoRepository.save(cotaçãoNova));

    }
    
    public CotaçãoDTO atualizarParcialmente(Long id, CotaçãoDTO cotaçãoDTO)
    {

        Cotação cotaçãoNova = cotaçãoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cotação não encontrada com o id: " + id));

        Cliente clienteReal = clienteRepository.findById(cotaçãoDTO.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Destino destinoReal = destinoRepository.findById(cotaçãoDTO.getDestinoId()).orElseThrow(() -> new RuntimeException("Destino não encontrado"));

        if(cotaçãoDTO.getClienteId() != null) { cotaçãoNova.setCliente(clienteReal); }
        if(cotaçãoDTO.getDestinoId() != null) { cotaçãoNova.setDestino(destinoReal); }
        if(cotaçãoDTO.getDataCotação() != null) { cotaçãoNova.setDataCotação(cotaçãoDTO.getDataCotação()); }
        if(cotaçãoDTO.getNúmeroDePessoas() != null) { cotaçãoNova.setNúmeroDePessoas(cotaçãoDTO.getNúmeroDePessoas()); }
        if(cotaçãoDTO.getValorTotal() != null) { cotaçãoNova.setValorTotal(cotaçãoDTO.getValorTotal()); }
        if(cotaçãoDTO.getStatus() != null) { cotaçãoNova.setStatus(cotaçãoDTO.getStatus()); }

        return cotaçãoMapper.toDTO(cotaçãoRepository.save(cotaçãoNova));

    }

    public void deletar(Long id)
    {

        Cotação cotaçãoNova = cotaçãoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cotação não encontrada com o id: " + id));

        cotaçãoRepository.delete(cotaçãoNova);

    }

}
