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

import com.example.demo.dto.CotaçãoDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.ErrorResponse;
import com.example.demo.service.CotaçãoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;


@Tag(name = "Cotação", description = "Endpoints para gerenciamento de cotações")
@RestController
@RequestMapping("/api/cotações")
public class CotaçãoController {

    private final CotaçãoService cotaçãoService;

    public CotaçãoController(CotaçãoService cotaçãoService)
    {

        this.cotaçãoService = cotaçãoService;

    }

    @Operation(summary = "Lista todas as cotações", description = "Mostra todas as cotações do sistema")
    @GetMapping
    public ResponseEntity<List<CotaçãoDTO>> listarCotaçãos()
    {

        List<CotaçãoDTO> cotaçãos = cotaçãoService.listarTodos();

        return ResponseEntity.ok(cotaçãos);

    }

    @Operation(summary = "Busca uma cotação por ID", description = "Retorna os detalhes de uma cotação")
    @GetMapping("/{id}")
    public ResponseEntity<CotaçãoDTO> buscarPorId(@PathVariable Long id) 
    {

        Optional<CotaçãoDTO> cotaçãoDTO = cotaçãoService.buscarPorId(id);

        return cotaçãoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Cria uma nova cotação", description = "Cadastra uma nova cotação no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<CotaçãoDTO>> criarCotação(@Valid @RequestBody CotaçãoDTO cotaçãoDTO)
    {

        try {

            CotaçãoDTO savedCotação = cotaçãoService.salvar(cotaçãoDTO);

            ApiResponse<CotaçãoDTO> response = new ApiResponse<>(savedCotação);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {

            ErrorResponse errorResponse = new ErrorResponse("Argumento inváĺido", e.getMessage());

            ApiResponse<CotaçãoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<CotaçãoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }

    @Operation(summary = "Altera uma cotação", description = "Altera uma cotação no sistema")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CotaçãoDTO>> alterarCotação(@PathVariable Long id, @Valid @RequestBody CotaçãoDTO cotaçãoDTO)
    {

        try {

            CotaçãoDTO novoCotação = cotaçãoService.atualizar(id, cotaçãoDTO);

            ApiResponse<CotaçãoDTO> response = new ApiResponse<>(novoCotação);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            ErrorResponse errorResponse = new ErrorResponse("Cotação não encontrada", e.getMessage());

            ApiResponse<CotaçãoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<CotaçãoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }

    @Operation(summary = "Altera uma cotação", description = "Altera uma cotação no sistema")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CotaçãoDTO>> alterarParcialmenteCotação(@PathVariable Long id, @Valid @RequestBody CotaçãoDTO cotaçãoDTO)
    {

        try {

            CotaçãoDTO novoCotação = cotaçãoService.atualizarParcialmente(id, cotaçãoDTO);

            ApiResponse<CotaçãoDTO> response = new ApiResponse<>(novoCotação);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            ErrorResponse errorResponse = new ErrorResponse("Cotação não encontrada", e.getMessage());

            ApiResponse<CotaçãoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<CotaçãoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }
    
    @Operation(summary = "Deleta um cotação", description = "Remove uma cotação do sistema pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCotação(@PathVariable Long id)
    {

        cotaçãoService.deletar(id);

        return ResponseEntity.noContent().build();

    }

}
