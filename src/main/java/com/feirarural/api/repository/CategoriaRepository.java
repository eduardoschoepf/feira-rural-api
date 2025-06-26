package com.feirarural.api.repository;

import com.feirarural.api.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Additional query methods can be defined here if needed

}
