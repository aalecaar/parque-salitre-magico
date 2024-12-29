package dev.alecar.parquesalitremagico.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    protected boolean canEqual(final Object other) {
        return other instanceof Visita;
    }

    public Long getId() {
        return this.id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public Atraccion getAtraccion() {
        return this.atraccion;
    }

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setAtraccion(Atraccion atraccion) {
        this.atraccion = atraccion;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Visita)) return false;
        final Visita other = (Visita) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$cliente = this.getCliente();
        final Object other$cliente = other.getCliente();
        if (this$cliente == null ? other$cliente != null : !this$cliente.equals(other$cliente)) return false;
        final Object this$atraccion = this.getAtraccion();
        final Object other$atraccion = other.getAtraccion();
        if (this$atraccion == null ? other$atraccion != null : !this$atraccion.equals(other$atraccion)) return false;
        final Object this$fecha = this.getFecha();
        final Object other$fecha = other.getFecha();
        if (this$fecha == null ? other$fecha != null : !this$fecha.equals(other$fecha)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $cliente = this.getCliente();
        result = result * PRIME + ($cliente == null ? 43 : $cliente.hashCode());
        final Object $atraccion = this.getAtraccion();
        result = result * PRIME + ($atraccion == null ? 43 : $atraccion.hashCode());
        final Object $fecha = this.getFecha();
        result = result * PRIME + ($fecha == null ? 43 : $fecha.hashCode());
        return result;
    }

    public String toString() {
        return "Visita(id=" + this.getId() + ", cliente=" + this.getCliente() + ", atraccion=" + this.getAtraccion() + ", fecha=" + this.getFecha() + ")";
    }
}