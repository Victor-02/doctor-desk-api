package com.tcc.tccbackend.controllers;

import com.tcc.tccbackend.models.Servico;
import com.tcc.tccbackend.services.ServicoService;
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
@RequestMapping("/api/servicos")
public class ServicoController {

    final ServicoService service;
    private final Logger logger = LoggerFactory.getLogger(ServicoController.class);

    public ServicoController(ServicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Servico> insert(@Valid @RequestBody Servico servico) {
        servico = service.save(servico);
        logger.info("Efetuando insercão de Servico");
        return ResponseEntity.status(HttpStatus.CREATED).body(servico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> getByID(@PathVariable Integer id) {
        Servico servico = service.findById(id);
        return ResponseEntity.ok().body(servico);
    }

    @GetMapping
    public ResponseEntity<Page<Servico>> getAllPage(Pageable page) {
        Page<Servico> servicos = service.getAllPage(page);
        return ResponseEntity.ok().body(servicos);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Servico>> getAll() {
        List<Servico> servicos = service.getAll();
        return ResponseEntity.ok().body(servicos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
