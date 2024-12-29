package dev.alecar.parquesalitremagico.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Estacion estación;
    private LocalDate fecha;

    public Ticket() {
    }

    public Long getId() {
        return this.id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public Estacion getEstación() {
        return this.estación;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEstación(Estacion estación) {
        this.estación = estación;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Ticket)) return false;
        final Ticket other = (Ticket) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$cliente = this.getCliente();
        final Object other$cliente = other.getCliente();
        if (this$cliente == null ? other$cliente != null : !this$cliente.equals(other$cliente)) return false;
        final Object this$estación = this.getEstación();
        final Object other$estación = other.getEstación();
        if (this$estación == null ? other$estación != null : !this$estación.equals(other$estación)) return false;
        final Object this$fecha = this.getFecha();
        final Object other$fecha = other.getFecha();
        if (this$fecha == null ? other$fecha != null : !this$fecha.equals(other$fecha)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Ticket;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $cliente = this.getCliente();
        result = result * PRIME + ($cliente == null ? 43 : $cliente.hashCode());
        final Object $estación = this.getEstación();
        result = result * PRIME + ($estación == null ? 43 : $estación.hashCode());
        final Object $fecha = this.getFecha();
        result = result * PRIME + ($fecha == null ? 43 : $fecha.hashCode());
        return result;
    }

    public String toString() {
        return "Ticket(id=" + this.getId() + ", cliente=" + this.getCliente() + ", estación=" + this.getEstación() + ", fecha=" + this.getFecha() + ")";
    }
}