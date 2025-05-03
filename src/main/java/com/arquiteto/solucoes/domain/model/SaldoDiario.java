package com.arquiteto.solucoes.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SaldoDiario {
    private LocalDate data;
    private BigDecimal saldo;

    public SaldoDiario(LocalDate data, BigDecimal saldo) {
        this.data = data;
        this.saldo = saldo;
    }


}
