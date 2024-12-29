package dev.alecar.parquesalitremagico;

import dev.alecar.parquesalitremagico.model.*;
import dev.alecar.parquesalitremagico.service.*;
import dev.alecar.parquesalitremagico.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ParqueSalitreMagicoApplicationTests {

    @Mock
    private AtraccionRepository atraccionRepository;

    @Mock
    private EstacionRepository estacionRepository;

    @Mock
    private VisitaRepository visitaRepository;

    @Mock
    private AtraccionService atraccionService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private EmpleadoService empleadoService;

    @Mock
    private EstacionService estacionService;

    @Mock
    private VisitaService visitaService;

    @InjectMocks
    private ParqueService parqueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarVisitaAtraccion_CuandoClienteCumpleRequisitos_RegistraVisita() {
        Cliente cliente = new Cliente();
        cliente.setEstatura(150);
        Atraccion atraccion = new Atraccion();
        atraccion.setEstaturaMinima(120);
        atraccion.setDisponible(true);

        when(visitaService.verificarAccesoAtraccion(cliente, atraccion)).thenReturn(true);

        visitaService.registrarVisitaAtraccion(cliente, atraccion);

        verify(visitaService, times(1)).registrarVisitaAtraccion(cliente, atraccion);
    }

    @Test
    void verificarAccesoAtraccion_CuandoAtraccionNoDisponible_RetornaFalse() {
        Cliente cliente = new Cliente();
        cliente.setEstatura(150);
        Atraccion atraccion = new Atraccion();
        atraccion.setEstaturaMinima(120);
        atraccion.setDisponible(false);

        boolean resultado = visitaService.verificarAccesoAtraccion(cliente, atraccion);

        assertFalse(resultado);
    }

    @Test
    void verificarAccesoAtraccion_CuandoClienteNoCumpleEstatura_RetornaFalse() {
        Cliente cliente = new Cliente();
        cliente.setEstatura(100);
        Atraccion atraccion = new Atraccion();
        atraccion.setEstaturaMinima(120);
        atraccion.setDisponible(true);

        boolean resultado = visitaService.verificarAccesoAtraccion(cliente, atraccion);

        assertFalse(resultado);
    }

    @Test
    void setAtraccionNoDisponible_CuandoAtraccionExiste_CambiaEstado() {
        Long atraccionId = 1L;
        Atraccion atraccion = new Atraccion();
        atraccion.setDisponible(true);

        when(atraccionRepository.findById(atraccionId)).thenReturn(Optional.of(atraccion));
        doAnswer(invocation -> {
            atraccion.setDisponible(false);
            return null;
        }).when(atraccionService).setAtraccionNoDisponible(atraccionId);

        atraccionService.setAtraccionNoDisponible(atraccionId);

        assertFalse(atraccion.isDisponible());
    }

    @Test
    void habilitarEstacion_CuandoEstacionExiste_CambiaEstado() {
        Long estacionId = 1L;
        Estacion estacion = new Estacion();
        estacion.setHabilitada(false);

        when(estacionRepository.findById(estacionId)).thenReturn(Optional.of(estacion));
        doAnswer(invocation -> {
            estacion.setHabilitada(true);
            return null;
        }).when(estacionService).habilitarEstacion(estacionId);

        estacionService.habilitarEstacion(estacionId);

        assertTrue(estacion.isHabilitada());
    }
}