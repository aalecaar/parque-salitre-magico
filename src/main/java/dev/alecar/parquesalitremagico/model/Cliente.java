package dev.alecar.parquesalitremagico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.*;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private Estacion estacionRegistro;
    
    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private int estatura;
    private int edad;
    private String familiarContacto; // Solo para menores de edad
    private boolean primeraVisita = true; // Para registrar si es la primera visita

    public Cliente() {
    }
}