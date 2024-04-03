package com.tcc.tccbackend.services;


import com.tcc.tccbackend.dtos.AgendamentoDTO;
import com.tcc.tccbackend.dtos.PacienteDTO;
import com.tcc.tccbackend.exceptions.AgendamentoException;
import com.tcc.tccbackend.exceptions.HorarioIgualException;
import com.tcc.tccbackend.models.Agendamento;
import com.tcc.tccbackend.models.Paciente;
import com.tcc.tccbackend.repository.AgendamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tcc.tccbackend.utils.Utils.formatterDataHora;
import static com.tcc.tccbackend.utils.Utils.verificaHorario;

@Service
public class AgendamentoService {
    final AgendamentoRepository repository;
    final ModelMapper mapper;

    public AgendamentoService(AgendamentoRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Agendamento save(Agendamento agendamento) {
        try {
            if (verificaHorario(transferToAgendamento(repository.agendamentos()), agendamento)) {
                throw new HorarioIgualException("Horário já agendado");
            }
            return repository.save(agendamento);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AgendamentoDTO findById(Integer id) {
        return mapper.map(repository.findById(id), AgendamentoDTO.class);
    }

    public void deleteById(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Page<AgendamentoDTO> toPage(List<AgendamentoDTO> list, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min(startIndex + pageSize, list.size());
        List<AgendamentoDTO> content = list.stream().skip(startIndex).limit(endIndex - startIndex).collect(Collectors.toList());
        return new PageImpl<>(content, pageable, list.size());
    }

    public Page<AgendamentoDTO> getAll(Pageable page) throws AgendamentoException {
        try {
            Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize());
            return toPage(transfer(repository.agendamentos()), pageable);
        } catch (Exception e) {
            throw new AgendamentoException("Erro ao obter agendamentos", e);
        }
    }

    private List<AgendamentoDTO> transfer(List<AgendamentoDTO.Agendamentos> agendamentos) throws ParseException {
        List<AgendamentoDTO> AgendamentoDTOList = new ArrayList<>();
        for (AgendamentoDTO.Agendamentos agendamento : agendamentos) {
            AgendamentoDTO dto = mapper.map(agendamento, AgendamentoDTO.class);
            if (dto.getData() != null) dto.setData((formatterDataHora(dto.getData().toString())));
            AgendamentoDTOList.add(dto);
        }
        return AgendamentoDTOList;
    }

    private List<Agendamento> transferToAgendamento(List<AgendamentoDTO.Agendamentos> agendamentos) {
        List<Agendamento> AgendamentoList = new ArrayList<>();
        for (AgendamentoDTO.Agendamentos agendamento : agendamentos) {
            Agendamento dto = mapper.map(agendamento, Agendamento.class);
            AgendamentoList.add(dto);
        }
        return AgendamentoList;
    }
}
