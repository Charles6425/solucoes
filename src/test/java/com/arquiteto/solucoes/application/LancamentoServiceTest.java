package com.arquiteto.solucoes.application;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.arquiteto.solucoes.domain.Lancamento;
import com.arquiteto.solucoes.enums.TipoLancamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LancamentoService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class LancamentoServiceTest {
    @MockitoBean
    private com.arquiteto.solucoes.application.port.out.LancamentoRepositoryPort lancamentoRepositoryPort;

    @Autowired
    private LancamentoService lancamentoService;

    /**
     * Test {@link LancamentoService#save(Lancamento)}.
     * <p>
     * Method under test: {@link LancamentoService#save(Lancamento)}
     */
    @Test
    @DisplayName("Test save(Lancamento)")
    @Tag("MaintainedBy")
    void testSave() {
        // Arrange
        Lancamento lancamento = new Lancamento();
        lancamento.setData("Data");
        lancamento.setDescricao("Descricao");
        lancamento.setFormaPagamento("alice.liddell@example.org");
        lancamento.setId(1L);
        lancamento.setStatus("Status");
        lancamento.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento.setValor(10.0d);
        when(lancamentoRepositoryPort.save(Mockito.<Lancamento>any())).thenReturn(lancamento);

        Lancamento lancamento2 = new Lancamento();
        lancamento2.setData("Data");
        lancamento2.setDescricao("Descricao");
        lancamento2.setFormaPagamento("alice.liddell@example.org");
        lancamento2.setId(1L);
        lancamento2.setStatus("Status");
        lancamento2.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento2.setValor(10.0d);

        // Act
        Lancamento actualSaveResult = lancamentoService.save(lancamento2);

        // Assert
        verify(lancamentoRepositoryPort).save(isA(Lancamento.class));
        assertSame(lancamento, actualSaveResult);
    }

    /**
     * Test {@link LancamentoService#findAll()}.
     * <p>
     * Method under test: {@link LancamentoService#findAll()}
     */
    @Test
    @DisplayName("Test findAll()")
    @Tag("MaintainedBy")
    void testFindAll() {
        // Arrange
        when(lancamentoRepositoryPort.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Lancamento> actualFindAllResult = lancamentoService.findAll();

        // Assert
        verify(lancamentoRepositoryPort).findAll();
        assertTrue(actualFindAllResult.isEmpty());
    }

    /**
     * Test {@link LancamentoService#findById(Long)}.
     * <p>
     * Method under test: {@link LancamentoService#findById(Long)}
     */
    @Test
    @DisplayName("Test findById(Long)")
    @Tag("MaintainedBy")
    void testFindById() {
        // Arrange
        Lancamento lancamento = new Lancamento();
        lancamento.setData("Data");
        lancamento.setDescricao("Descricao");
        lancamento.setFormaPagamento("alice.liddell@example.org");
        lancamento.setId(1L);
        lancamento.setStatus("Status");
        lancamento.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento.setValor(10.0d);
        Optional<Lancamento> ofResult = Optional.of(lancamento);
        when(lancamentoRepositoryPort.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Optional<Lancamento> actualFindByIdResult = lancamentoService.findById(1L);

        // Assert
        verify(lancamentoRepositoryPort).findById(eq(1L));
        assertSame(ofResult, actualFindByIdResult);
    }

    /**
     * Test {@link LancamentoService#delete(Long)}.
     * <p>
     * Method under test: {@link LancamentoService#delete(Long)}
     */
    @Test
    @DisplayName("Test delete(Long)")
    @Tag("MaintainedBy")
    void testDelete() {
        // Arrange
        doNothing().when(lancamentoRepositoryPort).deleteById(Mockito.<Long>any());

        // Act
        lancamentoService.delete(1L);

        // Assert
        verify(lancamentoRepositoryPort).deleteById(eq(1L));
    }
}
