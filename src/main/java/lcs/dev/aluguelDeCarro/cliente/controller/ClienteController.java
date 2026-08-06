package lcs.dev.aluguelDeCarro.cliente.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lcs.dev.aluguelDeCarro.cliente.dto.ClienteDTO;
import lcs.dev.aluguelDeCarro.cliente.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Expõe os endpoints REST de Cliente (prefixo /cliente)
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    // Injeção de dependência do service via construtor
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // SALVAR CLIENTE
    @PostMapping("/salvar")

    @Operation(summary = "Salvar um cliente",
            description = "Rota para inserir um cliente ao banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Cliente não foi salvo")
    })

    public ResponseEntity<ClienteDTO> salvarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO cliente = clienteService.criarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cliente);
    }

    // LISTAR CLIENTE
    @GetMapping("/listar")


    @Operation(summary = "Lista todos os Cliente",
            description = "Rota lista todos os Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Cliente encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista de Cliente não encontrada")
    })

    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> listCliente = clienteService.listarClientes();
        return ResponseEntity.ok(listCliente);
    }
    // LISTAR CLIENTE POR ID
    @GetMapping("/{id}")

    @Operation(summary = "Lista os Cliente pelo seu id",
            description = "Rota lista os Cliente pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })

    public ResponseEntity<?> listarClientePorId(@PathVariable Long id) {
        ClienteDTO clientePorId = clienteService.listarClientePorId(id);
        if (clientePorId != null) {
            return ResponseEntity.ok(clientePorId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente de Id: " + id + " não foi localizado");
        }
    }

    // ATUALIZAR CLIENTE POR ID
    @PutMapping("/{id}")

    @Operation(summary = "Altera um cliente por Id",
            description = "Rota altera os dados do cliente pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado, não foi possivel alterar")
    })

    public ResponseEntity<?> editarClientePorId(@PathVariable Long id, @Valid @RequestBody ClienteDTO cliente) {
        ClienteDTO atualizarCliente = clienteService.atualizarClientePorId(cliente, id);
        if (atualizarCliente != null) {
            return ResponseEntity.ok(atualizarCliente);
        }  else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente de Id: " + id + " não foi localizado.");
        }
    }

    // DELETAR CLIENTE
    @DeleteMapping("/{id}")

    @Operation(summary = "Deleta um cliente pelo seu id",
            description = "Rota deleta um cliente pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possivel deletar o cliente")
    })

    public ResponseEntity<?> deletarClientePorId(@PathVariable Long id) {
        if (clienteService.listarClientePorId(id) != null) {
            clienteService.excluirClientePorId(id);
            return ResponseEntity.ok("Cliente de Id: " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente de Id: " + id + " não foi localizado.");
        }
    }
}
