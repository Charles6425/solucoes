package com.arquiteto.solucoes.adapters.in;

import com.arquiteto.solucoes.domain.model.SaldoDiario;
import com.arquiteto.solucoes.domain.model.ports.in.SaldoDiarioServicePort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador responsável por gerenciar os endpoints relacionados aos saldos diários.
 */
@RestController
@RequestMapping("/api/saldos")
public class SaldoDiarioController {

    private final SaldoDiarioServicePort service;

    public SaldoDiarioController(SaldoDiarioServicePort service) {
        this.service = service;
    }

    @GetMapping
    public List<SaldoDiario> listarSaldos() {
        return service.obterSaldosConsolidados();
    }
}

