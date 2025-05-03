package com.arquiteto.solucoes.adapters.out;

import com.arquiteto.solucoes.domain.model.Lancamento;
import com.arquiteto.solucoes.domain.model.ports.out.LancamentoConsultaPort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LancamentoConsultaRepository implements LancamentoConsultaPort {

    private final SpringDataLancamentoRepository repository;

    public LancamentoConsultaRepository(SpringDataLancamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Lancamento> buscarTodos() {
        return repository.findAll();
    }
}
