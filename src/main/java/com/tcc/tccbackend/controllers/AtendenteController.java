package com.tcc.tccbackend.controllers;

import com.tcc.tccbackend.dtos.AtendenteDTO;
import com.tcc.tccbackend.models.Atendente;
import com.tcc.tccbackend.security.Token;
import com.tcc.tccbackend.services.AtendenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user", produces = {"application/json"})
@CrossOrigin("*")
public class AtendenteController {

    final AtendenteService atendenteService;
    private final Logger logger = LoggerFactory.getLogger(AtendenteController.class);

    public AtendenteController(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> salvarAtendente(@RequestBody Atendente atendente) {
        try {
            atendenteService.save(atendente);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Token> loginAtendente(@Valid @RequestBody AtendenteDTO atendente) {
        if (atendenteService.validaSenha(atendente)) {
            Token token = atendenteService.gerarToken(atendente);
            return (token != null ? ResponseEntity.ok().body(token) : new ResponseEntity<>(HttpStatus.FORBIDDEN));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendente> getUser(@PathVariable Integer id) {
        Atendente atendente = atendenteService.findById(id);
        return (atendente != null ? ResponseEntity.ok().body(atendente) : new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}