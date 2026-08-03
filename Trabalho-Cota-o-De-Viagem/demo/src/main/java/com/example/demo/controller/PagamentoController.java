package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PagamentoDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.ErrorResponse;
import com.example.demo.service.PagamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;


@Tag(name = "pPagamento", description = "Endpoints para gerenciamento de pagamentos")
@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService)
    {

        this.pagamentoService = pagamentoService;

    }

    @Operation(summary = "Lista todos os pagamentos", description = "Mostra todos os pagamentos do sistema")
    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> listarPagamentos()
    {

        List<PagamentoDTO> pagamentos = pagamentoService.listarTodos();

        return ResponseEntity.ok(pagamentos);

    }

    @Operation(summary = "Busca um pagamento por ID", description = "Retorna os detalhes de um pagamento")
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscarPorId(@PathVariable Long id) 
    {

        Optional<PagamentoDTO> pagamentoDTO = pagamentoService.buscarPorId(id);

        return pagamentoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Cria um novo pagamento", description = "Cadastra um novo pagamento no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<PagamentoDTO>> criarPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO)
    {

        try {

            PagamentoDTO savedPagamento = pagamentoService.salvar(pagamentoDTO);

            ApiResponse<PagamentoDTO> response = new ApiResponse<>(savedPagamento);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {

            ErrorResponse errorResponse = new ErrorResponse("Argumento inváĺido", e.getMessage());

            ApiResponse<PagamentoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<PagamentoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }

    @Operation(summary = "Altera um pagamento", description = "Altera um pagamento no sistema")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PagamentoDTO>> alterarPagamento(@PathVariable Long id, @Valid @RequestBody PagamentoDTO pagamentoDTO)
    {

        try {

            PagamentoDTO novoPagamento = pagamentoService.atualizar(id, pagamentoDTO);

            ApiResponse<PagamentoDTO> response = new ApiResponse<>(novoPagamento);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            ErrorResponse errorResponse = new ErrorResponse("Pagamento não encontrado", e.getMessage());

            ApiResponse<PagamentoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<PagamentoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }

    @Operation(summary = "Altera um pagamento", description = "Altera um pagamento no sistema")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<PagamentoDTO>> alterarParcialmentePagamento(@PathVariable Long id, @Valid @RequestBody PagamentoDTO pagamentoDTO)
    {

        try {

            PagamentoDTO novoPagamento = pagamentoService.atualizarParcialmente(id, pagamentoDTO);

            ApiResponse<PagamentoDTO> response = new ApiResponse<>(novoPagamento);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            ErrorResponse errorResponse = new ErrorResponse("Pagamento não encontrado", e.getMessage());

            ApiResponse<PagamentoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<PagamentoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }
    
    @Operation(summary = "Deleta um pagamento", description = "Remove um pagamento do sistema pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Long id)
    {

        pagamentoService.deletar(id);

        return ResponseEntity.noContent().build();

    }

}
