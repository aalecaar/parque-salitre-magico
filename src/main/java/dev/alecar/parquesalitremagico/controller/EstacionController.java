package dev.alecar.parquesalitremagico.controller;

import dev.alecar.parquesalitremagico.model.Atraccion;
import dev.alecar.parquesalitremagico.model.Estacion;
import dev.alecar.parquesalitremagico.service.EstacionService;
import dev.alecar.parquesalitremagico.service.AtraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/estaciones")
public class EstacionController {

    @Autowired
    private EstacionService estacionService;

    @Autowired
    private AtraccionService atraccionService;

    @GetMapping
    public String listarEstaciones(Model model) {
        model.addAttribute("estaciones", estacionService.getAllEstaciones());
        return "estaciones/listar";
    }

    @PostMapping("/{id}/habilitar")
    public String habilitarEstacion(@PathVariable Long id) {
        estacionService.habilitarEstacion(id);
        return "redirect:/estaciones";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitarEstacion(@PathVariable Long id) {
        estacionService.deshabilitarEstacion(id);
        return "redirect:/estaciones";
    }

    @GetMapping("/{id}/atracciones")
    public String verAtracciones(@PathVariable Long id, Model model) {
        Optional<Estacion> estacion = estacionService.getEstacionById(id);
        if (estacion.isPresent()) {
            model.addAttribute("estacion", estacion.get());
            List<Atraccion> atraccionesDisponibles = atraccionService.getAllAtracciones()
                .stream()
                .filter(Atraccion::isDisponible)
                .collect(Collectors.toList());
            model.addAttribute("atracciones", atraccionesDisponibles);
            return "estaciones/atracciones";
        }
        return "redirect:/estaciones";
    }
}