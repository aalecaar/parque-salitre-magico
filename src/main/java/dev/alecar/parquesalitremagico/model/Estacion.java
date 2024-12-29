package dev.alecar.parquesalitremagico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
import java.util.ArrayList;

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

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public boolean isHabilitada() {
        return this.habilitada;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public List<Atraccion> getAtracciones() {
        return this.atracciones;
    }

    public void setAtracciones(List<Atraccion> atracciones) {
        this.atracciones = atracciones;
    }

    public void addAtraccion(Atraccion atraccion) {
        atracciones.add(atraccion);
        atraccion.setEstacion(this);
    }

    public void removeAtraccion(Atraccion atraccion) {
        atracciones.remove(atraccion);
        atraccion.setEstacion(null);
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Estacion)) return false;
        final Estacion other = (Estacion) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) return false;
        if (this.isHabilitada() != other.isHabilitada()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Estacion;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        result = result * PRIME + (this.isHabilitada() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "Estacion(id=" + this.getId() + ", nombre=" + this.getNombre() + ", habilitada=" + this.isHabilitada() + ")";
    }
}