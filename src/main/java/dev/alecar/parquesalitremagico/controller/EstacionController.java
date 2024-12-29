package dev.alecar.parquesalitremagico.controller;

import dev.alecar.parquesalitremagico.model.Atraccion;
import dev.alecar.parquesalitremagico.model.Cargo;
import dev.alecar.parquesalitremagico.model.Empleado;
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
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String habilitarEstacion(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        // Verificar si hay un empleado seleccionado y si es administrativo
        Empleado empleadoSeleccionado = (Empleado) session.getAttribute("empleadoSeleccionado");
        String cargoEmpleado = (String) session.getAttribute("cargoEmpleado");
        
        if (empleadoSeleccionado == null || !Cargo.ADMINISTRATIVO.name().equals(cargoEmpleado)) {
            redirectAttributes.addFlashAttribute("error", "Para realizar esta acción debes ser empleado ADMINISTRATIVO");
            return "redirect:/estaciones";
        }
        
        estacionService.habilitarEstacion(id);
        return "redirect:/estaciones";
    }

    @PostMapping("/{id}/deshabilitar")
    public String deshabilitarEstacion(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        // Verificar si hay un empleado seleccionado y si es administrativo
        Empleado empleadoSeleccionado = (Empleado) session.getAttribute("empleadoSeleccionado");
        String cargoEmpleado = (String) session.getAttribute("cargoEmpleado");
        
        if (empleadoSeleccionado == null || !Cargo.ADMINISTRATIVO.name().equals(cargoEmpleado)) {
            redirectAttributes.addFlashAttribute("error", "Para realizar esta acción debes ser empleado ADMINISTRATIVO");
            return "redirect:/estaciones";
        }
        
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