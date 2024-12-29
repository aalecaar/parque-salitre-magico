package dev.alecar.parquesalitremagico.controller;

import dev.alecar.parquesalitremagico.model.Estacion;
import dev.alecar.parquesalitremagico.service.ParqueService;
import dev.alecar.parquesalitremagico.service.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parque")
public class ParqueController {

    @Autowired
    private ParqueService parqueService;

    @Autowired
    private EstacionService estacionService;

    // Valor temporal para simular el aforo máximo
    private final int AFORO_MAXIMO = 100;

    @GetMapping("/ocupacion")
    public String mostrarOcupacion(Model model) {
        model.addAttribute("numeroClientes", parqueService.getNumeroClientesEnParque());
        double porcentajeOcupacion = parqueService.getPorcentajeOcupacion(AFORO_MAXIMO);
        model.addAttribute("porcentajeOcupacion", porcentajeOcupacion);
        
        long estacionesHabilitadas = estacionService.getAllEstaciones()
            .stream()
            .filter(Estacion::isHabilitada)
            .count();
        model.addAttribute("estacionesHabilitadas", estacionesHabilitadas);
        
        return "parque/ocupacion";
    }
}