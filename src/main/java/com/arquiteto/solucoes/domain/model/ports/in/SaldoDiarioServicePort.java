package com.arquiteto.solucoes.domain.model.ports.in;

import com.arquiteto.solucoes.domain.model.SaldoDiario;

import java.util.List;

public interface SaldoDiarioServicePort {
    List<SaldoDiario> obterSaldosConsolidados();
}

