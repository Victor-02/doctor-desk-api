package com.tcc.tccbackend.services;

import com.tcc.tccbackend.dtos.AtendenteDTO;
import com.tcc.tccbackend.models.Atendente;
import com.tcc.tccbackend.repository.AtendenteRepository;
import com.tcc.tccbackend.security.Token;
import com.tcc.tccbackend.security.TokenUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AtendenteService {

    final AtendenteRepository repository;
    final PasswordEncoder encoder;

    public AtendenteService(AtendenteRepository repository) {
        this.repository = repository;
        this.encoder = new BCryptPasswordEncoder();
    }

    public void save(Atendente atendente) {
        try {
            String encoder = this.encoder.encode(atendente.getSenha());
            atendente.setSenha(encoder);
            repository.save(atendente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validaSenha(AtendenteDTO atendente) {
        return this.encoder.matches(atendente.getSenha(), repository.findByEmail(atendente.getEmail()).getSenha());
    }

    public Token gerarToken(AtendenteDTO atendente) {
        Atendente user = repository.findByUsernameOrEmail(atendente.getUsername(), atendente.getEmail());
        boolean valid = encoder.matches(atendente.getSenha(), user.getSenha());
        return (valid ? new Token(TokenUtil.createToken(user), user.getId()) : null);
    }

    public Atendente findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}