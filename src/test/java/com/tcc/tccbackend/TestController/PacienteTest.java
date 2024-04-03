package com.tcc.tccbackend.TestController;

import com.tcc.tccbackend.builder.PacienteDTOBuilder;
import com.tcc.tccbackend.controllers.PacienteController;
import com.tcc.tccbackend.dtos.PacienteDTO;
import com.tcc.tccbackend.services.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static com.tcc.tccbackend.util.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PacienteTest extends BaseTest {

    private static final String API_URL_PATH = "/api/pacientes/";

    @Mock
    private PacienteService pacienteService;

    private MockMvc mockMvc;

    @InjectMocks
    private PacienteController pacienteController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(pacienteController).build();
    }

    @Test
    @DisplayName("Retorna sucesso quando busca todos os pacientes")
    void t1() throws Exception {

        when(pacienteService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get(API_URL_PATH + "all")
                        .header("Authorization", getJWT())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Retorna sucesso quando busca um paciente ")
    void t2() throws Exception {
        PacienteDTO pacienteDTO = PacienteDTOBuilder.builder().build().toPacienteDTO();

        when(pacienteService.findById(pacienteDTO.getId())).thenReturn(pacienteDTO);

        mockMvc.perform(get(API_URL_PATH + pacienteDTO.getId())
                        .header("Authorization", getJWT())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pacienteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(pacienteDTO.getNome())))
                .andExpect(jsonPath("$.email", is(pacienteDTO.getEmail())))
                .andExpect(jsonPath("$.cpf", is(pacienteDTO.getCpf())))
                .andExpect(jsonPath("$.telefone", is(pacienteDTO.getTelefone())))
                .andExpect(jsonPath("$.dataNascimento", is(pacienteDTO.getDataNascimento())));
    }

    @Test
    @DisplayName("Retorna sucesso quando cadastrar um paciente")
    void t3() throws Exception {
        PacienteDTO pacienteDTO = PacienteDTOBuilder.builder().build().toPacienteDTO();

        when(pacienteService.save(pacienteDTO)).thenReturn(pacienteDTO);

        mockMvc.perform(post(API_URL_PATH)
                        .header("Authorization", getJWT())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pacienteDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is(pacienteDTO.getNome())))
                .andExpect(jsonPath("$.email", is(pacienteDTO.getEmail())))
                .andExpect(jsonPath("$.cpf", is(pacienteDTO.getCpf())))
                .andExpect(jsonPath("$.telefone", is(pacienteDTO.getTelefone())))
                .andExpect(jsonPath("$.dataNascimento", is(pacienteDTO.getDataNascimento())));
    }

    @Test
    @DisplayName("Retorna sucesso ao editar um paciente")
    void t4() throws Exception {
        PacienteDTO pacienteDTO = PacienteDTOBuilder.builder().build().toPacienteDTO();

        when(pacienteService.save(pacienteDTO)).thenReturn(pacienteDTO);
        pacienteDTO.setNome("Nome alterado");
        pacienteDTO.setEmail("Email Alterado");
        pacienteDTO.setCpf("Cpf Alterado");
        pacienteDTO.setTelefone("Telefone Alterado");
        when(pacienteService.save(pacienteDTO)).thenReturn(pacienteDTO);

        mockMvc.perform(put(API_URL_PATH + pacienteDTO.getId())
                        .header("Authorization", getJWT())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pacienteDTO)))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Retorna sucesso ao excluir um paciente")
    void t5() throws Exception {
        PacienteDTO pacienteDTO = PacienteDTOBuilder.builder().build().toPacienteDTO();

        when(pacienteService.deleteById(pacienteDTO.getId())).thenReturn("deletado com sucesso");

        mockMvc.perform(delete(API_URL_PATH + pacienteDTO.getId())
                        .header("Authorization", getJWT())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pacienteDTO)))
                .andExpect(status().isNoContent());
    }
}
