package com.example.demo.service;

import com.example.demo.mapper.DescontoMapperImpl;
import com.example.demo.repository.IDescontoRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.repository.IClienteRepository;

@Service
public class ClienteService {
    
    private final IClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(IClienteRepository clienteRepository, ClienteMapper clienteMapper, IDescontoRepository IDescontoRepository, DescontoMapperImpl descontoMapperImpl)
    {

        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;

    }

    public List<ClienteDTO> listarTodos()
    {

        return clienteMapper.toDTOList(clienteRepository.findAll());

    }

    public Optional<ClienteDTO> buscarPorId(Long id)
    {

        return clienteRepository.findById(id).map(clienteMapper::toDTO);

    }

    public ClienteDTO salvar(ClienteDTO clienteDTO)
    {

        if (clienteRepository.existsByEmail(clienteDTO.getEmail()))
        {

            throw new IllegalArgumentException("Email já existe no sistema");

        }

        Cliente cliente = clienteMapper.toEntity(clienteDTO);

        return clienteMapper.toDTO(clienteRepository.save(cliente));
        
    }

    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO)
    {

        Cliente clienteNovo = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id: " + id));

        clienteNovo.setNome(clienteDTO.getNome());
        clienteNovo.setEmail(clienteDTO.getEmail());
        clienteNovo.setTelefone(clienteDTO.getTelefone());

        return clienteMapper.toDTO(clienteRepository.save(clienteNovo));

    }

    public ClienteDTO atualizarParcialmente(Long id, ClienteDTO clienteDTO)
    {

        Cliente clienteNovo = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id: " + id));

        if (clienteDTO.getNome() != null) { clienteDTO.setNome(clienteNovo.getNome()); }
        if (clienteDTO.getEmail() != null) { clienteDTO.setEmail(clienteNovo.getEmail()); }
        if (clienteDTO.getTelefone() != null) { clienteDTO.setTelefone(clienteNovo.getTelefone()); }

        return clienteMapper.toDTO(clienteRepository.save(clienteNovo));

    }

    public void deletar(Long id)
    {

        Cliente clienteNovo = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id: " + id));

        clienteRepository.delete(clienteNovo);

    }

}
