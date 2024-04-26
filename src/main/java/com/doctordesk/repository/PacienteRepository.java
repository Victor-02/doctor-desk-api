package com.doctordesk.repository;

import com.doctordesk.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    List<Paciente> findByCpfContaining(String nome);
    List<Paciente> findAllByOrderByNome();
}