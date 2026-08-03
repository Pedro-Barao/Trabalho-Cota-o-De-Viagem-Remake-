package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PagamentoDTO;
import com.example.demo.entities.Cotação;
import com.example.demo.entities.Pagamento;
import com.example.demo.mapper.PagamentoMapper;
import com.example.demo.repository.ICotaçãoRepository;
import com.example.demo.repository.IPagamentoRepository;

@Service
public class PagamentoService {
    
    private final IPagamentoRepository pagamentoRepository;
    private final ICotaçãoRepository cotaçãoRepository;
    private final PagamentoMapper pagamentoMapper;

    public PagamentoService(IPagamentoRepository pagamentoRepository, ICotaçãoRepository cotaçãoRepository, PagamentoMapper pagamentoMapper)
    {

        this.pagamentoRepository = pagamentoRepository;
        this.cotaçãoRepository = cotaçãoRepository;
        this.pagamentoMapper = pagamentoMapper;

    }

    public List<PagamentoDTO> listarTodos()
    {

        return pagamentoMapper.toDTOList(pagamentoRepository.findAll());

    }

    public Optional<PagamentoDTO> buscarPorId(Long id)
    {

        return pagamentoRepository.findById(id).map(pagamentoMapper::toDTO);

    }

    public PagamentoDTO salvar(PagamentoDTO pagamentoDTO)
    {

        Pagamento pagamento = pagamentoMapper.toEntity(pagamentoDTO);

        Cotação cotaçãoReal = cotaçãoRepository.findById(pagamentoDTO.getCotaçãoId()).orElseThrow(() -> new RuntimeException("Cotação não encontrada"));
        pagamento.setCotação(cotaçãoReal);

        return pagamentoMapper.toDTO(pagamentoRepository.save(pagamento));

    }

    public PagamentoDTO atualizar(Long id, PagamentoDTO pagamentoDTO)
    {

        Pagamento pagamentoNovo = pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado com o id: " + id));

        Cotação cotaçãoReal = cotaçãoRepository.findById(pagamentoDTO.getCotaçãoId()).orElseThrow(() -> new RuntimeException("Cotação não encontrada"));

        pagamentoNovo.setCotação(cotaçãoReal);
        pagamentoNovo.setValorPago(pagamentoNovo.getValorPago());
        pagamentoNovo.setStatus(pagamentoNovo.getStatus());
        pagamentoNovo.setDataPagamento(pagamentoNovo.getDataPagamento());

        return pagamentoMapper.toDTO(pagamentoRepository.save(pagamentoNovo));

    }
    
    public PagamentoDTO atualizarParcialmente(Long id, PagamentoDTO pagamentoDTO)
    {

        Pagamento pagamentoNovo = pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado com o id: " + id));

        Cotação cotaçãoReal = cotaçãoRepository.findById(pagamentoDTO.getCotaçãoId()).orElseThrow(() -> new RuntimeException("Cotação não encontrada"));
        pagamentoNovo.setCotação(cotaçãoReal);

        if(pagamentoNovo.getCotação() != null) { pagamentoNovo.setCotação(cotaçãoReal); }        
        if(pagamentoNovo.getValorPago() != null) { pagamentoNovo.setValorPago(pagamentoNovo.getValorPago()); }
        if(pagamentoNovo.getStatus() != null) { pagamentoNovo.setStatus(pagamentoNovo.getStatus()); }
        if(pagamentoNovo.getDataPagamento() != null) { pagamentoNovo.setDataPagamento(pagamentoNovo.getDataPagamento()); }

        return pagamentoMapper.toDTO(pagamentoRepository.save(pagamentoNovo));

    }

    public void deletar(Long id)
    {

        Pagamento pagamentoNovo = pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado com o id: " + id));

        pagamentoRepository.delete(pagamentoNovo);

    }

}
