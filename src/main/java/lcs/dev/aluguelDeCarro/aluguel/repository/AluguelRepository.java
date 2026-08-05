package lcs.dev.aluguelDeCarro.aluguel.repository;

import lcs.dev.aluguelDeCarro.aluguel.model.AluguelModel;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório do Aluguel: herda do Spring Data JPA os métodos prontos de CRUD (save, findById, findAll, deleteById, etc.)
public interface AluguelRepository extends JpaRepository<AluguelModel, Long> {
}
