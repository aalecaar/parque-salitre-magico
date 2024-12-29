package dev.alecar.parquesalitremagico.service;

import dev.alecar.parquesalitremagico.model.Atraccion;
import dev.alecar.parquesalitremagico.repository.AtraccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtraccionService {

    @Autowired
    private AtraccionRepository atraccionRepository;

    public List<Atraccion> getAllAtracciones() {
        return atraccionRepository.findAll();
    }

    public Optional<Atraccion> getAtraccionById(Long id) {
        return atraccionRepository.findById(id);
    }

    public Atraccion saveAtraccion(Atraccion atraccion) {
        return atraccionRepository.save(atraccion);
    }

    public void deleteAtraccion(Long id) {
        atraccionRepository.deleteById(id);
    }

    // Permite que un empleado administrativo modifique la descripción,
    // clasificación y condiciones de uso de una atracción.
    public Atraccion updateAtraccion(Long id, Atraccion atraccionActualizada) {
        Optional<Atraccion> atraccionExistente = atraccionRepository.findById(id);
        if (atraccionExistente.isPresent()) {
            Atraccion atraccion = atraccionExistente.get();
            atraccion.setDescripcion(atraccionActualizada.getDescripcion());
            atraccion.setClasificacion(atraccionActualizada.getClasificacion());
            atraccion.setCondicionesUso(atraccionActualizada.getCondicionesUso());
            return atraccionRepository.save(atraccion);
        } else {
            return null; // O lanzar una excepción
        }
    }

    // Permite que un empleado de mantenimiento cambie el estado de una
    // atracción a "no disponible".
    public Atraccion setAtraccionNoDisponible(Long id) {
        Optional<Atraccion> atraccionExistente = atraccionRepository.findById(id);
        if (atraccionExistente.isPresent()) {
            Atraccion atraccion = atraccionExistente.get();
            atraccion.setDisponible(false);
            return atraccionRepository.save(atraccion);
        } else {
            return null; // O lanzar una excepción
        }
    }

    // Permite que un empleado de mantenimiento cambie el estado de una
    // atracción a "disponible".
    public Atraccion setAtraccionDisponible(Long id) {
        Optional<Atraccion> atraccionExistente = atraccionRepository.findById(id);
        if (atraccionExistente.isPresent()) {
            Atraccion atraccion = atraccionExistente.get();
            atraccion.setDisponible(true);
            return atraccionRepository.save(atraccion);
        } else {
            return null; // O lanzar una excepción
        }
    }

    public List<Atraccion> getAtraccionesDisponibles() {
        return atraccionRepository.findAll()
            .stream()
            .filter(Atraccion::isDisponible)
            .collect(Collectors.toList());
    }
}