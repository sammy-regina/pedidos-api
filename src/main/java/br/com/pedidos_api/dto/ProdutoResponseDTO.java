package br.com.pedidos_api.dto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(

        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean ativo
) {}