package lcs.dev.aluguelDeCarro.veiculo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lcs.dev.aluguelDeCarro.veiculo.dto.VeiculoDTO;

import lcs.dev.aluguelDeCarro.veiculo.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Expõe os endpoints REST de Veiculo (prefixo /veiculos)
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    // Injeção de dependência do service via construtor
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    // SALVAR VEICULO
    @PostMapping("/salvar")

    @Operation(summary = "Salvar um veiculo",
            description = "Rota para inserir um veiculo ao banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veiculo salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Veiculo não foi salvo")
    })

    public ResponseEntity<?> salvarVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        VeiculoDTO veiculoSalvo = veiculoService.salvarVeiculo(veiculoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(veiculoSalvo);
    }
    // LISTAR VEICULO
    @GetMapping("/listar")

    @Operation(summary = "Listar todos os veiculos",
            description = "Rota para listar todos veiculos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de veiculos encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista de veiculos não localziada")
    })

    public ResponseEntity<List<VeiculoDTO>> listarVeiculos() {
        List<VeiculoDTO> listarVeiculos = veiculoService.listarVeiculos();
        return ResponseEntity.ok(listarVeiculos);
    }

    // LISTAR VEICULO PELO ID
    @GetMapping("/{id}")

    @Operation(summary = "Listar veiculos pelo seu id",
            description = "Rota para localizar um veiculo pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veiculo localizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veiculo não localizado")
    })

    public ResponseEntity<?> buscarVeiculoPorId(@PathVariable Long id) {
         VeiculoDTO veiculoPorId = veiculoService.listarVeiculoPorId(id);
         if (veiculoPorId != null) {
             return ResponseEntity.ok(veiculoPorId);
         } else {
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body("Veiculo de Id: " + id + " não localizado.");
         }
    }
    // ATUALIZAR VEICULO PELO ID
    @PutMapping("/{id}")

    @Operation(summary = "Atualizar informações dos veiculos pelo id",
            description = "Rota para atualizar veiculos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veiculo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veiculo não foi atualizado")
    })

    public ResponseEntity<?> atualizarVeiculoPorId(@RequestBody VeiculoDTO veiculoDTO, @PathVariable Long id) {
        VeiculoDTO atualizarVeiculoPorId = veiculoService.atualizarVeiculoPorId(id, veiculoDTO);
        if (atualizarVeiculoPorId != null) {
            return ResponseEntity.ok(atualizarVeiculoPorId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Veiculo de Id: " + id + " não localizado.");
        }
    }

    // DELETAR VEICULO PELO ID
    @DeleteMapping("/{id}")

    @Operation(summary = "Deletar veiculo pelo seu id",
            description = "Rota para deletar um veiculo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veiculo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veiculo não foi deletado")
    })

    public ResponseEntity<?> deleteVeiculoPorId(@PathVariable Long id) {
        if (veiculoService.listarVeiculoPorId(id) != null) {
            veiculoService.excluirVeiculoPorId(id);
            return ResponseEntity.ok("Veiculo de Id: " + id + " deletado com sucesso.");
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Veiculo de Id: " + id + " não localizado.");
        }
    }
}
