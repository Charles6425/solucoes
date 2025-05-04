package com.arquiteto.solucoes.adapters.in;

import com.arquiteto.solucoes.application.service.LancamentoService;
import com.arquiteto.solucoes.domain.model.Lancamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    private final LancamentoService service;

    public LancamentoController(LancamentoService service) {
        this.service = service;
    }

    @Operation(summary = "Cria um novo lançamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lançamento criado com sucesso")
    })
    @PostMapping
    public ResponseEntity<Lancamento> create(@RequestBody Lancamento lancamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(lancamento));
    }

    @Operation(summary = "Retorna todos os lançamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de lançamentos")
    })
    @GetMapping
    public List<Lancamento> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Retorna um lançamento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lançamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Lançamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza um lançamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lançamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lançamento não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> update(@PathVariable Long id, @RequestBody Lancamento lancamento) {
        return service.findById(id).map(l -> {
            lancamento.setId(id);
            return ResponseEntity.ok(service.save(lancamento));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um lançamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lançamento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lançamento não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}