package dev.alecar.parquesalitremagico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

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

    protected boolean canEqual(final Object other) {
        return other instanceof Cliente;
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

    public int getEstatura() {
        return this.estatura;
    }

    public int getEdad() {
        return this.edad;
    }

    public String getFamiliarContacto() {
        return this.familiarContacto;
    }

    public boolean isPrimeraVisita() {
        return this.primeraVisita;
    }

    public Estacion getEstacionRegistro() {
        return this.estacionRegistro;
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

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setFamiliarContacto(String familiarContacto) {
        this.familiarContacto = familiarContacto;
    }

    public void setPrimeraVisita(boolean primeraVisita) {
        this.primeraVisita = primeraVisita;
    }

    public void setEstacionRegistro(Estacion estacionRegistro) {
        this.estacionRegistro = estacionRegistro;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Cliente)) return false;
        final Cliente other = (Cliente) o;
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
        if (this.getEstatura() != other.getEstatura()) return false;
        if (this.getEdad() != other.getEdad()) return false;
        final Object this$familiarContacto = this.getFamiliarContacto();
        final Object other$familiarContacto = other.getFamiliarContacto();
        if (this$familiarContacto == null ? other$familiarContacto != null : !this$familiarContacto.equals(other$familiarContacto))
            return false;
        if (this.isPrimeraVisita() != other.isPrimeraVisita()) return false;
        return true;
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
        result = result * PRIME + this.getEstatura();
        result = result * PRIME + this.getEdad();
        final Object $familiarContacto = this.getFamiliarContacto();
        result = result * PRIME + ($familiarContacto == null ? 43 : $familiarContacto.hashCode());
        result = result * PRIME + (this.isPrimeraVisita() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "Cliente(id=" + this.getId() + ", nombre=" + this.getNombre() + ", cedula=" + this.getCedula() + ", telefono=" + this.getTelefono() + ", correo=" + this.getCorreo() + ", estatura=" + this.getEstatura() + ", edad=" + this.getEdad() + ", familiarContacto=" + this.getFamiliarContacto() + ", primeraVisita=" + this.isPrimeraVisita() + ")";
    }
}