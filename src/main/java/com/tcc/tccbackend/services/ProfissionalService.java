package com.tcc.tccbackend.services;

import com.tcc.tccbackend.models.Profissional;
import com.tcc.tccbackend.repository.ProfissionalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class ProfissionalService {

    final
    ProfissionalRepository repository;

    public ProfissionalService(ProfissionalRepository repository) {
        this.repository = repository;
    }

    public Profissional save(Profissional profissional) {
        try {
            return repository.save(profissional);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Profissional findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado: " + id));
    }

    public Page<Profissional> getAllPage(Pageable page) {
        try {
            return repository.findAll(page);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Profissional> getAll(){
        try {
            return repository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteById(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
