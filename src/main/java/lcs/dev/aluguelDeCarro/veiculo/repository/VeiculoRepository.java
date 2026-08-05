package lcs.dev.aluguelDeCarro.veiculo.repository;

import lcs.dev.aluguelDeCarro.veiculo.model.VeiculoModel;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório do Veiculo: herda do Spring Data JPA os métodos prontos de CRUD (save, findById, findAll, deleteById, etc.)
public interface VeiculoRepository extends JpaRepository<VeiculoModel, Long> {
}
