package br.com.pedidos_api.service;

import java.util.NoSuchElementException;
import br.com.pedidos_api.dto.ProdutoRequestDTO;
import br.com.pedidos_api.dto.ProdutoResponseDTO;
import br.com.pedidos_api.entity.ProdutoEntity;
import br.com.pedidos_api.mapper.ProdutoMapper;
import br.com.pedidos_api.repository.ProdutoRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO produtoRequestDTO) {

        ProdutoEntity produtoEntity = ProdutoMapper.toEntity(produtoRequestDTO);

        ProdutoEntity produtoSalvo = produtoRepository.save(produtoEntity);

        return ProdutoMapper.toDTO(produtoSalvo);
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> ListarTodos() {
        List<ProdutoEntity> produtos = produtoRepository.findAll();
        return ProdutoMapper.toDTOList(produtos);
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO buscarPorId(Long id) {
        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado com o ID: " + id));
        return ProdutoMapper.toDTO(produto);
    }
}