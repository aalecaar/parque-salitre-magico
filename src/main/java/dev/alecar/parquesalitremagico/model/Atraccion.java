package dev.alecar.parquesalitremagico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

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

    protected boolean canEqual(final Object other) {
        return other instanceof Atraccion;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getClasificacion() {
        return this.clasificacion;
    }

    public String getCondicionesUso() {
        return this.condicionesUso;
    }

    public boolean isDisponible() {
        return this.disponible;
    }

    public int getEstaturaMinima() {
        return this.estaturaMinima;
    }

    public Estacion getEstacion() {
        return this.estacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setCondicionesUso(String condicionesUso) {
        this.condicionesUso = condicionesUso;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setEstaturaMinima(int estaturaMinima) {
        this.estaturaMinima = estaturaMinima;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Atraccion)) return false;
        final Atraccion other = (Atraccion) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) return false;
        final Object this$descripcion = this.getDescripcion();
        final Object other$descripcion = other.getDescripcion();
        if (this$descripcion == null ? other$descripcion != null : !this$descripcion.equals(other$descripcion))
            return false;
        final Object this$clasificacion = this.getClasificacion();
        final Object other$clasificacion = other.getClasificacion();
        if (this$clasificacion == null ? other$clasificacion != null : !this$clasificacion.equals(other$clasificacion))
            return false;
        final Object this$condicionesUso = this.getCondicionesUso();
        final Object other$condicionesUso = other.getCondicionesUso();
        if (this$condicionesUso == null ? other$condicionesUso != null : !this$condicionesUso.equals(other$condicionesUso))
            return false;
        if (this.isDisponible() != other.isDisponible()) return false;
        if (this.getEstaturaMinima() != other.getEstaturaMinima()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        final Object $descripcion = this.getDescripcion();
        result = result * PRIME + ($descripcion == null ? 43 : $descripcion.hashCode());
        final Object $clasificacion = this.getClasificacion();
        result = result * PRIME + ($clasificacion == null ? 43 : $clasificacion.hashCode());
        final Object $condicionesUso = this.getCondicionesUso();
        result = result * PRIME + ($condicionesUso == null ? 43 : $condicionesUso.hashCode());
        result = result * PRIME + (this.isDisponible() ? 79 : 97);
        result = result * PRIME + this.getEstaturaMinima();
        return result;
    }

    public String toString() {
        return "Atraccion(id=" + this.getId() + ", nombre=" + this.getNombre() + ", descripcion=" + this.getDescripcion() + ", clasificacion=" + this.getClasificacion() + ", condicionesUso=" + this.getCondicionesUso() + ", disponible=" + this.isDisponible() + ", estaturaMinima=" + this.getEstaturaMinima() + ")";
    }
}