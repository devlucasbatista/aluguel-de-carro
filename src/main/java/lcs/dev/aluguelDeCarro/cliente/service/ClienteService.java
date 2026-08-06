package lcs.dev.aluguelDeCarro.cliente.service;

import lcs.dev.aluguelDeCarro.cliente.dto.ClienteDTO;
import lcs.dev.aluguelDeCarro.cliente.mapper.ClienteMapper;
import lcs.dev.aluguelDeCarro.cliente.model.ClienteModel;
import lcs.dev.aluguelDeCarro.cliente.repository.ClienteRepository;
import lcs.dev.aluguelDeCarro.exceptions.ClienteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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
    // Lança ClienteNotFoundException se o id não existir

    public ClienteDTO listarClientePorId(Long id) {
        ClienteModel clienteModel = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
                return clienteMapper.toDTO(clienteModel);
    }

    // ATUALIZAR CLIENTE PELO ID
    // Busca a entidade existente, sobrescreve os campos com os dados do DTO e salva novamente
    public ClienteDTO atualizarClientePorId(ClienteDTO dto, Long id) {
            ClienteModel cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ClienteNotFoundException(id));
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

    // DELETAR CLIENTE PELO ID
    public void excluirClientePorId(Long id) {
        if(!clienteRepository.existsById(id)){
            throw new ClienteNotFoundException(id);
        }
        clienteRepository.deleteById(id);
    }
}
