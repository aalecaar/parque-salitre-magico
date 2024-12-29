package dev.alecar.parquesalitremagico.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
public class Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Atraccion atraccion;

    private LocalDateTime fecha;

    public Visita() {
    }
}