package br.com.pedidos_api.mapper;

import br.com.pedidos_api.dto.ClienteRequestDTO;
import br.com.pedidos_api.dto.ClienteResponseDTO;
import br.com.pedidos_api.entity.ClienteEntity;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<ClienteResponseDTO> toDTOList(List<ClienteEntity> entidades) {
        if (entidades == null) {
            return List.of(); // Retorna uma lista vazia segura
        }

        // Pega a lista de entidades, transforma em stream, converte cada uma usando o método toDTO que já criamos, e junta tudo numa nova lista.
        return entidades.stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

}
