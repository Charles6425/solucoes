package com.arquiteto.solucoes.service;

import com.arquiteto.solucoes.model.Lancamento;
import com.arquiteto.solucoes.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRepository repository;

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
