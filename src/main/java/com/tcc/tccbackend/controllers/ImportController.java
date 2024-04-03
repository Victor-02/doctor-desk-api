package com.tcc.tccbackend.controllers;

import com.tcc.tccbackend.models.Import;
import com.tcc.tccbackend.models.Paciente;
import com.tcc.tccbackend.services.ImportService;
import com.tcc.tccbackend.services.PacienteService;
import com.tcc.tccbackend.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/importacao", produces = {"application/json"})
@CrossOrigin("*")
public class ImportController {

    final PacienteService pacienteService;
    final ImportService importService;
    private final Logger logger = LoggerFactory.getLogger(ImportController.class);

    public ImportController(PacienteService pacienteService, ImportService importService) {
        this.pacienteService = pacienteService;
        this.importService = importService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> salvarArquivo(@RequestParam("file") MultipartFile file) {
        String path = UUID.randomUUID() + "." + Utils.extrairExtensao(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            List<Paciente> pacientes = pacienteService.converter(file);
            pacienteService.saveAll(pacientes);
            Files.copy(file.getInputStream(), Path.of(path), StandardCopyOption.REPLACE_EXISTING);

            Import importacao = new Import(file.getOriginalFilename(), Utils.formatter(LocalDate.now()));
            importService.saveImport(importacao);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Import>> getAll(Pageable page) {
        Page<Import> imports = importService.getAll(page);
        return ResponseEntity.ok().body(imports);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Import> findById(@PathVariable Integer id) {
        Import importacao = importService.findById(id);
        return ResponseEntity.ok().body(importacao);
    }
}