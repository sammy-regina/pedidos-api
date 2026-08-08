package br.com.pedidos_api.controller;

import br.com.pedidos_api.dto.ClienteRequestDTO;
import br.com.pedidos_api.dto.ClienteResponseDTO;
import br.com.pedidos_api.entity.ClienteEntity;
import br.com.pedidos_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO dto) { // recebe um DTO de requisição de cliente, valida os dados e cria um novo cliente no banco de dados
        ClienteEntity novoCliente = ClienteMapper.toEntity(dto); // converte o DTO de requisição para uma entidade de cliente e salva na variável novoCliente

        ClienteEntity clienteSalvo = clienteService.salvar(novoCliente); // chama o método salvar do service para persistir o novo cliente no banco de dados e retorna a entidade salva correspondente, que é salva na variável clienteSalvo

        ClienteResponseDTO cliente = ClienteMapper.toDTO(clienteSalvo); // converte a entidade salva para um DTO de resposta seguro e salva na variável cliente

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente); // retorna uma resposta HTTP com status 201 Created e o DTO do cliente criado no corpo da resposta
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() { // busca todos os clientes no banco de dados e retorna uma lista de DTOs de resposta
        List<ClienteEntity> clientes = clienteService.listarTodos(); // busca todos os clientes no banco de dados através do Service e retorna uma lista de entidades correspondentes e salva na variável clientes

        List<ClienteResponseDTO> dtos = ClienteMapper.toDTOList(clientes); // converte a lista de entidades para uma lista de DTOs de saída seguros e salva na variável dtos

        return ResponseEntity.ok(dtos); // retorna o status 200 OK junto com a lista de DTOs dos clientes encontrados
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) { // busca um cliente específico pelo ID no banco de dados

        ClienteEntity cliente = clienteService.buscarPorId(id); // busca o cliente no banco de dados através do Service e retorna a entidade correspondente e salva na variável cliente

        ClienteResponseDTO dto = ClienteMapper.toDTO(cliente); // converte a entidade para o DTO de saída seguro e salva na variável dto

        return ResponseEntity.ok(dto); // retorna o status 200 OK junto com o DTO do cliente encontrado
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) { // atualiza os dados de um cliente específico no banco de dados com base no ID fornecido e nos novos dados fornecidos no DTO de requisição
        ClienteEntity dadosNovos = ClienteMapper.toEntity(dto); // converte o DTO de requisição para uma entidade de cliente e salva na variável dadosNovos

        ClienteEntity entidadeAtualizada = clienteService.atualizar(id, dadosNovos); // chama o método atualizar do service para atualizar os dados do cliente no banco de dados com base no ID fornecido e nos novos dados fornecidos, e retorna a entidade atualizada correspondente, que é salva na variável entidadeAtualizada

        ClienteResponseDTO resposta = ClienteMapper.toDTO(entidadeAtualizada); // converte a entidade atualizada para um DTO de resposta seguro e salva na variável resposta

        return ResponseEntity.ok(resposta); // retorna o status 200 OK junto com o DTO do cliente atualizado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) { // chama o método deletar do service para remover o cliente do banco de dados com base no ID fornecido
        clienteService.deletar(id); // chama o método deletar do service para remover o cliente do banco de dados com base no ID fornecido

        return ResponseEntity.noContent().build(); // retorna uma resposta HTTP com status 204 No Content, indicando que a operação de exclusão foi bem-sucedida, mas não há conteúdo adicional a ser retornado na resposta
    }

}