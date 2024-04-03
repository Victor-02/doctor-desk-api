package com.tcc.tccbackend.controllers.docs;

import com.tcc.tccbackend.dtos.PacienteDTO;
import com.tcc.tccbackend.models.Paciente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api("Gerenciamento de Pacientes")
public interface PacienteControllerDocs {

    @ApiOperation(value = "Inserir um paciente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso na insercao de paciente"),
            @ApiResponse(code = 400, message = "Faltando valores obrigatorios."),
            @ApiResponse(code = 401, message = "Não Autenticado.")
    })
    ResponseEntity<PacienteDTO> insert(PacienteDTO pacienteDTO);

    @ApiOperation(value = "Receber um paciente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso"),
            @ApiResponse(code = 404, message = "Paciente não existe."),
            @ApiResponse(code = 401, message = "Não Autenticado.")
    })
    ResponseEntity<PacienteDTO> getByID(Integer id);

    @ApiOperation(value = "Receber todos os pacientes em página")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso"),
            @ApiResponse(code = 401, message = "Não Autenticado.")
    })
    ResponseEntity<Page<PacienteDTO>> getAllPage(Pageable page);

    @ApiOperation(value = "Receber todos os pacientes em página")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso"),
            @ApiResponse(code = 401, message = "Não Autenticado.")
    })
    ResponseEntity<List<Paciente>> getAll();

    @ApiOperation(value = "Pesquisar paciente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso"),
            @ApiResponse(code = 401, message = "Não Autenticado.")
    })
    ResponseEntity<List<Paciente>> search(String query);

    @ApiOperation(value = "Atualizar um paciente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso"),
            @ApiResponse(code = 400, message = "Faltando valores obrigatorios."),
            @ApiResponse(code = 404, message = "Paciente nâo encontrado."),
            @ApiResponse(code = 401, message = "Não Autenticado.")
    })
    ResponseEntity<Object> update(Paciente paciente, Integer id);

    @ApiOperation(value = "Excluir um paciente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucesso na exclusao de paciente"),
            @ApiResponse(code = 401, message = "Não Autenticado.")
    })
    ResponseEntity<Object> delete(Integer id);
}
