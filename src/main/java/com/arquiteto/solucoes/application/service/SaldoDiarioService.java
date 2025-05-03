package com.arquiteto.solucoes.application.service;

import com.arquiteto.solucoes.domain.model.Lancamento;
import com.arquiteto.solucoes.domain.model.SaldoDiario;
import com.arquiteto.solucoes.domain.model.ports.in.SaldoDiarioServicePort;
import com.arquiteto.solucoes.domain.model.ports.out.LancamentoConsultaPort;
import com.arquiteto.solucoes.enums.TipoLancamento;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaldoDiarioService implements SaldoDiarioServicePort {

    private final LancamentoConsultaPort lancamentoConsultaPort;

    public SaldoDiarioService(LancamentoConsultaPort lancamentoConsultaPort) {
        this.lancamentoConsultaPort = lancamentoConsultaPort;
    }

    @Override
    @Cacheable("saldosConsolidados")
    public List<SaldoDiario> obterSaldosConsolidados() {
        List<Lancamento> lancamentos = lancamentoConsultaPort.buscarTodos();

        return lancamentos.stream()
                .collect(Collectors.groupingBy(
                        Lancamento::getData,
                        Collectors.reducing(BigDecimal.ZERO,
                                l -> l.getTipoLancamento() == TipoLancamento.CREDITO ? l.getValor() : l.getValor().negate(),
                                BigDecimal::add)))
                .entrySet().stream()

                .map(e -> new SaldoDiario(e.getKey(), e.getValue()))
                .sorted(Comparator.comparing(SaldoDiario::getData))
                .collect(Collectors.toList());
    }
}

