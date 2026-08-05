package lcs.dev.aluguelDeCarro.aluguel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lcs.dev.aluguelDeCarro.aluguel.dto.AluguelDTO;
import lcs.dev.aluguelDeCarro.aluguel.service.AluguelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    // SALVAR ALUGUEL
    @PostMapping("/salvar")

    @Operation(summary = "Salvar um aluguel",
            description = "Rota para inserir um aluguel ao banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluguel salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Aluguel não foi salvo")
    })

    public ResponseEntity<AluguelDTO> salvarAluguel(@RequestBody AluguelDTO aluguelDTO) {
        AluguelDTO saveAluguel = aluguelService.salvarAluguel(aluguelDTO);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(saveAluguel);
    }

    // LISTAR ALUGUEL
    @GetMapping("/listar")

    @Operation(summary = "Lista todos os aluguel",
            description = "Rota lista todos os aluguel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de aluguel encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista de aluguel não encontrada")
    })

    public ResponseEntity<List<AluguelDTO>> listarAluguel() {
        List<AluguelDTO> listarAluguel = aluguelService.listarAluguel();
        return ResponseEntity.ok(listarAluguel);
    }

    // LISTAR ALUGUEL PELO ID
    @GetMapping("/{id}")

    @Operation(summary = "Lista os aluguel pelo seu id",
            description = "Rota lista os aluguel pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluguel encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluguel não encontrado")
    })

    public ResponseEntity<?> buscarAluguelPorId(@PathVariable long id) {
        AluguelDTO buscarAluguelPorId = aluguelService.consultarAluguelPorId(id);
        if (buscarAluguelPorId != null) {
            return ResponseEntity.ok(buscarAluguelPorId);
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aluguel de Id: " + id + " não foi encontrada");
        }
    }

    // ATUALIZAR ALUGUEL POR ID
    @PutMapping("/{id}")

    @Operation(summary = "Atualiza um aluguel pelo seu id",
            description = "Rota atualiza os dados do aluguel pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluguel encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluguel não encontrado")
    })

    public ResponseEntity<?> atualizarAluguelPorId(@PathVariable long id, @RequestBody AluguelDTO aluguelDTO) {
        AluguelDTO atualizarAluguel = aluguelService.atualizarAluguelPorId(id, aluguelDTO);
        if (atualizarAluguel != null) {
            return ResponseEntity.ok(atualizarAluguel);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aluguel de Id: " + id + " não foi encontrada");
        }
    }

    // DELETAR ALUGUEL
    @DeleteMapping("/{id}")

    @Operation(summary = "Deleta um aluguel pelo seu id",
            description = "Rota deleta um aluguel pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluguel deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possivel deletar o aluguel")
    })

    public ResponseEntity<?> deleteAluguelPorId(@PathVariable long id) {
        if (aluguelService.consultarAluguelPorId(id) != null) {
            aluguelService.deleteAluguelPorId(id);
            return ResponseEntity.ok("Aluguel de Id: " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aluguel de Id: " + id + " não foi encontrada");
        }
    }
}
