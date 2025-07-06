package com.feirarural.api.categoria.adapter.out.persistence;

import com.feirarural.api.categoria.domain.model.Categoria;
import com.feirarural.api.categoria.domain.port.CategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoriaJpaAdapter implements CategoriaRepository {

    private final CategoriaJpaRepository jpaRepository;

    public CategoriaJpaAdapter(CategoriaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Categoria salvar(Categoria categoria) {
        return jpaRepository.save(categoria);
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Categoria> listarTodas() {
        return jpaRepository.findAll();
    }

    @Override
    public void excluir(Categoria categoria) {
        jpaRepository.delete(categoria);
    }

    @Override
    public Categoria buscarPorIdDTO(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorIdDTO'");
    }
}
