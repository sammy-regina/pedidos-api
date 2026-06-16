package br.com.pedidos_api.mapper;

import br.com.pedidos_api.dto.ClienteRequestDTO;
import br.com.pedidos_api.dto.ClienteResponseDTO;
import br.com.pedidos_api.entity.ClienteEntity;

public class ClienteMapper {

    public static ClienteEntity toEntity(ClienteRequestDTO dto) { // Mapeia o DTO para a entidade, ou seja, converte os dados do formato de transferência para o formato de armazenamento
        if (dto == null) { // Verifica se o DTO é nulo antes de tentar mapear
            return null; // Se for null lança uma exceção, dependendo da sua preferência de tratamento de erros
        }
        return ClienteEntity.builder() // Se não ele mapeia normalmente
                .nome(dto.nome())
                .email(dto.email())
                .telefone(dto.telefone())
                .cpf(dto.cpf())
                .build();
    }

    public static ClienteResponseDTO toDTO(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ClienteResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getCpf()
        );
    }

}
