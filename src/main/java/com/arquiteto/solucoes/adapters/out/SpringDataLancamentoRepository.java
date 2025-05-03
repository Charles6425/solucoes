package com.arquiteto.solucoes.adapters.out;

import com.arquiteto.solucoes.domain.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataLancamentoRepository extends JpaRepository<Lancamento, Long> {
}