package com.feirarural.api.produto.adapter.out.persistence;

import com.feirarural.api.produto.domain.port.ProdutoRepository;
import com.feirarural.api.produto.domain.model.Produto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import com.feirarural.api.produto.adapter.out.persistence.ProdutoEntity;
import com.feirarural.api.produto.adapter.out.persistence.ProdutoJpaRepository;

@Component
public class ProdutoJpaAdapter implements ProdutoRepository {

    private final ProdutoJpaRepository jpaRepository;

    public ProdutoJpaAdapter(ProdutoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoEntity entity = ProdutoEntity.fromDomain(produto);
        ProdutoEntity saved = jpaRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(ProdutoEntity::toDomain);
    }

    @Override
    public List<Produto> listarTodos() {
        return jpaRepository.findAll().stream()
                .map(ProdutoEntity::toDomain)
                .toList();
    }

    @Override
    public void excluir(Produto produto) {
        ProdutoEntity entity = ProdutoEntity.fromDomain(produto);
        jpaRepository.delete(entity);
    }

    @Override
    public Produto buscarPorIdDTO(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorIdDTO'");
    }

}
