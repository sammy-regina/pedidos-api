package br.com.pedidos_api.controller;

import br.com.pedidos_api.dto.ClienteRequestDTO;
import br.com.pedidos_api.dto.ClienteResponseDTO;
import br.com.pedidos_api.entity.ClienteEntity;
import br.com.pedidos_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.pedidos_api.mapper.ClienteMapper;

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
}