package br.com.pedidos_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProdutoRequestDTO(

    @NotBlank(message = "O nome do produto é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome do produto deve ter entre 3 e 100 caracteres.")
    String nome,

    @NotBlank(message = "A descrição do produto é obrigatória.")
    @Size(min = 3, max = 255, message = "A descrição do produto deve ter entre 3 e 255 caracteres.")
    String descricao,

    @NotNull(message = "O preço do produto é obrigatório.")
    @Positive(message = "O preço do produto deve ser um valor maior que zero.")
    BigDecimal preco,

    @NotNull(message = "O status do produto é obrigatório.")
    Boolean ativo

) {}