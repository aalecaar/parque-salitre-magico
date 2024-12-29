package dev.alecar.parquesalitremagico.controller;

import dev.alecar.parquesalitremagico.model.*;
import dev.alecar.parquesalitremagico.service.AtraccionService;
import dev.alecar.parquesalitremagico.service.ClienteService;
import dev.alecar.parquesalitremagico.service.ParqueService;
import dev.alecar.parquesalitremagico.service.PromocionService;
import dev.alecar.parquesalitremagico.service.TicketService;
import dev.alecar.parquesalitremagico.service.VisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/visitas")
public class VisitaController {

    @Autowired
    private VisitaService visitaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AtraccionService atraccionService;

    @Autowired
    private ParqueService parqueService;

    @Autowired
    private PromocionService promocionService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/registrar/{clienteId}/{atraccionId}")
    public String registrarVisita(@PathVariable Long clienteId, @PathVariable Long atraccionId, 
                                RedirectAttributes redirectAttributes) {
        Cliente cliente = clienteService.getClienteById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Atraccion atraccion = atraccionService.getAtraccionById(atraccionId)
                .orElseThrow(() -> new IllegalArgumentException("Atracción no encontrada"));

        if (visitaService.verificarAccesoAtraccion(cliente, atraccion)) {
            Visita visita = visitaService.registrarVisitaAtraccion(cliente, atraccion);
            
            Ticket ticket = new Ticket();
            ticket.setCliente(cliente);
            ticket.setEstacion(cliente.getEstacionRegistro());
            ticket.setFecha(LocalDate.now());
            ticketService.saveTicket(ticket);
            
            parqueService.registrarEntradaCliente(cliente);
            parqueService.registrarSalidaCliente(cliente);
            redirectAttributes.addFlashAttribute("registroVisitaExitoso", true);
            return "redirect:/";
        } else {
            return "visitas/denegado";
        }
    }

    @GetMapping("/verificar")
    public String verificarAcceso(Model model, @RequestParam(required = false) Long clienteId,
                                  @RequestParam(required = false) Long atraccionId,
                                  HttpSession session) {
        // Verificar si hay un empleado seleccionado y si es de logística
        Empleado empleadoSeleccionado = (Empleado) session.getAttribute("empleadoSeleccionado");
        String cargoEmpleado = (String) session.getAttribute("cargoEmpleado");
        
        if (empleadoSeleccionado == null || !Cargo.LOGISTICA.name().equals(cargoEmpleado)) {
            model.addAttribute("error", "Para realizar esta acción debes ser empleado de LOGÍSTICA");
            return "visitas/denegado-empleado";
        }
        
        List<Cliente> clientes = clienteService.getAllClientes();
        List<Atraccion> atracciones = atraccionService.getAllAtracciones();
        
        model.addAttribute("clientes", clientes);
        model.addAttribute("atracciones", atracciones);
        
        if (clienteId != null) {
            model.addAttribute("selectedCliente", clienteId);
        } else if (!clientes.isEmpty()) {
            model.addAttribute("selectedCliente", clientes.get(0).getId());
        } else {
            model.addAttribute("selectedCliente", null);
        }
        
        if (atraccionId != null) {
            model.addAttribute("selectedAtraccion", atraccionId);
        } else if (!atracciones.isEmpty()) {
            model.addAttribute("selectedAtraccion", atracciones.get(0).getId());
        } else {
            model.addAttribute("selectedAtraccion", null);
        }
        
        return "visitas/verificar";
    }

    @GetMapping("/visitas-atracciones")
    public String listarVisitasAtracciones(Model model) {
        model.addAttribute("visitasAtracciones", visitaService.getVisitasAtracciones());
        return "visitas/lista-atracciones";
    }

    @GetMapping("/clientes-frecuentes")
    public String listarClientesFrecuentes(Model model) {
        List<Cliente> todosLosClientes = clienteService.getAllClientes();
        List<Cliente> clientesFrecuentes = todosLosClientes.stream()
                .filter(cliente -> visitaService.esClienteFrecuente(cliente))
                .collect(Collectors.toList());
                
        // Simular envío de promociones a clientes frecuentes
        clientesFrecuentes.forEach(cliente -> promocionService.simularEnvioPromocion(cliente));
        
        model.addAttribute("clientes", clientesFrecuentes);
        model.addAttribute("visitaService", visitaService);
        model.addAttribute("promocionService", promocionService);
        return "visitas/clientes-frecuentes";
    }

    @PostMapping("/enviar-promocion/{id}")
    public String enviarPromocion(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Empleado empleadoSeleccionado = (Empleado) session.getAttribute("empleadoSeleccionado");
        String cargoEmpleado = (String) session.getAttribute("cargoEmpleado");
        
        if (empleadoSeleccionado == null || !Cargo.PUBLICIDAD.name().equals(cargoEmpleado)) {
            redirectAttributes.addFlashAttribute("error", "Para realizar esta acción debes ser empleado de PUBLICIDAD");
            return "redirect:/visitas/clientes-frecuentes";
        }
        
        String mensaje = promocionService.enviarPromocion(id);
        if (mensaje != null) {
            redirectAttributes.addFlashAttribute("mensajeExito", mensaje);
        }
        return "redirect:/visitas/clientes-frecuentes";
    }
}