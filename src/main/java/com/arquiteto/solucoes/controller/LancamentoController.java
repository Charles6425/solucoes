package com.arquiteto.solucoes.controller;

import com.arquiteto.solucoes.model.Lancamento;
import com.arquiteto.solucoes.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService service;

    /**
     * metodo para criar um lancamento
     * @param lancamento
     * @return
     */
    @PostMapping
    public ResponseEntity<Lancamento> create(@RequestBody Lancamento lancamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(lancamento));
    }

    /**
     * metodo para listar todos os lancamentos
     * @return
     */
    @GetMapping
    public List<Lancamento> findAll() {
        return service.findAll();
    }

    /**
     * metodo para listar um lancamento por id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * metodo para atualizar um lancamento
     * @param id
     * @param lancamento
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> update(@PathVariable Long id, @RequestBody Lancamento lancamento) {
        return service.findById(id).map(l -> {
            lancamento.setId(id);
            return ResponseEntity.ok(service.save(lancamento));
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * metodo para deletar um lancamento
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

