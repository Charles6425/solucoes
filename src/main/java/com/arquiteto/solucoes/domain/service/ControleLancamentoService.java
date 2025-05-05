package com.arquiteto.solucoes.domain.service;

import com.arquiteto.solucoes.domain.model.Lancamento;
import com.arquiteto.solucoes.domain.model.SaldoDiario;
import com.arquiteto.solucoes.domain.model.ports.out.LancamentoConsultaPort;
import com.arquiteto.solucoes.enums.TipoLancamento;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ControleLancamentoService {

    private final LancamentoConsultaPort lancamentoConsultaPort;

    public ControleLancamentoService(LancamentoConsultaPort lancamentoConsultaPort) {
        this.lancamentoConsultaPort = lancamentoConsultaPort;
    }

    @CircuitBreaker(name = "consolidadoDiarioService", fallbackMethod = "fallbackCalcularSaldoDiario")
    public SaldoDiario calcularSaldoDiario(LocalDate data) {
        List<Lancamento> lancamentos = lancamentoConsultaPort.buscarTodos();

        BigDecimal saldo = lancamentos.stream()
                .filter(l -> l.getData().equals(data))
                .map(l -> l.getTipoLancamento() == TipoLancamento.CREDITO ? l.getValor() : l.getValor().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new SaldoDiario(data, saldo);
    }

    // Metodo de fallback caso o sistema de consolidado diário esteja indisponível
    public SaldoDiario fallbackCalcularSaldoDiario(LocalDate data, Throwable throwable) {
        return new SaldoDiario(data, BigDecimal.ZERO); // Retorna saldo zero como fallback
    }
}