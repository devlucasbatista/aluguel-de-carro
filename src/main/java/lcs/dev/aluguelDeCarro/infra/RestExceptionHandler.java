package lcs.dev.aluguelDeCarro.infra;

import lcs.dev.aluguelDeCarro.exceptions.AluguelNotFoundException;
import lcs.dev.aluguelDeCarro.exceptions.ClienteNotFoundException;
import lcs.dev.aluguelDeCarro.exceptions.ProprietarioNotFoundException;
import lcs.dev.aluguelDeCarro.exceptions.VeiculoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Classe global de tratamento de exceptions: intercepta os erros lançados pelos services
// de todas as entidades e devolve uma resposta HTTP padronizada (404) em vez do erro genérico do Spring
@ControllerAdvice
public class RestExceptionHandler {

    // Captura AluguelNotFoundException e retorna 404 com a mensagem do erro
    @ExceptionHandler(AluguelNotFoundException.class)
    public ResponseEntity<String> handleAluguelNotFound(AluguelNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    // Captura ClienteNotFoundException e retorna 404 com a mensagem do erro
    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<String> handlerClienteNotFound(ClienteNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    // Captura ProprietarioNotFoundException e retorna 404 com a mensagem do erro
    @ExceptionHandler(ProprietarioNotFoundException.class)
    public ResponseEntity<String> proprietarioNotFound(ProprietarioNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    // Captura VeiculoNotFoundException e retorna 404 com a mensagem do erro
    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<String> veiculoNotFound(VeiculoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}