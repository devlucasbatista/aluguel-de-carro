package lcs.dev.aluguelDeCarro.proprietario.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lcs.dev.aluguelDeCarro.proprietario.dto.ProprietarioDTO;
import lcs.dev.aluguelDeCarro.proprietario.service.ProprietarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Expõe os endpoints REST de Proprietario (prefixo /proprietario)
@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    // Injeção de dependência do service via construtor
    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    // SALVAR PROPRIETARIO
    @PostMapping("/salvar")
    @Operation(summary = "Salvar um proprietario",
            description = "Rota para inserir um proprietario ao banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proprietario salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Proprietario não foi salvo")
    })

    public ResponseEntity<ProprietarioDTO> salvarProprietario(@Valid @RequestBody ProprietarioDTO proprietarioDTO){
        ProprietarioDTO proprietarioDto = proprietarioService.salvarProprietario(proprietarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(proprietarioDto);
    }

    // LISTAR PROPRIETARIO
    @GetMapping("/lista")

    @Operation(summary = "Lista todos os proprietario",
            description = "Rota lista todos os proprietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proprietarios encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista de Proprietarios não encontrada")
    })

    public ResponseEntity<List<ProprietarioDTO>> listarProprietarios(){
        List<ProprietarioDTO> listarProprietarios = proprietarioService.listarProprietarios();
        return ResponseEntity.ok(listarProprietarios);
    }

    // LISTAR POR ID
    @GetMapping("/{id}")

    @Operation(summary = "Lista os proprietario pelo seu id",
            description = "Rota lista os proprietario pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proprietario encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Proprietario não encontrado")
    })

    public ResponseEntity<?> buscarProprietarioPorId(@PathVariable Long id){
        ProprietarioDTO proprietarioPorId = proprietarioService.buscarProprietarioPorId(id);
        if (proprietarioPorId != null) {
            return ResponseEntity.ok(proprietarioPorId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proprietario de Id: " + id + " Não foi encontrado.");
        }
    }

    // ATUALIZAR POR ID
    @PutMapping("/{id}")
    @Operation(summary = "Altera um proprietario por Id",
            description = "Rota altera os dados do proprietario pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proprietario alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Proprietario não encontrado, não foi possivel alterar")
    })

    public ResponseEntity<?> atualizarProprietarioPorId(@PathVariable Long id, @Valid @RequestBody ProprietarioDTO proprietarioDTO){
        ProprietarioDTO atualizarProprietarioPorId = proprietarioService.atualizarProprietarioPorId(id,proprietarioDTO);
        if (atualizarProprietarioPorId != null) {
            return ResponseEntity.ok(atualizarProprietarioPorId);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Proprietario de Id: " + id + " Não foi encontrado.");
    }

    // DELETAR POR ID
    @DeleteMapping("/{id}")


    @Operation(summary = "Deleta um proprietario pelo seu id",
            description = "Rota deleta um proprietario pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proprietario deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possivel deletar o proprietario")
    })

    public ResponseEntity<?> excluirProprietarioPorId(@PathVariable Long id) {
        if (proprietarioService.buscarProprietarioPorId(id) != null) {
            proprietarioService.deletarProprietarioPorId(id);
            return ResponseEntity.ok("Proprietario de Id: " + id + " Deletado com sucesso.");
        } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Proprietario de Id: " + id + " não foi localizado");
        }
    }
}
