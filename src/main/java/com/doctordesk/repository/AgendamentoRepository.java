package com.doctordesk.repository;

import com.doctordesk.dtos.AgendamentoDTO;
import com.doctordesk.models.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    @Query(value = "select * from agendamentos", nativeQuery = true)
    List<AgendamentoDTO.Agendamentos> agendamentos();
}
