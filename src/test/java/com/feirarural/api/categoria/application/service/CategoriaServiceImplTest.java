package com.feirarural.api.categoria.application.service;

import com.feirarural.api.categoria.domain.model.Categoria;
import com.feirarural.api.categoria.domain.port.CategoriaRepository;
import com.feirarural.api.categoria.dto.CategoriaRequest;
import com.feirarural.api.categoria.dto.CategoriaResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoriaServiceImplTest {

    private CategoriaRepository repository;
    private CategoriaServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(CategoriaRepository.class);
        service = new CategoriaServiceImpl(repository);
    }

    @Test
    void deveSalvarCategoriaComSucesso() {
        CategoriaRequest request = new CategoriaRequest("Frutas", "Categoria de frutas");
        Categoria salva = new Categoria(1L, "Frutas", "Categoria de frutas");

        when(repository.salvar(any(Categoria.class))).thenReturn(salva);

        CategoriaResponse response = service.salvar(request);

        assertNotNull(response);
        assertEquals("Frutas", response.nome());
        assertEquals(1L, response.id());
    }

    @Test
    void deveListarTodasCategorias() {
        List<Categoria> categorias = List.of(
                new Categoria(1L, "Verduras", "Verdes"),
                new Categoria(2L, "Carnes", "Frescas")
        );

        when(repository.listarTodas()).thenReturn(categorias);

        List<CategoriaResponse> respostas = service.listarTodas();

        assertEquals(2, respostas.size());
        assertEquals("Verduras", respostas.get(0).nome());
    }

    @Test
    void deveBuscarCategoriaPorId() {
        Categoria categoria = new Categoria(1L, "Legumes", "Frescos");
        when(repository.buscarPorId(1L)).thenReturn(Optional.of(categoria));

        CategoriaResponse response = service.buscarPorIdDTO(1L);

        assertEquals("Legumes", response.nome());
    }

    @Test
    void deveLancarExcecaoAoBuscarCategoriaInexistente() {
        when(repository.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.buscarPorIdDTO(99L));
    }

    @Test
    void deveAtualizarCategoria() {
        Categoria existente = new Categoria(1L, "Legumes", "Antiga");
        Categoria atualizada = new Categoria(1L, "Legumes", "Atualizada");
        CategoriaRequest request = new CategoriaRequest("Legumes", "Atualizada");

        when(repository.buscarPorId(1L)).thenReturn(Optional.of(existente));
        when(repository.salvar(any(Categoria.class))).thenReturn(atualizada);

        CategoriaResponse response = service.atualizar(1L, request);

        assertEquals("Atualizada", response.descricao());
    }

    @Test
    void deveExcluirCategoria() {
        Categoria categoria = new Categoria(1L, "Grãos", "Descrição");

        when(repository.buscarPorId(1L)).thenReturn(Optional.of(categoria));

        service.excluir(1L);

        verify(repository, times(1)).excluir(categoria);
    }

    @Test
    void deveLancarExcecaoAoExcluirCategoriaInexistente() {
        when(repository.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.excluir(99L));
    }
}
