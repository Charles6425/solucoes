package com.arquiteto.solucoes.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.arquiteto.solucoes.domain.model.Lancamento;
import com.arquiteto.solucoes.domain.model.SaldoDiario;
import com.arquiteto.solucoes.domain.model.ports.out.LancamentoConsultaPort;
import com.arquiteto.solucoes.enums.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ControleLancamentoService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ControleLancamentoServiceTest {
    @Autowired
    private ControleLancamentoService controleLancamentoService;

    @MockitoBean
    private LancamentoConsultaPort lancamentoConsultaPort;

    /**
     * Test {@link ControleLancamentoService#calcularSaldoDiario(LocalDate)}.
     * <ul>
     *   <li>Then return Saldo is {@link BigDecimal#BigDecimal(String)} with {@code 0}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ControleLancamentoService#calcularSaldoDiario(LocalDate)}
     */
    @Test
    @DisplayName("Test calcularSaldoDiario(LocalDate); then return Saldo is BigDecimal(String) with '0'")
    @Tag("MaintainedBy")
    void testCalcularSaldoDiario_thenReturnSaldoIsBigDecimalWith0() {
        // Arrange
        when(lancamentoConsultaPort.buscarTodos()).thenReturn(new ArrayList<>());
        LocalDate data = LocalDate.of(1970, 1, 1);

        // Act
        SaldoDiario actualCalcularSaldoDiarioResult = controleLancamentoService.calcularSaldoDiario(data);

        // Assert
        verify(lancamentoConsultaPort).buscarTodos();
        BigDecimal expectedSaldo = new BigDecimal("0");
        assertEquals(expectedSaldo, actualCalcularSaldoDiarioResult.getSaldo());
        assertSame(data, actualCalcularSaldoDiarioResult.getData());
    }

    /**
     * Test {@link ControleLancamentoService#calcularSaldoDiario(LocalDate)}.
     * <ul>
     *   <li>Then return Saldo is {@link BigDecimal#BigDecimal(String)} with {@code 0.0}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ControleLancamentoService#calcularSaldoDiario(LocalDate)}
     */
    @Test
    @DisplayName("Test calcularSaldoDiario(LocalDate); then return Saldo is BigDecimal(String) with '0.0'")
    @Tag("MaintainedBy")
    void testCalcularSaldoDiario_thenReturnSaldoIsBigDecimalWith00() {
        // Arrange
        Lancamento lancamento = new Lancamento();
        lancamento.setData(LocalDate.of(1970, 1, 1));
        lancamento.setDescricao("Descricao");
        lancamento.setFormaPagamento("alice.liddell@example.org");
        lancamento.setId(1L);
        lancamento.setStatus("Status");
        lancamento.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento.setValor(new BigDecimal("2.3"));

        Lancamento lancamento2 = new Lancamento();
        lancamento2.setData(LocalDate.of(1970, 1, 1));
        lancamento2.setDescricao("com.arquiteto.solucoes.domain.model.Lancamento");
        lancamento2.setFormaPagamento("Forma Pagamento");
        lancamento2.setId(2L);
        lancamento2.setStatus("com.arquiteto.solucoes.domain.model.Lancamento");
        lancamento2.setTipoLancamento(TipoLancamento.DEBITO);
        lancamento2.setValor(new BigDecimal("2.3"));

        ArrayList<Lancamento> lancamentoList = new ArrayList<>();
        lancamentoList.add(lancamento2);
        lancamentoList.add(lancamento);
        when(lancamentoConsultaPort.buscarTodos()).thenReturn(lancamentoList);
        LocalDate data = LocalDate.of(1970, 1, 1);

        // Act
        SaldoDiario actualCalcularSaldoDiarioResult = controleLancamentoService.calcularSaldoDiario(data);

        // Assert
        verify(lancamentoConsultaPort).buscarTodos();
        BigDecimal expectedSaldo = new BigDecimal("0.0");
        assertEquals(expectedSaldo, actualCalcularSaldoDiarioResult.getSaldo());
        assertSame(data, actualCalcularSaldoDiarioResult.getData());
    }

    /**
     * Test {@link ControleLancamentoService#calcularSaldoDiario(LocalDate)}.
     * <ul>
     *   <li>Then return Saldo is {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ControleLancamentoService#calcularSaldoDiario(LocalDate)}
     */
    @Test
    @DisplayName("Test calcularSaldoDiario(LocalDate); then return Saldo is BigDecimal(String) with '2.3'")
    @Tag("MaintainedBy")
    void testCalcularSaldoDiario_thenReturnSaldoIsBigDecimalWith23() {
        // Arrange
        Lancamento lancamento = new Lancamento();
        lancamento.setData(LocalDate.of(1970, 1, 1));
        lancamento.setDescricao("Descricao");
        lancamento.setFormaPagamento("alice.liddell@example.org");
        lancamento.setId(1L);
        lancamento.setStatus("Status");
        lancamento.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento.setValor(new BigDecimal("2.3"));

        ArrayList<Lancamento> lancamentoList = new ArrayList<>();
        lancamentoList.add(lancamento);
        when(lancamentoConsultaPort.buscarTodos()).thenReturn(lancamentoList);
        LocalDate data = LocalDate.of(1970, 1, 1);

        // Act
        SaldoDiario actualCalcularSaldoDiarioResult = controleLancamentoService.calcularSaldoDiario(data);

        // Assert
        verify(lancamentoConsultaPort).buscarTodos();
        BigDecimal expectedSaldo = new BigDecimal("2.3");
        assertEquals(expectedSaldo, actualCalcularSaldoDiarioResult.getSaldo());
        assertSame(data, actualCalcularSaldoDiarioResult.getData());
    }

    /**
     * Test {@link ControleLancamentoService#fallbackCalcularSaldoDiario(LocalDate, Throwable)}.
     * <p>
     * Method under test: {@link ControleLancamentoService#fallbackCalcularSaldoDiario(LocalDate, Throwable)}
     */
    @Test
    @DisplayName("Test fallbackCalcularSaldoDiario(LocalDate, Throwable)")
    @Tag("MaintainedBy")
    void testFallbackCalcularSaldoDiario() {
        // Arrange
        LocalDate data = LocalDate.of(1970, 1, 1);

        // Act
        SaldoDiario actualFallbackCalcularSaldoDiarioResult = controleLancamentoService.fallbackCalcularSaldoDiario(data,
                new Throwable());

        // Assert
        BigDecimal expectedSaldo = new BigDecimal("0");
        assertEquals(expectedSaldo, actualFallbackCalcularSaldoDiarioResult.getSaldo());
        assertSame(data, actualFallbackCalcularSaldoDiarioResult.getData());
    }
}
