package com.tcc.tccbackend.repository;

import com.tcc.tccbackend.models.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Integer> {
    public Atendente findByUsernameOrEmail(String username, String email);
    public Atendente findByEmail(String email);
}

