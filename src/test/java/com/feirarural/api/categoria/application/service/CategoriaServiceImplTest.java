package com.feirarural.api.categoria.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.feirarural.api.categoria.domain.model.Categoria;
import com.feirarural.api.categoria.domain.port.out.CategoriaRepository;
import com.feirarural.api.categoria.application.service.CategoriaServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class CategoriaServiceImplTest {

    @Mock
    private CategoriaRepository repository;

    @InjectMocks
    private CategoriaServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodas_deveRetornarListaCategorias() {
        List<Categoria> mockCategorias = List.of(
            new Categoria(1L, "Frutas", "Frutas frescas"),
            new Categoria(2L, "Verduras", "Verduras orgânicas")
        );

        when(repository.listarTodas()).thenReturn(mockCategorias);

        List<Categoria> resultado = service.listarTodas();

        assertEquals(2, resultado.size());
        assertEquals("Frutas", resultado.get(0).getNome());
        verify(repository).listarTodas();
    }

    @Test
    void salvar_deveChamarRepositoryESalvarCategoria() {
        Categoria categoriaEntrada = new Categoria(null, "Cereais", "Diversos cereais");
        Categoria categoriaSalva = new Categoria(3L, "Cereais", "Diversos cereais");

        when(repository.salvar(categoriaEntrada)).thenReturn(categoriaSalva);

        Categoria resultado = service.salvar(categoriaEntrada);

        assertNotNull(resultado.getId());
        assertEquals("Cereais", resultado.getNome());
        verify(repository).salvar(categoriaEntrada);
    }

    @Test
    void buscarPorId_quandoExiste_deveRetornarCategoria() {
        Categoria categoria = new Categoria(1L, "Legumes", "Legumes variados");

        when(repository.buscarPorId(1L)).thenReturn(Optional.of(categoria));

        Categoria resultado = service.buscarPorId(1L);

        assertEquals("Legumes", resultado.getNome());
        verify(repository).buscarPorId(1L);
    }

    @Test
    void buscarPorId_quandoNaoExiste_deveLancarExcecao() {
        when(repository.buscarPorId(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.buscarPorId(1L);
        });

        assertEquals("Categoria não encontrada", exception.getMessage());
        verify(repository).buscarPorId(1L);
    }

    @Test
    void atualizar_deveAtualizarCategoriaExistente() {
        Categoria categoriaExistente = new Categoria(1L, "Antigo", "Descrição antiga");
        Categoria categoriaAtualizada = new Categoria(null, "Novo", "Descrição nova");

        when(repository.buscarPorId(1L)).thenReturn(Optional.of(categoriaExistente));
        when(repository.salvar(any(Categoria.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Categoria resultado = service.atualizar(1L, categoriaAtualizada);

        assertEquals("Novo", resultado.getNome());
        assertEquals("Descrição nova", resultado.getDescricao());
        verify(repository).buscarPorId(1L);
        verify(repository).salvar(categoriaExistente);
    }

    @Test
    void excluir_deveChamarRepositoryExcluir() {
        Categoria categoria = new Categoria(1L, "Legumes", "Legumes variados");

        when(repository.buscarPorId(1L)).thenReturn(Optional.of(categoria));
        doNothing().when(repository).excluir(categoria);

        service.excluir(1L);

        verify(repository).buscarPorId(1L);
        verify(repository).excluir(categoria);
    }
}
