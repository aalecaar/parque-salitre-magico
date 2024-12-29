package dev.alecar.parquesalitremagico.service;

import dev.alecar.parquesalitremagico.model.Cliente;
import dev.alecar.parquesalitremagico.model.Estacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParqueService {
    private int contadorClientes = 0;

    @Autowired
    private EstacionService estacionService;

    public int getNumeroClientesEnParque() {
        return contadorClientes;
    }

    // Simula la entrada de un cliente al parque
    public void registrarEntradaCliente(Cliente cliente) {
        contadorClientes++;
        ajustarEstacionesSegunOcupacion();
    }

    // Simula la salida de un cliente del parque
    public void registrarSalidaCliente(Cliente cliente) {
        contadorClientes--;
        ajustarEstacionesSegunOcupacion();
    }

    // Calcula el porcentaje de ocupación del parque (basado en un aforo máximo)
    public double getPorcentajeOcupacion(int aforoMaximo) {
        if (aforoMaximo <= 0) {
            return 0; // O lanzar una excepción
        }
        return (double) contadorClientes / aforoMaximo * 100;
    }

    private void ajustarEstacionesSegunOcupacion() {
        double porcentaje = getPorcentajeOcupacion(100); // Usando 100 como aforo máximo
        List<Estacion> estaciones = estacionService.getAllEstaciones();
        
        // Ordenar estaciones por ID para mantener consistencia
        estaciones.sort((e1, e2) -> e1.getId().compareTo(e2.getId()));
        
        int estacionesAHabilitar;
        if (porcentaje > 70) {
            estacionesAHabilitar = 5;
        } else if (porcentaje >= 40) {
            estacionesAHabilitar = 4;
        } else if (porcentaje >= 20) {
            estacionesAHabilitar = 3;
        } else if (porcentaje >= 10) {
            estacionesAHabilitar = 2;
        } else {
            estacionesAHabilitar = 1;
        }

        // Habilitar o deshabilitar estaciones según corresponda
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacion = estaciones.get(i);
            if (i < estacionesAHabilitar) {
                if (!estacion.isHabilitada()) {
                    estacionService.habilitarEstacion(estacion.getId());
                }
            } else {
                if (estacion.isHabilitada()) {
                    estacionService.deshabilitarEstacion(estacion.getId());
                }
            }
        }
    }
}