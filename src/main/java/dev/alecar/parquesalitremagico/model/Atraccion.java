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
public class Atraccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String descripcion;
    private String clasificacion;
    private String condicionesUso;
    private boolean disponible = true;
    private int estaturaMinima; // (cm)

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private Estacion estacion;

    public Atraccion() {
    }
}