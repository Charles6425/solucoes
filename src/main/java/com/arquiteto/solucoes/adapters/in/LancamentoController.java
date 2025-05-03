package com.arquiteto.solucoes.adapters.in;

import com.arquiteto.solucoes.application.LancamentoService;
import com.arquiteto.solucoes.domain.Lancamento;
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

    @PostMapping
    public ResponseEntity<Lancamento> create(@RequestBody Lancamento lancamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(lancamento));
    }

    @GetMapping
    public List<Lancamento> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> update(@PathVariable Long id, @RequestBody Lancamento lancamento) {
        return service.findById(id).map(l -> {
            lancamento.setId(id);
            return ResponseEntity.ok(service.save(lancamento));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}