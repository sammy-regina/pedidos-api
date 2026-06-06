package br.com.pedidos_api.repository;

import br.com.pedidos_api.entity.ClienteEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Deve salvar um cliente com sucesso e gerar o ID autoincrementado")
    void deveSalvarClienteComSucesso() {

        ClienteEntity novoCliente = ClienteEntity.builder()
                .nome("Sammy Mentor")
                .email("sammy@mentoria.com")
                .telefone("11999999999")
                .cpf("12345678901")
                .build();

        ClienteEntity clienteSalvo = clienteRepository.save(novoCliente);

        assertThat(clienteSalvo.getId()).isGreaterThan(0);

        Optional<ClienteEntity> clienteNoBanco = clienteRepository.findById(clienteSalvo.getId());
        assertThat(clienteNoBanco).isPresent();
        assertThat(clienteNoBanco.get().getNome()).isEqualTo("Sammy Mentor");
    }

    @Test
    @DisplayName("Deve falhar ao tentar salvar dois clientes com o mesmo CPF (Design for Failure)")
    void naoDevePermitirCpfDuplicado() {

        ClienteEntity cliente1 = ClienteEntity.builder()
                .nome("Primeiro Cliente")
                .email("primeiro@email.com")
                .cpf("11122233344")
                .build();
        clienteRepository.save(cliente1);

        ClienteEntity cliente2 = ClienteEntity.builder()
                .nome("Segundo Cliente")
                .email("segundo@email.com")
                .cpf("11122233344")
                .build();

        assertThatThrownBy(() -> clienteRepository.saveAndFlush(cliente2))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}