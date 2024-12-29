package dev.alecar.parquesalitremagico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private String cargo;
    private String horario;

    public Empleado() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getCedula() {
        return this.cedula;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getCorreo() {
        return this.correo;
    }

    public String getCargo() {
        return this.cargo;
    }

    public String getHorario() {
        return this.horario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Empleado)) return false;
        final Empleado other = (Empleado) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) return false;
        final Object this$cedula = this.getCedula();
        final Object other$cedula = other.getCedula();
        if (this$cedula == null ? other$cedula != null : !this$cedula.equals(other$cedula)) return false;
        final Object this$telefono = this.getTelefono();
        final Object other$telefono = other.getTelefono();
        if (this$telefono == null ? other$telefono != null : !this$telefono.equals(other$telefono)) return false;
        final Object this$correo = this.getCorreo();
        final Object other$correo = other.getCorreo();
        if (this$correo == null ? other$correo != null : !this$correo.equals(other$correo)) return false;
        final Object this$cargo = this.getCargo();
        final Object other$cargo = other.getCargo();
        if (this$cargo == null ? other$cargo != null : !this$cargo.equals(other$cargo)) return false;
        final Object this$horario = this.getHorario();
        final Object other$horario = other.getHorario();
        if (this$horario == null ? other$horario != null : !this$horario.equals(other$horario)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Empleado;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        final Object $cedula = this.getCedula();
        result = result * PRIME + ($cedula == null ? 43 : $cedula.hashCode());
        final Object $telefono = this.getTelefono();
        result = result * PRIME + ($telefono == null ? 43 : $telefono.hashCode());
        final Object $correo = this.getCorreo();
        result = result * PRIME + ($correo == null ? 43 : $correo.hashCode());
        final Object $cargo = this.getCargo();
        result = result * PRIME + ($cargo == null ? 43 : $cargo.hashCode());
        final Object $horario = this.getHorario();
        result = result * PRIME + ($horario == null ? 43 : $horario.hashCode());
        return result;
    }

    public String toString() {
        return "Empleado(id=" + this.getId() + ", nombre=" + this.getNombre() + ", cedula=" + this.getCedula() + ", telefono=" + this.getTelefono() + ", correo=" + this.getCorreo() + ", cargo=" + this.getCargo() + ", horario=" + this.getHorario() + ")";
    }
}