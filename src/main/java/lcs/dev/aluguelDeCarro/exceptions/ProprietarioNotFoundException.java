package lcs.dev.aluguelDeCarro.exceptions;

public class ProprietarioNotFoundException extends RuntimeException {

    // Exception lançada quando um Proprietário não é encontrado pelo id informado
    public ProprietarioNotFoundException(Long id) {
        super("Proprietario não encontrado com id: " + id);
    }
}
