package dev.alecar.parquesalitremagico.controller;

import dev.alecar.parquesalitremagico.model.Empleado;
import dev.alecar.parquesalitremagico.model.Cargo;
import dev.alecar.parquesalitremagico.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String listarEmpleados(Model model, HttpSession session) {
        List<Empleado> empleados = empleadoService.getAllEmpleados();
        model.addAttribute("empleados", empleados);
        
        Empleado empleadoSeleccionado = (Empleado) session.getAttribute("empleadoSeleccionado");
        model.addAttribute("empleadoSeleccionado", empleadoSeleccionado);
        
        return "empleados/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("cargos", Cargo.values());
        return "empleados/crear";
    }

    @PostMapping("/crear")
    public String crearEmpleado(@ModelAttribute Empleado empleado) {
        empleadoService.saveEmpleado(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Empleado> empleado = empleadoService.getEmpleadoById(id);
        if (empleado.isPresent()) {
            model.addAttribute("empleado", empleado.get());
            model.addAttribute("cargos", Cargo.values());
            return "empleados/editar";
        }
        return "redirect:/empleados";
    }

    @PostMapping("/editar/{id}")
    public String actualizarEmpleado(@PathVariable Long id, @ModelAttribute Empleado empleado) {
        empleado.setId(id);
        empleadoService.saveEmpleado(empleado);
        return "redirect:/empleados";
    }

    @PostMapping("/seleccionar/{id}")
    public String seleccionarEmpleado(@PathVariable Long id, HttpSession session) {
        Optional<Empleado> empleado = empleadoService.getEmpleadoById(id);
        if (empleado.isPresent()) {
            session.setAttribute("empleadoSeleccionado", empleado.get());
            session.setAttribute("cargoEmpleado", empleado.get().getCargo());
            return "redirect:/empleados";
        }
        return "redirect:/empleados";
    }

    @GetMapping("/deseleccionar")
    public String deseleccionarEmpleado(HttpSession session) {
        session.removeAttribute("empleadoSeleccionado");
        session.removeAttribute("cargoEmpleado");
        return "redirect:/empleados";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
        return "redirect:/empleados";
    }
}