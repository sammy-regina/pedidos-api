package br.com.pedidos_api.controller;

import br.com.pedidos_api.dto.ClienteRequestDTO;
import br.com.pedidos_api.dto.ClienteResponseDTO;
import br.com.pedidos_api.entity.ClienteEntity;
import br.com.pedidos_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.pedidos_api.mapper.ClienteMapper;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO dto) {
        ClienteEntity novaEntidade = ClienteMapper.toEntity(dto);

        ClienteEntity entidadeSalva = clienteService.salvar(novaEntidade);

        ClienteResponseDTO resposta = ClienteMapper.toDTO(entidadeSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        // 1. Busca a lista de entidades do banco através do Service
        List<ClienteEntity> entidades = clienteService.listarTodos();

        // 2. Usa o Mapper para converter a lista de entidades em uma lista de DTOs seguros
        List<ClienteResponseDTO> dtos = ClienteMapper.toDTOList(entidades);

        // 3. Retorna o status 200 OK junto com a lista (que virá vazia [] se não houver clientes)
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        // Chama o serviço para buscar a entidade (que já valida se existe ou não)
        ClienteEntity entidade = clienteService.buscarPorId(id);

        // Converte a entidade encontrada para o DTO de saída seguro
        ClienteResponseDTO dto = ClienteMapper.toDTO(entidade);

        // Devolve os dados com o status 200 OK
        return ResponseEntity.ok(dto);
    }
}