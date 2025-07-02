package com.feirarural.api.categoria.adapter.out.persistence;

import com.feirarural.api.categoria.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaJpaRepository extends JpaRepository<Categoria, Long> {}