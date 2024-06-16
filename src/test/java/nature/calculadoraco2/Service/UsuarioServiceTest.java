package nature.calculadoraco2.Service;

import nature.calculadoraco2.Model.Usuario;
import nature.calculadoraco2.Repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsuarios() {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Usuario 1");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Usuario 2");

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        assertEquals(2, usuarios.size());
        assertEquals("Usuario 1", usuarios.get(0).getNome());
        assertEquals("Usuario 2", usuarios.get(1).getNome());
    }

    @Test
    void testGetUsuarioById() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Usuario");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario found = usuarioService.getUsuarioById(1L);

        assertEquals(1L, found.getId());
        assertEquals("Usuario", found.getNome());
    }
}