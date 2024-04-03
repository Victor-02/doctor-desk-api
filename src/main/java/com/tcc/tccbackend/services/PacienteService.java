package com.tcc.tccbackend.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tcc.tccbackend.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.tccbackend.dtos.PacienteDTO;
import com.tcc.tccbackend.models.Paciente;
import com.tcc.tccbackend.repository.PacienteRepository;
import com.tcc.tccbackend.utils.ConverterToData;

import javax.persistence.EntityNotFoundException;

@Service
public class PacienteService {

    final PacienteRepository repository;
    final ModelMapper mapper;

    public PacienteService(PacienteRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PacienteDTO save(PacienteDTO paciente) {
        try {
            return mapper.map(repository.save(mapper.map(paciente, Paciente.class)), PacienteDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveAll(List<Paciente> pacientes) {
        try {
            repository.saveAll(pacientes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PacienteDTO findById(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado: " + id)), PacienteDTO.class);
    }

    public List<Paciente> searchPacientes(String query) {
        return repository.findByCpfContaining(query);
    }

    public Page<PacienteDTO> getAllPage(Pageable page) {
        try {
            Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize());
            List<PacienteDTO> pacientes = transfer(repository.findAllByOrderByNome());
            Page<PacienteDTO> pacientesPage = toPage(pacientes, pageable);
            return pacientesPage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Paciente> getAll() {
        try {
            return repository.findAll();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Paciente update(Integer id, Paciente pacienteAtt) {
        try {
            Paciente paciente = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado: " + id));
            updateData(paciente, pacienteAtt);
            return repository.save(paciente);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteById(Integer id) {
        try {
            repository.deleteById(id);
            return "deletado com sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "erro ao deletar";
        }
    }

    private void updateData(Paciente paciente, Paciente pacienteAtt) {
        paciente.setNome(pacienteAtt.getNome());
        paciente.setEmail(pacienteAtt.getEmail());
        paciente.setCpf(pacienteAtt.getCpf());
        paciente.setTelefone(pacienteAtt.getTelefone());
    }

    

    private Page toPage(List<PacienteDTO> list, Pageable pageable){
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        Page<Paciente> page = new PageImpl(list.subList(start, end), pageable, list.size());
        return page;
    }

    private List<PacienteDTO> transfer ( List<Paciente> pacientes) {
        List<PacienteDTO> PacienteDTOList = new ArrayList<>();
        for(Paciente paciente : pacientes) {
            PacienteDTO dto = mapper.map(paciente, PacienteDTO.class);
            if (paciente.getDataNascimento() != null)
                dto.setDataNascimento(Utils.toDate(paciente.getDataNascimento()));
            PacienteDTOList.add(dto);
        }
        return PacienteDTOList;
    }

    public List<Paciente> converter(MultipartFile file) throws IOException{
        return ConverterToData.converteArquivo(file);
    }
}