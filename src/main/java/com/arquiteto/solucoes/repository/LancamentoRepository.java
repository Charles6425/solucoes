package com.arquiteto.solucoes.repository;

import com.arquiteto.solucoes.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
