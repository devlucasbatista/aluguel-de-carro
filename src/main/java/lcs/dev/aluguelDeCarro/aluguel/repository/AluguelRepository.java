package lcs.dev.aluguelDeCarro.aluguel.repository;

import lcs.dev.aluguelDeCarro.aluguel.model.AluguelModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository extends JpaRepository<AluguelModel, Long> {
}
