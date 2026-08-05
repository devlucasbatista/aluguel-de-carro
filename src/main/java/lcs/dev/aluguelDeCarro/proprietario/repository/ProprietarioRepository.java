package lcs.dev.aluguelDeCarro.proprietario.repository;

import lcs.dev.aluguelDeCarro.proprietario.model.ProprietarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório do Proprietario: herda do Spring Data JPA os métodos prontos de CRUD (save, findById, findAll, deleteById, etc.)
public interface ProprietarioRepository extends JpaRepository<ProprietarioModel, Long> {
}
