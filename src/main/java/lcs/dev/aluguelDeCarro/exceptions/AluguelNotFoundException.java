package lcs.dev.aluguelDeCarro.exceptions;

public class AluguelNotFoundException extends RuntimeException {

    // Exception lançada quando um Aluguel não é encontrado pelo id informado
    public AluguelNotFoundException(Long id) {
        super("Aluguel não encontrado com id: " + id);
    }
}
