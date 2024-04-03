package com.tcc.tccbackend.controllers;

import com.tcc.tccbackend.models.Profissional;
import com.tcc.tccbackend.services.ProfissionalService;
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
@RequestMapping("/api/profissionais")
public class ProfissionalController {
    final ProfissionalService service;
    private final Logger logger = LoggerFactory.getLogger(ProfissionalController.class);

    public ProfissionalController(ProfissionalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Profissional> insert(@Valid @RequestBody Profissional profissional) {
        profissional = service.save(profissional);
        logger.info("Efetuando insercão de Profissional");
        return ResponseEntity.status(HttpStatus.CREATED).body(profissional);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> getByID(@PathVariable Integer id) {
        Profissional profissional = service.findById(id);
        return ResponseEntity.ok().body(profissional);
    }

    @GetMapping
    public ResponseEntity<Page<Profissional>> getAllPage(Pageable page) {
        Page<Profissional> profissionais = service.getAllPage(page);
        return ResponseEntity.ok().body(profissionais);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Profissional>> getAll() {
        List<Profissional> profissionais = service.getAll();
        return ResponseEntity.ok().body(profissionais);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
