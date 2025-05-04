package com.arquiteto.solucoes.adapters.in;

import static org.mockito.Mockito.when;

import com.arquiteto.solucoes.domain.model.ports.in.SaldoDiarioServicePort;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SaldoDiarioController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SaldoDiarioControllerTest {
    @Autowired
    private SaldoDiarioController saldoDiarioController;

    @MockitoBean
    private SaldoDiarioServicePort saldoDiarioServicePort;

    /**
     * Test {@link SaldoDiarioController#listarSaldos()}.
     * <p>
     * Method under test: {@link SaldoDiarioController#listarSaldos()}
     */
    @Test
    @DisplayName("Test listarSaldos()")
    @Tag("MaintainedBy")
    void testListarSaldos() throws Exception {
        // Arrange
        when(saldoDiarioServicePort.obterSaldosConsolidados()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/saldos");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(saldoDiarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
