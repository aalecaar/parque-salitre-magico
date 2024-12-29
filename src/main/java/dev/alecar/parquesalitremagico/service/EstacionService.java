package dev.alecar.parquesalitremagico.service;

import dev.alecar.parquesalitremagico.model.Estacion;
import dev.alecar.parquesalitremagico.repository.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstacionService {

    @Autowired
    private EstacionRepository estacionRepository;

    public List<Estacion> getAllEstaciones() {
        return estacionRepository.findAll();
    }

    public Optional<Estacion> getEstacionById(Long id) {
        return estacionRepository.findById(id);
    }

    public Estacion saveEstacion(Estacion estacion) {
        return estacionRepository.save(estacion);
    }

    public void deleteEstacion(Long id) {
        estacionRepository.deleteById(id);
    }

    // Permite que un empleado administrativo habilite una estación.
    public Estacion habilitarEstacion(Long id) {
        Optional<Estacion> estacionExistente = estacionRepository.findById(id);
        if (estacionExistente.isPresent()) {
            Estacion estacion = estacionExistente.get();
            estacion.setHabilitada(true);
            return estacionRepository.save(estacion);
        } else {
            return null; // O lanzar una excepción
        }
    }

    // Permite que un empleado administrativo deshabilite una estación.
    public Estacion deshabilitarEstacion(Long id) {
        Optional<Estacion> estacionExistente = estacionRepository.findById(id);
        if (estacionExistente.isPresent()) {
            Estacion estacion = estacionExistente.get();
            estacion.setHabilitada(false);
            return estacionRepository.save(estacion);
        } else {
            return null; // O lanzar una excepción
        }
    }
}