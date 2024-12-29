package dev.alecar.parquesalitremagico.service;

import dev.alecar.parquesalitremagico.model.Atraccion;
import dev.alecar.parquesalitremagico.model.Cliente;
import dev.alecar.parquesalitremagico.model.Visita;
import dev.alecar.parquesalitremagico.repository.VisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    public List<Visita> getAllVisitas() {
        return visitaRepository.findAll();
    }

    public Optional<Visita> getVisitaById(Long id) {
        return visitaRepository.findById(id);
    }

    public Visita saveVisita(Visita visita) {
        return visitaRepository.save(visita);
    }

    public void deleteVisita(Long id) {
        visitaRepository.deleteById(id);
    }

    // Registra una visita de un cliente a una atracción
    public Visita registrarVisitaAtraccion(Cliente cliente, Atraccion atraccion) {
        Visita visita = new Visita();
        visita.setCliente(cliente);
        visita.setAtraccion(atraccion);
        visita.setFecha(LocalDateTime.now());
        return visitaRepository.save(visita);
    }

    // Verifica si un cliente puede acceder a una atracción
    public boolean verificarAccesoAtraccion(Cliente cliente, Atraccion atraccion) {
        if (!atraccion.isDisponible()) {
            return false; // La atracción no está disponible
        }
        if (cliente.getEstatura() < atraccion.getEstaturaMinima()) {
            return false; // El cliente no tiene la estatura mínima
        }
        return true;
    }

    // Obtiene el número de visitas de un cliente
    public long getNumeroVisitasCliente(Cliente cliente) {
        List<Visita> visitas = visitaRepository.findAll();
        return visitas.stream()
                .filter(visita -> visita.getCliente().equals(cliente))
                .count();
    }

    // Verifica si un cliente es frecuente (2 o más visitas a atracciones diferentes)
    public boolean esClienteFrecuente(Cliente cliente) {
        List<Visita> visitas = visitaRepository.findAll();
        long numVisitas = visitas.stream()
                .filter(visita -> visita.getCliente().equals(cliente))
                .count();
        return numVisitas >= 2;
    }

    public List<Map.Entry<Atraccion, Long>> getVisitasAtracciones() {
        List<Visita> visitas = visitaRepository.findAll();

        return visitas.stream()
                .collect(Collectors.groupingBy(Visita::getAtraccion, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
    }

    public List<Atraccion> getAtraccionesMasVisitadas() {
        Map<Atraccion, Long> visitasPorAtraccion = getAllVisitas().stream()
            .collect(Collectors.groupingBy(Visita::getAtraccion, Collectors.counting()));
        
        return visitasPorAtraccion.entrySet().stream()
            .sorted(Map.Entry.<Atraccion, Long>comparingByValue().reversed())
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }
}