package lcs.dev.aluguelDeCarro.exceptions;

public class VeiculoIndisponivelException extends RuntimeException {
    public VeiculoIndisponivelException(Long veiculoId) {
        super("Veículo de id " + veiculoId + " está indisponível para aluguel");
    }
}
