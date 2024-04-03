package com.tcc.tccbackend.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcc.tccbackend.controllers.docs.PacienteControllerDocs;
import com.tcc.tccbackend.dtos.PacienteDTO;
import com.tcc.tccbackend.models.Paciente;
import com.tcc.tccbackend.services.PacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@JsonFormat(pattern = "dd/MM/yyyy")
public class PacienteController implements PacienteControllerDocs {

    final PacienteService service;
    final ModelMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(PacienteController.class);

    public PacienteController(PacienteService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> insert(@Valid @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO pacienteDto = service.save(pacienteDTO);
        logger.info("Efetuando insercao de paciente");
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getByID(@PathVariable Integer id) {
        PacienteDTO pacienteDTO = service.findById(id);
        logger.info("Efetuando busca por ID do paciente: " + pacienteDTO.getId());
        return ResponseEntity.ok().body(pacienteDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PacienteDTO>> getAllPage(Pageable page) {
        Page<PacienteDTO> pacientes = service.getAllPage(page);
        return ResponseEntity.ok().body(pacientes);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Paciente>> getAll() {
        List<Paciente> profissionais = service.getAll();
        return ResponseEntity.ok().body(profissionais);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Paciente>> search(@RequestParam("key") String query) {
        logger.info("Efetuando busca por CPF do paciente: " + query);
        return ResponseEntity.ok(service.searchPacientes(query));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody Paciente paciente, @PathVariable Integer id) {
        paciente = service.update(id, paciente);
        return ResponseEntity.ok().body(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}