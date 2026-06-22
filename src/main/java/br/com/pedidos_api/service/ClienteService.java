package br.com.pedidos_api.service;

import br.com.pedidos_api.entity.ClienteEntity;
import br.com.pedidos_api.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // Injeção de dependência do ClienteRepository sem o @Autowired facilita testes unitarios pois permite a injeção de mocks ou stubs do repositório durante os testes, promovendo um design mais flexível e testável.
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    // A anotação @Transactional é usada para garantir que as operações de banco de dados sejam executadas dentro de uma transação, o que ajuda a manter a integridade dos dados e a lidar com falhas de forma mais eficaz. Se ocorrer uma exceção durante a execução do método, a transação será revertida automaticamente, garantindo que o banco de dados permaneça em um estado consistente.
    public ClienteEntity salvar(ClienteEntity cliente) {

        //Regra para validar se o CPF ja existe
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado: " + cliente.getCpf());
        }
        //Regra para validar se o Email ja existe
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado: " + cliente.getEmail());
        }

        return clienteRepository.save(cliente);
    }
}
