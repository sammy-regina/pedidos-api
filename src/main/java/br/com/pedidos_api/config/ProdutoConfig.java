package br.com.pedidos_api.config;

import br.com.pedidos_api.entity.ProdutoEntity;
import br.com.pedidos_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ProdutoConfig implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {

        if (produtoRepository.count() == 0) {

            ProdutoEntity p1 = ProdutoEntity.builder()
                    .nome("Mouse")
                    .descricao("Mouse sem fio, preto")
                    .preco(new BigDecimal("10.00"))
                    .ativo(true)
                    .build();

            ProdutoEntity p2 = ProdutoEntity.builder()
                    .nome("Teclado")
                    .descricao("Teclado mecânico, preto")
                    .preco(new BigDecimal("30.50"))
                    .ativo(true)
                    .build();

            produtoRepository.saveAll(List.of(p1, p2));

            System.out.println("Carga inicial de produtos realizada com sucesso!");
        }
    }
}