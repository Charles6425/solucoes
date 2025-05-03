package com.arquiteto.solucoes.adapters.out;

import com.arquiteto.solucoes.application.port.out.LancamentoRepositoryPort;
import com.arquiteto.solucoes.domain.Lancamento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LancamentoRepositoryImpl implements LancamentoRepositoryPort {

    private final SpringDataLancamentoRepository repository;

    public LancamentoRepositoryImpl(SpringDataLancamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Lancamento save(Lancamento lancamento) {
        return repository.save(lancamento);
    }

    @Override
    public List<Lancamento> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Lancamento> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}