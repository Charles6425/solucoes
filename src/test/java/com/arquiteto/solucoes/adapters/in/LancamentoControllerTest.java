package com.arquiteto.solucoes.adapters.in;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.arquiteto.solucoes.application.service.LancamentoService;
import com.arquiteto.solucoes.domain.Lancamento;
import com.arquiteto.solucoes.enums.TipoLancamento;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {LancamentoController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class LancamentoControllerTest {
    @Autowired
    private LancamentoController lancamentoController;

    @MockitoBean
    private LancamentoService lancamentoService;

    /**
     * Test {@link LancamentoController#create(Lancamento)}.
     * <p>
     * Method under test: {@link LancamentoController#create(Lancamento)}
     */
    @Test
    @DisplayName("Test create(Lancamento)")
    @Tag("MaintainedBy")
    void testCreate() throws Exception {
        // Arrange
        Lancamento lancamento = new Lancamento();
        lancamento.setData("Data");
        lancamento.setDescricao("Descricao");
        lancamento.setFormaPagamento("alice.liddell@example.org");
        lancamento.setId(1L);
        lancamento.setStatus("Status");
        lancamento.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento.setValor(10.0d);
        when(lancamentoService.save(Mockito.<Lancamento>any())).thenReturn(lancamento);

        Lancamento lancamento2 = new Lancamento();
        lancamento2.setData("Data");
        lancamento2.setDescricao("Descricao");
        lancamento2.setFormaPagamento("alice.liddell@example.org");
        lancamento2.setId(1L);
        lancamento2.setStatus("Status");
        lancamento2.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento2.setValor(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(lancamento2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/lancamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(lancamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"descricao\":\"Descricao\",\"data\":\"Data\",\"valor\":10.0,\"tipoLancamento\":\"CREDITO\",\"formaPagamento"
                                        + "\":\"alice.liddell@example.org\",\"status\":\"Status\"}"));
    }

    /**
     * Test {@link LancamentoController#findAll()}.
     * <p>
     * Method under test: {@link LancamentoController#findAll()}
     */
    @Test
    @DisplayName("Test findAll()")
    @Tag("MaintainedBy")
    void testFindAll() throws Exception {
        // Arrange
        when(lancamentoService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/lancamentos");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(lancamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link LancamentoController#findById(Long)}.
     * <p>
     * Method under test: {@link LancamentoController#findById(Long)}
     */
    @Test
    @DisplayName("Test findById(Long)")
    @Tag("MaintainedBy")
    void testFindById() throws Exception {
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
        when(lancamentoService.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/lancamentos/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(lancamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"descricao\":\"Descricao\",\"data\":\"Data\",\"valor\":10.0,\"tipoLancamento\":\"CREDITO\",\"formaPagamento"
                                        + "\":\"alice.liddell@example.org\",\"status\":\"Status\"}"));
    }

    /**
     * Test {@link LancamentoController#update(Long, Lancamento)}.
     * <ul>
     *   <li>Given {@link LancamentoService} {@link LancamentoService#findById(Long)} return empty.</li>
     *   <li>Then status {@link StatusResultMatchers#isNotFound()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LancamentoController#update(Long, Lancamento)}
     */
    @Test
    @DisplayName("Test update(Long, Lancamento); given LancamentoService findById(Long) return empty; then status isNotFound()")
    @Tag("MaintainedBy")
    void testUpdate_givenLancamentoServiceFindByIdReturnEmpty_thenStatusIsNotFound() throws Exception {
        // Arrange
        Lancamento lancamento = new Lancamento();
        lancamento.setData("Data");
        lancamento.setDescricao("Descricao");
        lancamento.setFormaPagamento("alice.liddell@example.org");
        lancamento.setId(1L);
        lancamento.setStatus("Status");
        lancamento.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento.setValor(10.0d);
        when(lancamentoService.save(Mockito.<Lancamento>any())).thenReturn(lancamento);
        Optional<Lancamento> emptyResult = Optional.empty();
        when(lancamentoService.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Lancamento lancamento2 = new Lancamento();
        lancamento2.setData("Data");
        lancamento2.setDescricao("Descricao");
        lancamento2.setFormaPagamento("alice.liddell@example.org");
        lancamento2.setId(1L);
        lancamento2.setStatus("Status");
        lancamento2.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento2.setValor(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(lancamento2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/lancamentos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(lancamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Test {@link LancamentoController#update(Long, Lancamento)}.
     * <ul>
     *   <li>Then status {@link StatusResultMatchers#isOk()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link LancamentoController#update(Long, Lancamento)}
     */
    @Test
    @DisplayName("Test update(Long, Lancamento); then status isOk()")
    @Tag("MaintainedBy")
    void testUpdate_thenStatusIsOk() throws Exception {
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

        Lancamento lancamento2 = new Lancamento();
        lancamento2.setData("Data");
        lancamento2.setDescricao("Descricao");
        lancamento2.setFormaPagamento("alice.liddell@example.org");
        lancamento2.setId(1L);
        lancamento2.setStatus("Status");
        lancamento2.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento2.setValor(10.0d);
        when(lancamentoService.save(Mockito.<Lancamento>any())).thenReturn(lancamento2);
        when(lancamentoService.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Lancamento lancamento3 = new Lancamento();
        lancamento3.setData("Data");
        lancamento3.setDescricao("Descricao");
        lancamento3.setFormaPagamento("alice.liddell@example.org");
        lancamento3.setId(1L);
        lancamento3.setStatus("Status");
        lancamento3.setTipoLancamento(TipoLancamento.CREDITO);
        lancamento3.setValor(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(lancamento3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/lancamentos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(lancamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"descricao\":\"Descricao\",\"data\":\"Data\",\"valor\":10.0,\"tipoLancamento\":\"CREDITO\",\"formaPagamento"
                                        + "\":\"alice.liddell@example.org\",\"status\":\"Status\"}"));
    }

    /**
     * Test {@link LancamentoController#delete(Long)}.
     * <p>
     * Method under test: {@link LancamentoController#delete(Long)}
     */
    @Test
    @DisplayName("Test delete(Long)")
    @Tag("MaintainedBy")
    void testDelete() throws Exception {
        // Arrange
        doNothing().when(lancamentoService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/lancamentos/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(lancamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
