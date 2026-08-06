package lcs.dev.aluguelDeCarro.exceptions;

public class ClienteNotFoundException extends RuntimeException {

    // Exception lançada quando um Cliente não é encontrado pelo id informado
    public ClienteNotFoundException(Long id) {
        super("Cliente não encontrado com id: " + id);
    }
}
