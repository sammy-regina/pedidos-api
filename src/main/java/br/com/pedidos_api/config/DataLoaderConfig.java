package br.com.pedidos_api.config;

import br.com.pedidos_api.entity.ClienteEntity;
import br.com.pedidos_api.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoaderConfig {

    // O CommandLineRunner é um método que o Spring executa AUTOMATICAMENTE
    // logo após a aplicação subir com sucesso.
    @Bean
    public CommandLineRunner carregarDadosIniciais(ClienteRepository clienteRepository) {
        return args -> {
            //Limpa o banco para garantir que não haverá duplicidade ao reiniciar
            clienteRepository.deleteAll();

            //Cria os clientes de teste usando o seu Builder
            ClienteEntity cliente1 = ClienteEntity.builder()
                    .nome("Sandra Regina")
                    .email("sandra@email.com")
                    .telefone("11999999990")
                    .cpf("12345678900")
                    .build();

            ClienteEntity cliente2 = ClienteEntity.builder()
                    .nome("Regina Maria")
                    .email("regina@email.com")
                    .telefone("11999999991")
                    .cpf("12345678901")
                    .build();

            ClienteEntity cliente3 = ClienteEntity.builder()
                    .nome("Pedro")
                    .email("pedro@email.com")
                    .telefone("11999999992")
                    .cpf("12345678902")
                    .build();

            //Salva no banco H2 automaticamente
            clienteRepository.save(cliente1);
            clienteRepository.save(cliente2);
            clienteRepository.save(cliente3);

            System.out.println("Dados de teste carregados com sucesso no H2!");
        };
    }
}