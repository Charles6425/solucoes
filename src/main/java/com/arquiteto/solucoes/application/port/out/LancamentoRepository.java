package com.arquiteto.solucoes.application.port.out;

import com.arquiteto.solucoes.domain.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}