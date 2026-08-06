package lcs.dev.aluguelDeCarro.exceptions;

public class PeriodoInvalidoException extends RuntimeException {
    public PeriodoInvalidoException() {
        super("A data de fim não pode ser anterior a data de inicio");
    }
}
