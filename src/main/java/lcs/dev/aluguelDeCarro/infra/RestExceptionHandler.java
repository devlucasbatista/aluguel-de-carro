package lcs.dev.aluguelDeCarro.infra;

import lcs.dev.aluguelDeCarro.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// Classe global de tratamento de exceptions: intercepta os erros lançados pelos services
// de todas as entidades e devolve respostas HTTP padronizadas (404 para não encontrado, 400 para dados inválidos)
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

    // Captura erros de validação (@Valid) e retorna 400 com um mapa de campo -> mensagem de erro
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(erro ->
                erros.put(erro.getField(), erro.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
    // Captura PeriodoInvalido e retorna 400 com a mensagem do erro
    @ExceptionHandler(PeriodoInvalidoException.class)
    public ResponseEntity<String> handlePeriodoInvalido(PeriodoInvalidoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    // Captura VeiculoIndisponivel e retorna 400 com a mensagem do erro
    @ExceptionHandler(VeiculoIndisponivelException.class)
    public ResponseEntity<String> handleVeiculoIndisponivel(VeiculoIndisponivelException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}