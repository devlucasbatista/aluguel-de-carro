package lcs.dev.aluguelDeCarro.exceptions;

public class VeiculoNotFoundException extends RuntimeException {

    // Exception lançada quando um Veículo não é encontrado pelo id informado
    public VeiculoNotFoundException(Long id) {
        super("Veiculo não encontrado com id: " + id);
    }
}
