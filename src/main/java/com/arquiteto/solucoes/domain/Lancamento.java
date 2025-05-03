package com.arquiteto.solucoes.domain;


import com.arquiteto.solucoes.enums.TipoLancamento;
import lombok.Data;

@Data
public class Lancamento {
    private Long id;
    private String descricao;
    private String data;
    private Double valor;
    private TipoLancamento tipoLancamento;
    private String formaPagamento; // "C" para cartão, "B" para boleto, "D" para dinheiro
    private String status; // "P" para pago, "A" para a pagar


}
