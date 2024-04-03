package com.tcc.tccbackend.services;

import com.tcc.tccbackend.dtos.ServicoDTO;
import com.tcc.tccbackend.models.Servico;
import com.tcc.tccbackend.repository.ServicoRepository;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class ServicoService {

    final ServicoRepository repository;
    final ModelMapper mapper;

    public ServicoService(ServicoRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Servico save(Servico servico) {
        try {
            return repository.save(servico);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Servico findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Servico não encontrado: " + id));
    }

    public Page<Servico> getAllPage(Pageable page) {
        try {
            return repository.findAll(page);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Servico> getAll(){
        try {
            //return transfer(repository.findAll());
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

    private List<ServicoDTO> transfer ( List<Servico> servicos) {
        List<ServicoDTO> ServicoDTOList = new ArrayList<>();
        for(Servico servico : servicos) {
            ServicoDTO dto = mapper.map(servico, ServicoDTO.class);
            ServicoDTOList.add(dto);
        }
        return ServicoDTOList;
    }
}
