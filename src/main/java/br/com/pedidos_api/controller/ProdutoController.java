package br.com.pedidos_api.controller;

import br.com.pedidos_api.dto.ProdutoRequestDTO;
import br.com.pedidos_api.dto.ProdutoResponseDTO;
import br.com.pedidos_api.service.ProdutoService;
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
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO dto){
        ProdutoResponseDTO produtos = produtoService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtos);

    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos(){

        List<ProdutoResponseDTO> produtos = produtoService.ListarTodos();

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id){
        ProdutoResponseDTO produto = produtoService.buscarPorId(id);

        return ResponseEntity.ok(produto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {

        ProdutoResponseDTO produtoAtualizado = produtoService.atualizar(id, dto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        produtoService.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/fisico")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletarFisico(id);
        return ResponseEntity.noContent().build();
    }

}