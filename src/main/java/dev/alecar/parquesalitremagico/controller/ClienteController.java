package dev.alecar.parquesalitremagico.controller;

import dev.alecar.parquesalitremagico.model.Cliente;
import dev.alecar.parquesalitremagico.model.Estacion;
import dev.alecar.parquesalitremagico.service.ClienteService;
import dev.alecar.parquesalitremagico.service.EstacionService;
import dev.alecar.parquesalitremagico.service.ParqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ParqueService parqueService;

    @Autowired
    private EstacionService estacionService;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.getAllClientes());
        return "clientes/listar";
    }

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        List<Estacion> estacionesHabilitadas = estacionService.getAllEstaciones()
            .stream()
            .filter(Estacion::isHabilitada)
            .collect(Collectors.toList());
            
        model.addAttribute("estaciones", estacionesHabilitadas);
        model.addAttribute("cliente", new Cliente());
        return "clientes/registrar";
    }

    @PostMapping("/registrar")
    public String registrarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        clienteService.registrarPrimeraVisita(cliente);
        parqueService.registrarEntradaCliente(cliente);
        redirectAttributes.addFlashAttribute("registroExitoso", true);
        return "redirect:/clientes/registrar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "clientes/editar";
        }
        return "redirect:/clientes";
    }

    @PostMapping("/editar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        cliente.setId(id);
        clienteService.saveCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return "redirect:/clientes";
    }
}