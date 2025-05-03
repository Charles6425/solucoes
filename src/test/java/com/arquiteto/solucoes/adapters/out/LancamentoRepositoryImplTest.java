package com.arquiteto.solucoes.adapters.out;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.arquiteto.solucoes.domain.Lancamento;
import com.arquiteto.solucoes.enums.TipoLancamento;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LancamentoRepositoryImpl.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class LancamentoRepositoryImplTest {
    @Autowired
    private LancamentoRepositoryImpl lancamentoRepositoryImpl;

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

        // Act
        Lancamento actualSaveResult = lancamentoRepositoryImpl.save(lancamento);

        // Assert
        List<Lancamento> findAllResult = lancamentoRepositoryImpl.findAll();
        assertEquals(1, findAllResult.size());
        assertSame(lancamento, actualSaveResult);
        assertSame(lancamento, findAllResult.get(0));
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
        // Arrange, Act and Assert
        assertTrue((new LancamentoRepositoryImpl()).findAll().isEmpty());
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
        // Arrange, Act and Assert
        assertFalse(lancamentoRepositoryImpl.findById(1L).isPresent());
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
        lancamentoRepositoryImpl.deleteById(1L);
    }
}
