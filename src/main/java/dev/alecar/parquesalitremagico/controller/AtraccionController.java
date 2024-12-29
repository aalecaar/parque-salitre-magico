package dev.alecar.parquesalitremagico.controller;

import dev.alecar.parquesalitremagico.model.Atraccion;
import dev.alecar.parquesalitremagico.service.AtraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("atraccion", new Atraccion());
        return "atracciones/crear"; // Retorna la vista crear.html en la carpeta atracciones
    }

    @PostMapping("/crear")
    public String crearAtraccion(@ModelAttribute Atraccion atraccion) {
        atraccionService.saveAtraccion(atraccion);
        return "redirect:/atracciones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        atraccionService.getAtraccionById(id).ifPresent(atraccion -> model.addAttribute("atraccion", atraccion));
        return "atracciones/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizarAtraccion(@PathVariable Long id, @ModelAttribute Atraccion atraccion) {
        atraccionService.updateAtraccion(id, atraccion);
        return "redirect:/atracciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAtraccion(@PathVariable Long id) {
        atraccionService.deleteAtraccion(id);
        return "redirect:/atracciones";
    }

    @PostMapping("/{id}/disponible")
    public String setAtraccionDisponible(@PathVariable Long id) {
        atraccionService.setAtraccionDisponible(id);
        return "redirect:/atracciones";
    }

    @PostMapping("/{id}/no-disponible")
    public String setAtraccionNoDisponible(@PathVariable Long id) {
        atraccionService.setAtraccionNoDisponible(id);
        return "redirect:/atracciones";
    }
}