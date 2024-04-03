package com.tcc.tccbackend.services;

import com.tcc.tccbackend.models.Import;
import com.tcc.tccbackend.repository.ImportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ImportService {

    final ImportRepository repository;

    public ImportService(ImportRepository repository) {
        this.repository = repository;
    }

    public void saveImport(Import importacao) {
        try {
            repository.save(importacao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Import findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Planilha não encontrado: " + id));
    }

    public Page<Import> getAll(Pageable page) {
        try {
            return repository.findAll(page);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
