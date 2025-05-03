package com.arquiteto.solucoes.domain.model;

import com.arquiteto.solucoes.enums.TipoLancamento;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "lancamentos")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String data;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;

    private String formaPagamento; // "C" para cartão, "B" para boleto, "D" para dinheiro

    private String status; // "P" para pago, "A" para a pagar
}