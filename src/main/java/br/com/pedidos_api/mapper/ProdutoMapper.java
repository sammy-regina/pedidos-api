package br.com.pedidos_api.mapper;

import br.com.pedidos_api.dto.ProdutoRequestDTO;
import br.com.pedidos_api.dto.ProdutoResponseDTO;
import br.com.pedidos_api.entity.ProdutoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapper {

    public static ProdutoEntity toEntity(ProdutoRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return ProdutoEntity.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .preco(dto.preco())
                .ativo(dto.ativo())
                .build();
    }

    public static ProdutoResponseDTO toDTO(ProdutoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ProdutoResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getAtivo()
        );
    }

    public static List<ProdutoResponseDTO> toDTOList(List<ProdutoEntity> entidades) {
        if (entidades == null) {
            return List.of();
        }
        return entidades.stream()
                .map(ProdutoMapper::toDTO)
                .toList();
    }
}