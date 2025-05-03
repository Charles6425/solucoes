package com.arquiteto.solucoes.domain.model.ports.out;

import com.arquiteto.solucoes.domain.model.Lancamento;

import java.util.List;

public interface LancamentoConsultaPort {
    List<Lancamento> buscarTodos();
}
