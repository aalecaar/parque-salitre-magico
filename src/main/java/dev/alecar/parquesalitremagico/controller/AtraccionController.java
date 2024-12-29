package dev.alecar.parquesalitremagico.controller;

import dev.alecar.parquesalitremagico.model.Atraccion;
import dev.alecar.parquesalitremagico.model.Cargo;
import dev.alecar.parquesalitremagico.model.Empleado;
import dev.alecar.parquesalitremagico.service.AtraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/atracciones")
public class AtraccionController {

    @Autowired
    private AtraccionService atraccionService;

    @GetMapping
    public String listarAtracciones(Model model) {
        model.addAttribute("atracciones", atraccionService.getAllAtracciones());
        return "atracciones/listar"; // Retorna la vista listar.html en la carpeta atracciones
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Verificar si hay un empleado seleccionado y si es administrativo
        Empleado empleadoSeleccionado = (Empleado) session.getAttribute("empleadoSeleccionado");
        String cargoEmpleado = (String) session.getAttribute("cargoEmpleado");
        
        if (empleadoSeleccionado == null || !Cargo.ADMINISTRATIVO.name().equals(cargoEmpleado)) {
            redirectAttributes.addFlashAttribute("error", "Para realizar esta acción debes ser empleado ADMINISTRATIVO");
            return "redirect:/atracciones";
        }
        
        model.addAttribute("atraccion", new Atraccion());
        return "atracciones/crear";
    }

    @PostMapping("/crear")
    public String crearAtraccion(@ModelAttribute Atraccion atraccion) {
        atraccionService.saveAtraccion(atraccion);
        return "redirect:/atracciones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model,
                                        HttpSession session, RedirectAttributes redirectAttributes) {
        // Verificar si hay un empleado seleccionado y si es administrativo
        Empleado empleadoSeleccionado = (Empleado) session.getAttribute("empleadoSeleccionado");
        String cargoEmpleado = (String) session.getAttribute("cargoEmpleado");
        
        if (empleadoSeleccionado == null || !Cargo.ADMINISTRATIVO.name().equals(cargoEmpleado)) {
            redirectAttributes.addFlashAttribute("error", "Para realizar esta acción debes ser empleado ADMINISTRATIVO");
            return "redirect:/atracciones";
        }
        
        atraccionService.getAtraccionById(id).ifPresent(atraccion -> model.addAttribute("atraccion", atraccion));
        return "atracciones/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizarAtraccion(@PathVariable Long id, @ModelAttribute Atraccion atraccion,
                                    HttpSession session, RedirectAttributes redirectAttributes) {
        // Verificar si hay un empleado seleccionado y si es administrativo
        Empleado empleadoSeleccionado = (Empleado) session.getAttribute("empleadoSeleccionado");
        String cargoEmpleado = (String) session.getAttribute("cargoEmpleado");
        
        if (empleadoSeleccionado == null || !Cargo.ADMINISTRATIVO.name().equals(cargoEmpleado)) {
            redirectAttributes.addFlashAttribute("error", "Para realizar esta acción debes ser empleado ADMINISTRATIVO");
            return "redirect:/atracciones";
        }
        
        atraccionService.updateAtraccion(id, atraccion);
        return "redirect:/atracciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAtraccion(@PathVariable Long id) {
        atraccionService.deleteAtraccion(id);
        return "redirect:/atracciones";
    }

    @PostMapping("/{id}/disponible")
    public String setAtraccionDisponible(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        // Verificar si hay un empleado seleccionado y si es de mantenimiento
        Empleado empleadoSeleccionado = (Empleado) session.getAttribute("empleadoSeleccionado");
        String cargoEmpleado = (String) session.getAttribute("cargoEmpleado");
        
        if (empleadoSeleccionado == null || !Cargo.MANTENIMIENTO.name().equals(cargoEmpleado)) {
            redirectAttributes.addFlashAttribute("error", "Para realizar esta acción debes ser empleado de MANTENIMIENTO");
            return "redirect:/atracciones";
        }
        
        atraccionService.setAtraccionDisponible(id);
        return "redirect:/atracciones";
    }

    @PostMapping("/{id}/no-disponible")
    public String setAtraccionNoDisponible(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        // Verificar si hay un empleado seleccionado y si es de mantenimiento
        Empleado empleadoSeleccionado = (Empleado) session.getAttribute("empleadoSeleccionado");
        String cargoEmpleado = (String) session.getAttribute("cargoEmpleado");
        
        if (empleadoSeleccionado == null || !Cargo.MANTENIMIENTO.name().equals(cargoEmpleado)) {
            redirectAttributes.addFlashAttribute("error", "Para realizar esta acción debes ser empleado de MANTENIMIENTO");
            return "redirect:/atracciones";
        }
        
        atraccionService.setAtraccionNoDisponible(id);
        return "redirect:/atracciones";
    }
}