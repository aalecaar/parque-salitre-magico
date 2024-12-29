package dev.alecar.parquesalitremagico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

@Data
@Entity
public class Estacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private boolean habilitada = true;

    @OneToMany(mappedBy = "estacion", cascade = CascadeType.ALL)
    private List<Atraccion> atracciones = new ArrayList<>();

    public Estacion() {
    }

    public void addAtraccion(Atraccion atraccion) {
        atracciones.add(atraccion);
        atraccion .setEstacion(this);
    }

    public void removeAtraccion(Atraccion atraccion) {
        atracciones.remove(atraccion);
        atraccion.setEstacion(null);
    }
}