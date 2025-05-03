package com.arquiteto.solucoes.application.port.out;

import com.arquiteto.solucoes.domain.Lancamento;

import java.util.List;
import java.util.Optional;

public interface LancamentoRepositoryPort {
    Lancamento save(Lancamento lancamento);
    List<Lancamento> findAll();
    Optional<Lancamento> findById(Long id);
    void deleteById(Long id);
}