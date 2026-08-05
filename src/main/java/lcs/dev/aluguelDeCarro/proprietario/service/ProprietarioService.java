package lcs.dev.aluguelDeCarro.proprietario.service;

import lcs.dev.aluguelDeCarro.proprietario.dto.ProprietarioDTO;
import lcs.dev.aluguelDeCarro.proprietario.mapper.ProprietarioMapper;
import lcs.dev.aluguelDeCarro.proprietario.model.ProprietarioModel;
import lcs.dev.aluguelDeCarro.proprietario.repository.ProprietarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Camada de serviço: concentra a regra de negócio de Proprietario entre o controller e o repositório
@Service
public class ProprietarioService {

    // INJEÇÃO DE DEPENDENCIAS

    private final ProprietarioRepository proprietarioRepository;
    private final ProprietarioMapper proprietarioMapper;

    public ProprietarioService(ProprietarioRepository proprietarioRepository, ProprietarioMapper proprietarioMapper) {
        this.proprietarioRepository = proprietarioRepository;
        this.proprietarioMapper = proprietarioMapper;
    }

    // SALVAR PROPRIETARIO
    // Converte o DTO recebido em entidade, persiste no banco e retorna o resultado já como DTO
    public ProprietarioDTO salvarProprietario(ProprietarioDTO proprietarioDTO) {
        ProprietarioModel proprietarioNovo = proprietarioMapper.toModel(proprietarioDTO);
        ProprietarioModel proprietarioSalvo = proprietarioRepository.save(proprietarioNovo);

        return proprietarioMapper.toDTO(proprietarioSalvo);
    }

    // LISTAR PROPRIETARIOS
    // Busca todos os registros e converte cada entidade em DTO
    public List<ProprietarioDTO> listarProprietarios() {
        List<ProprietarioModel> proprietarios = proprietarioRepository.findAll();
        return proprietarios.stream()
                .map(proprietarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ACHAR PROPRIETARIO PELO SEU ID
    // Retorna null (tratado no controller) se não encontrar
    public ProprietarioDTO buscarProprietarioPorId(Long id) {
        return proprietarioMapper.toDTO(proprietarioRepository.findById(id).orElse(null));
    }
    // ATUALIZAR PROPRIETARIO PELO ID
    // Busca a entidade existente, sobrescreve os campos com os dados do DTO e salva novamente
    public ProprietarioDTO atualizarProprietarioPorId(Long id, ProprietarioDTO proprietarioDTO) {
        Optional<ProprietarioModel> proprietarioExiste = proprietarioRepository.findById(id);
        if (proprietarioExiste.isPresent()) {
            ProprietarioModel proprietario = proprietarioExiste.get();
            proprietario.setNome(proprietarioDTO.getNome());
            proprietario.setEmail(proprietarioDTO.getEmail());
            proprietario.setTelefone(proprietarioDTO.getTelefone());
            proprietario.setEndereco(proprietarioDTO.getEndereco());
            proprietario.setSexo(proprietarioDTO.getSexo());
            proprietario.setIdade(proprietarioDTO.getIdade());
            proprietario.setCpf(proprietarioDTO.getCpf());
            proprietarioRepository.save(proprietario);
            return proprietarioMapper.toDTO(proprietario);
        }
        return null; // id não encontrado
    }
    // DELETAR PROPRIETARIO PELO ID
    public void deletarProprietarioPorId(Long id) {
        proprietarioRepository.deleteById(id);
    }

}
