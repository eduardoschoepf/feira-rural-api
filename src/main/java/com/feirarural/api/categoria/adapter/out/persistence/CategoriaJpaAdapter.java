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
        CategoriaEntity entity = CategoriaEntity.fromDomain(categoria);
        CategoriaEntity saved = jpaRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(CategoriaEntity::toDomain);
    }

    @Override
    public List<Categoria> listarTodas() {
        return jpaRepository.findAll().stream()
                .map(CategoriaEntity::toDomain)
                .toList();
    }

    @Override
    public void excluir(Categoria categoria) {
        CategoriaEntity entity = CategoriaEntity.fromDomain(categoria);
        jpaRepository.delete(entity);
    }

    @Override
    public Categoria buscarPorIdDTO(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorIdDTO'");
    }
}
