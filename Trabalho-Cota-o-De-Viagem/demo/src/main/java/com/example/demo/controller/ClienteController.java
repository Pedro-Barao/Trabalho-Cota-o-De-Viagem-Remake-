package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DTO_Clientes.ClienteDTO;
import com.example.demo.dto.DTO_Clientes.ClienteDTO_PATCH;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.ErrorResponse;
import com.example.demo.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;


@Tag(name = "Cliente", description = "Endpoints para gerenciamento de clientes")
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService)
    {

        this.clienteService = clienteService;

    }

    @Operation(summary = "Lista todos os clientes", description = "Mostra todos os clientes do sistema")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes()
    {

        List<ClienteDTO> clientes = clienteService.listarTodos();

        return ResponseEntity.ok(clientes);

    }

    @Operation(summary = "Busca um cliente por ID", description = "Retorna os detalhes de um cliente")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) 
    {

        Optional<ClienteDTO> clienteDTO = clienteService.buscarPorId(id);

        return clienteDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Cria um novo cliente", description = "Cadastra um novo cliente no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<ClienteDTO>> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO)
    {

        try {

            ClienteDTO savedCliente = clienteService.salvar(clienteDTO);

            ApiResponse<ClienteDTO> response = new ApiResponse<>(savedCliente);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {

            ErrorResponse errorResponse = new ErrorResponse("Argumento inváĺido", e.getMessage());

            ApiResponse<ClienteDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<ClienteDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }

    @Operation(summary = "Altera um cliente", description = "Altera um cliente no sistema")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteDTO>> alterarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO)
    {

        try {

            ClienteDTO novoCliente = clienteService.atualizar(id, clienteDTO);

            ApiResponse<ClienteDTO> response = new ApiResponse<>(novoCliente);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            ErrorResponse errorResponse = new ErrorResponse("Cliente não encontrado", e.getMessage());

            ApiResponse<ClienteDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<ClienteDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }

    @Operation(summary = "Altera um cliente", description = "Altera um cliente no sistema")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteDTO>> alterarParcialmenteCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO_PATCH clienteDTO_patch)
    {

        try {

            ClienteDTO novoCliente = clienteService.atualizarParcialmente(id, clienteDTO_patch);

            ApiResponse<ClienteDTO> response = new ApiResponse<>(novoCliente);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            ErrorResponse errorResponse = new ErrorResponse("Cliente não encontrado", e.getMessage());

            ApiResponse<ClienteDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {

            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<ClienteDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }
    
    @Operation(summary = "Deleta um cliente", description = "Remove um cliente do sistema pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id)
    {

        clienteService.deletar(id);

        return ResponseEntity.noContent().build();

    }

}
