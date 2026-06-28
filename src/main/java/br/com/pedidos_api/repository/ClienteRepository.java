package br.com.pedidos_api.repository;

import br.com.pedidos_api.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByCpf(String cpf); // Método para encontrar um cliente pelo CPF, retornando um Optional para lidar com a possibilidade de não encontrar um cliente com o CPF fornecido

    Optional<ClienteEntity> findByEmail(String email); // Método para encontrar um cliente pelo email, retornando um Optional para lidar com a possibilidade de não encontrar um cliente com o email fornecido
}
