package br.com.pedidos_api.service;

import br.com.pedidos_api.entity.ClienteEntity;
import br.com.pedidos_api.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

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

    public List<ClienteEntity> listarTodos() {
        // O método findAll() busca tudo o que está na tabela do banco H2
        return clienteRepository.findAll();
    }

    public ClienteEntity buscarPorId(Long id) {
        // Busca no repositório. Se a caixa estiver cheia, extrai o Cliente.
        // Se estiver vazia, lança uma exceção "NoSuchElementException"
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente com ID " + id + " não foi encontrado."));
    }

    @Transactional
    public ClienteEntity atualizar(Long id, ClienteEntity dadosAtualizados) {
        // Busca o cliente atual no banco. Se não existir, o buscarPorId já lança a exceção do 404!
        ClienteEntity clienteExistente = buscarPorId(id);

        // Atualiza os campos do cliente que está no banco com os novos dados
        clienteExistente.setNome(dadosAtualizados.getNome());
        clienteExistente.setEmail(dadosAtualizados.getEmail());
        clienteExistente.setTelefone(dadosAtualizados.getTelefone());
        clienteExistente.setCpf(dadosAtualizados.getCpf());

        // Salva a entidade modificada de volta no H2
        return clienteRepository.save(clienteExistente);
    }

}
