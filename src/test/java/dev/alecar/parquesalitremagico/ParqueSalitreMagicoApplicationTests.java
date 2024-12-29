package dev.alecar.parquesalitremagico;

import dev.alecar.parquesalitremagico.model.*;
import dev.alecar.parquesalitremagico.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ParqueSalitreMagicoApplicationTests {

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

    @Test
    void registrarVisitaAtraccion_CuandoClienteCumpleRequisitos_RegistraVisita() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setEstatura(150);
        Atraccion atraccion = new Atraccion();
        atraccion.setEstaturaMinima(120);
        atraccion.setDisponible(true);

        when(visitaService.verificarAccesoAtraccion(cliente, atraccion)).thenReturn(true);

        // Act
        visitaService.registrarVisitaAtraccion(cliente, atraccion);

        // Assert
        verify(visitaService, times(1)).registrarVisitaAtraccion(cliente, atraccion);
    }

    @Test
    void verificarAccesoAtraccion_CuandoAtraccionNoDisponible_RetornaFalse() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setEstatura(150);
        Atraccion atraccion = new Atraccion();
        atraccion.setEstaturaMinima(120);
        atraccion.setDisponible(false);

        // Act
        boolean resultado = visitaService.verificarAccesoAtraccion(cliente, atraccion);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void verificarAccesoAtraccion_CuandoClienteNoCumpleEstatura_RetornaFalse() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setEstatura(100);
        Atraccion atraccion = new Atraccion();
        atraccion.setEstaturaMinima(120);
        atraccion.setDisponible(true);

        // Act
        boolean resultado = visitaService.verificarAccesoAtraccion(cliente, atraccion);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void setAtraccionNoDisponible_CuandoAtraccionExiste_CambiaEstado() {
        // Arrange
        Long atraccionId = 1L;
        Atraccion atraccion = new Atraccion();
        atraccion.setDisponible(true);

        when(atraccionService.getAtraccionById(atraccionId)).thenReturn(Optional.of(atraccion));
        when(atraccionService.setAtraccionNoDisponible(atraccionId)).thenCallRealMethod();

        // Act
        atraccionService.setAtraccionNoDisponible(atraccionId);

        // Assert
        assertFalse(atraccionService.getAtraccionById(atraccionId).get().isDisponible());
    }

    @Test
    void habilitarEstacion_CuandoEstacionExiste_CambiaEstado() {
        // Arrange
        Long estacionId = 1L;
        Estacion estacion = new Estacion();
        estacion.setHabilitada(false);

        when(estacionService.getEstacionById(estacionId)).thenReturn(Optional.of(estacion));
        when(estacionService.habilitarEstacion(estacionId)).thenCallRealMethod();

        // Act
        estacionService.habilitarEstacion(estacionId);

        // Assert
        assertTrue(estacionService.getEstacionById(estacionId).get().isHabilitada());


    }


    @Test
    void getAtraccionesMasVisitadas_CuandoHayVisitas_RetornaListaOrdenada() {
        // Arrange
        Atraccion atraccion1 = new Atraccion();
        atraccion1.setId(1L);
        atraccion1.setNombre("Atracción 1");
        Atraccion atraccion2 = new Atraccion();
        atraccion2.setId(2L);
        atraccion2.setNombre("Atracción 2");
        Atraccion atraccion3 = new Atraccion();
        atraccion3.setId(3L);
        atraccion3.setNombre("Atracción 3");

        Visita visita1 = new Visita();
        visita1.setAtraccion(atraccion1);
        Visita visita2 = new Visita();
        visita2.setAtraccion(atraccion2);
        Visita visita3 = new Visita();
        visita3.setAtraccion(atraccion1);
        Visita visita4 = new Visita();
        visita4.setAtraccion(atraccion3);
        Visita visita5 = new Visita();
        visita5.setAtraccion(atraccion1);

        when(visitaService.getAllVisitas()).thenReturn(Arrays.asList(visita1, visita2, visita3, visita4, visita5));
        when(visitaService.getAtraccionesMasVisitadas()).thenCallRealMethod();

        // Act
        List<Atraccion> resultado = visitaService.getAtraccionesMasVisitadas();

        // Assert
        assertEquals(3, resultado.size());
        assertEquals("Atracción 1", resultado.get(0).getNombre());
        assertEquals("Atracción 3", resultado.get(1).getNombre());
        assertEquals("Atracción 2", resultado.get(2).getNombre());
    }

    @Test
    void getNumeroVisitasCliente_CuandoClienteTieneVisitas_RetornaNumeroCorrecto() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Atraccion atraccion1 = new Atraccion();
        atraccion1.setId(1L);
        Atraccion atraccion2 = new Atraccion();
        atraccion2.setId(2L);

        Visita visita1 = new Visita();
        visita1.setCliente(cliente);
        visita1.setAtraccion(atraccion1);
        Visita visita2 = new Visita();
        visita2.setCliente(cliente);
        visita2.setAtraccion(atraccion2);
        Visita visita3 = new Visita();
        visita3.setCliente(cliente);
        visita3.setAtraccion(atraccion1);

        when(visitaService.getAllVisitas()).thenReturn(Arrays.asList(visita1, visita2, visita3));
        when(visitaService.getNumeroVisitasCliente(cliente)).thenCallRealMethod();

        // Act
        long resultado = visitaService.getNumeroVisitasCliente(cliente);

        // Assert
        assertEquals(3, resultado);
    }

    @Test
    void esClienteFrecuente_CuandoClienteTiene2oMasVisitas_RetornaTrue() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Atraccion atraccion = new Atraccion();
        atraccion.setId(1L);

        Visita visita1 = new Visita();
        visita1.setCliente(cliente);
        visita1.setAtraccion(atraccion);
        
        Visita visita2 = new Visita();
        visita2.setCliente(cliente);
        visita2.setAtraccion(atraccion);

        when(visitaService.getAllVisitas()).thenReturn(Arrays.asList(visita1, visita2));
        when(visitaService.esClienteFrecuente(cliente)).thenCallRealMethod();

        // Act
        boolean resultado = visitaService.esClienteFrecuente(cliente);

        // Assert
        assertTrue(resultado);
    }
}