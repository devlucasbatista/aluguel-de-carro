package lcs.dev.aluguelDeCarro.cliente.service;

import lcs.dev.aluguelDeCarro.cliente.dto.ClienteDTO;
import lcs.dev.aluguelDeCarro.cliente.mapper.ClienteMapper;
import lcs.dev.aluguelDeCarro.cliente.model.ClienteModel;
import lcs.dev.aluguelDeCarro.cliente.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Camada de serviço: concentra a regra de negócio de Cliente entre o controller e o repositório
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    // Injeção de dependência via construtor
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    // SALVAR CLIENTE
    // Converte o DTO recebido em entidade, persiste no banco e retorna o resultado já como DTO
    public ClienteDTO criarCliente(ClienteDTO dto) {
        ClienteModel clienteNovo = clienteMapper.toModel(dto);
        ClienteModel clienteSalvo = clienteRepository.save(clienteNovo);
        return clienteMapper.toDTO(clienteSalvo);
    }

    // LISTAR CLIENTES
    // Busca todos os registros e converte cada entidade em DTO
    public List<ClienteDTO> listarClientes() {
        List<ClienteModel> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }
    // LISTAR CLIENTE PELO ID
    // Retorna null (tratado no controller) se não encontrar
    public ClienteDTO listarClientePorId(Long id) {
        return clienteMapper.toDTO(clienteRepository.findById(id).orElse(null));
    }

    // ATUALIZAR CLIENTE PELO ID
    // Busca a entidade existente, sobrescreve os campos com os dados do DTO e salva novamente
    public ClienteDTO atualizarClientePorId(ClienteDTO dto, Long id) {
        Optional<ClienteModel> clienteExiste = clienteRepository.findById(id);
        if (clienteExiste.isPresent()) {
            ClienteModel cliente = clienteExiste.get();
            cliente.setNome(dto.getNome());
            cliente.setCpf(dto.getCpf());
            cliente.setEmail(dto.getEmail());
            cliente.setEndereco(dto.getEndereco());
            cliente.setTelefone(dto.getTelefone());
            cliente.setSexo(dto.getSexo());
            cliente.setCnh(dto.getCnh());
            cliente.setIdade(dto.getIdade());
            clienteRepository.save(cliente);
            return clienteMapper.toDTO(cliente);
        }
        return null; // id não encontrado
    }

    // DELETAR CLIENTE PELO ID
    public void excluirClientePorId(Long id) {
        clienteRepository.deleteById(id);
    }
}
