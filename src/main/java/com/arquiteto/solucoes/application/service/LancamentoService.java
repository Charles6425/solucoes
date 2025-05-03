package com.arquiteto.solucoes.application.service;

import com.arquiteto.solucoes.application.port.out.LancamentoRepositoryPort;
import com.arquiteto.solucoes.domain.Lancamento;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    private final LancamentoRepositoryPort repository;

    public LancamentoService(LancamentoRepositoryPort repository) {
        this.repository = repository;
    }

    public Lancamento save(Lancamento lancamento) {
        return repository.save(lancamento);
    }

    public List<Lancamento> findAll() {
        return repository.findAll();
    }

    public Optional<Lancamento> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}