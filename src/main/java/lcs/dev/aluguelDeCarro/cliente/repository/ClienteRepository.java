package lcs.dev.aluguelDeCarro.cliente.repository;

import lcs.dev.aluguelDeCarro.cliente.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório do Cliente: herda do Spring Data JPA os métodos prontos de CRUD (save, findById, findAll, deleteById, etc.)
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}
