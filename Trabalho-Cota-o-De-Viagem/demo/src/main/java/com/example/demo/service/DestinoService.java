package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DestinoDTO;
import com.example.demo.entities.Destino;
import com.example.demo.mapper.DestinoMapper;
import com.example.demo.repository.IDestinoRepository;

@Service
public class DestinoService {
    
    private final IDestinoRepository destinoRepository;
    private final DestinoMapper destinoMapper;

    public DestinoService(IDestinoRepository destinoRepository, DestinoMapper destinoMapper)
    {

        this.destinoMapper = destinoMapper;
        this.destinoRepository = destinoRepository;

    }

    public List<DestinoDTO> listarTodos()
    {

        return destinoMapper.toDTOList(destinoRepository.findAll());

    }

    @SuppressWarnings("null")
    public Optional<DestinoDTO> buscarPorId(Long id)
    {

        return destinoRepository.findById(id).map(destinoMapper::toDTO);

    }

    @SuppressWarnings("null")
    public DestinoDTO salvar(DestinoDTO destinoDTO)
    {

        Destino destino = destinoMapper.toEntity(destinoDTO);

        return destinoMapper.toDTO(destinoRepository.save(destino));

    }

    public DestinoDTO atualizar(Long id, DestinoDTO destinoDTO)
    {

        @SuppressWarnings("null")
        Destino destinoNovo = destinoRepository.findById(id).orElseThrow(() -> new RuntimeException("Destino não encontrado com o id: " + id));

        destinoNovo.setNome(destinoDTO.getNome());
        destinoNovo.setDescrição(destinoDTO.getDescrição());
        destinoNovo.setLocalização(destinoDTO.getLocalização());
        destinoNovo.setPreçoPorPessoa(destinoDTO.getPreçoPorPessoa());

        return destinoMapper.toDTO(destinoRepository.save(destinoNovo));

    }

    @SuppressWarnings("null")
    public DestinoDTO atualizarParcialmente(Long id, DestinoDTO destinoDTO)
    {

        Destino destinoNovo = destinoRepository.findById(id).orElseThrow(() -> new RuntimeException("Destino não encontrado com o id: " + id));

        if (destinoDTO.getNome() != null) { destinoDTO.setNome(destinoNovo.getNome()); }
        if (destinoDTO.getDescrição() != null) { destinoDTO.setDescrição(destinoNovo.getDescrição()); }
        if (destinoDTO.getLocalização() != null) { destinoDTO.setLocalização(destinoNovo.getLocalização()); }
        if (destinoDTO.getPreçoPorPessoa() != null) { destinoDTO.setPreçoPorPessoa(destinoNovo.getPreçoPorPessoa()); }

        return destinoMapper.toDTO(destinoRepository.save(destinoNovo));

    }

    @SuppressWarnings("null")
    public void deletar(Long id)
    {

        Destino destinoNovo = destinoRepository.findById(id).orElseThrow(() -> new RuntimeException("Destino não encontrado com o id: " + id));

        destinoRepository.delete(destinoNovo);

    }

}
