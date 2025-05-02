package com.arquiteto.solucoes.model;


import com.arquiteto.solucoes.enums.TipoLancamento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String data;
    private Double valor;
    private TipoLancamento tipoLancamento;
    private String formaPagamento; // "C" para cartão, "B" para boleto, "D" para dinheiro
    private String status; // "P" para pago, "A" para a pagar


}
