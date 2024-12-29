package dev.alecar.parquesalitremagico.service;

import dev.alecar.parquesalitremagico.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocionService {

    @Autowired
    private VisitaService visitaService;

    @Autowired
    private ClienteService clienteService;

    public int calcularDescuento(Cliente cliente) {
        if (!puedeRecibirPromociones(cliente)) {
            return 0;
        }

        long numeroVisitas = visitaService.getNumeroVisitasCliente(cliente);
        
        if (numeroVisitas >= 6) {
            return 50;
        } else if (numeroVisitas >= 4) {
            return 25;
        } else if (numeroVisitas >= 2) {
            return 10;
        }
        
        return 0;
    }

    public boolean puedeRecibirPromociones(Cliente cliente) {
        return cliente.getEdad() >= 18;
    }

    public void simularEnvioPromocion(Cliente cliente) {
        if (!puedeRecibirPromociones(cliente)) {
            System.out.println("No se puede enviar promoción al cliente " + cliente.getNombre() + " por ser menor de edad");
            return;
        }

        int descuento = calcularDescuento(cliente);
        if (descuento > 0) {
            System.out.println("Simulando envío de promoción por email a: " + cliente.getCorreo());
            System.out.println("¡Felicitaciones " + cliente.getNombre() + "! Has obtenido un " + descuento + "% de descuento en tu próxima visita.");
        }
    }

    public String enviarPromocion(Long clienteId) {
        Cliente cliente = clienteService.getClienteById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        
        if (!puedeRecibirPromociones(cliente)) {
            System.out.println("No se puede enviar promoción al cliente " + cliente.getNombre() + " por ser menor de edad");
            return null;
        }

        int descuento = calcularDescuento(cliente);
        if (descuento > 0) {
            System.out.println("Enviando promoción por email a: " + cliente.getCorreo());
            System.out.println("¡Felicitaciones " + cliente.getNombre() + "! Has obtenido un " + descuento + "% de descuento en tu próxima visita.");
            return "Promoción del " + descuento + "% enviada a " + cliente.getNombre();
        }
        return null;
    }
} 