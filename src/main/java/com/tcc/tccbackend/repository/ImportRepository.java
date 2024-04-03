package com.tcc.tccbackend.repository;

import com.tcc.tccbackend.models.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportRepository extends JpaRepository<Import, Integer> {
}
