package com.arquiteto.solucoes.adapters.out;

import com.arquiteto.solucoes.application.port.out.LancamentoRepository;
import com.arquiteto.solucoes.domain.Lancamento;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LancamentoRepositoryImpl implements LancamentoRepository {

    private final List<Lancamento> database = new ArrayList<>();

    @Override
    public Lancamento save(Lancamento lancamento) {
        database.add(lancamento);
        return lancamento;
    }

    @Override
    public List<Lancamento> findAll() {
        return database;
    }

    @Override
    public Optional<Lancamento> findById(Long id) {
        return database.stream().filter(l -> l.getId().equals(id)).findFirst();
    }

    @Override
    public void deleteById(Long id) {
        database.removeIf(l -> l.getId().equals(id));
    }
}