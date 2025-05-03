package com.arquiteto.solucoes.adapters.out;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.arquiteto.solucoes.domain.model.Lancamento;
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

@ContextConfiguration(classes = {LancamentoRepositoryImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class LancamentoRepositoryImplTest {
    @Autowired
    private LancamentoRepositoryImpl lancamentoRepositoryImpl;

    @MockitoBean
    private com.arquiteto.solucoes.adapters.out.SpringDataLancamentoRepository springDataLancamentoRepository;

    /**
     * Test {@link LancamentoRepositoryImpl#save(Lancamento)}.
     * <p>
     * Method under test: {@link LancamentoRepositoryImpl#save(Lancamento)}
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
        when(springDataLancamentoRepository.save(Mockito.<Lancamento>any())).thenReturn(lancamento);

        Lancamento lancamento2 = new Lancamento();
        lancamento2.setData("Data");
        lancamento2.setDescricao("Descricao");
        lancamento2.setFormaPagamento("alice.liddell@example.org");
        lancamento2.setId(1L);
        lancamento2.setStatus("Status");
        lancamento2.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento2.setValor(10.0d);

        // Act
        Lancamento actualSaveResult = lancamentoRepositoryImpl.save(lancamento2);

        // Assert
        verify(springDataLancamentoRepository).save(isA(Lancamento.class));
        assertSame(lancamento, actualSaveResult);
    }

    /**
     * Test {@link LancamentoRepositoryImpl#findAll()}.
     * <p>
     * Method under test: {@link LancamentoRepositoryImpl#findAll()}
     */
    @Test
    @DisplayName("Test findAll()")
    @Tag("MaintainedBy")
    void testFindAll() {
        // Arrange
        when(springDataLancamentoRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Lancamento> actualFindAllResult = lancamentoRepositoryImpl.findAll();

        // Assert
        verify(springDataLancamentoRepository).findAll();
        assertTrue(actualFindAllResult.isEmpty());
    }

    /**
     * Test {@link LancamentoRepositoryImpl#findById(Long)}.
     * <p>
     * Method under test: {@link LancamentoRepositoryImpl#findById(Long)}
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
        when(springDataLancamentoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Optional<Lancamento> actualFindByIdResult = lancamentoRepositoryImpl.findById(1L);

        // Assert
        verify(springDataLancamentoRepository).findById(eq(1L));
        assertSame(ofResult, actualFindByIdResult);
    }

    /**
     * Test {@link LancamentoRepositoryImpl#deleteById(Long)}.
     * <p>
     * Method under test: {@link LancamentoRepositoryImpl#deleteById(Long)}
     */
    @Test
    @DisplayName("Test deleteById(Long)")
    @Tag("MaintainedBy")
    void testDeleteById() {
        // Arrange
        doNothing().when(springDataLancamentoRepository).deleteById(Mockito.<Long>any());

        // Act
        lancamentoRepositoryImpl.deleteById(1L);

        // Assert
        verify(springDataLancamentoRepository).deleteById(eq(1L));
    }
}
