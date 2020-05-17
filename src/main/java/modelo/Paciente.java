/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author "adrian"
 */
@Entity
@Table(name="paciente")
public class Paciente implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPaciente;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellidos")
    private String apellidos;
    
    @Column(name="dni")
    private String dni;
    
    @Column(name="fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    @Column(name="ingresado")
    private boolean ingresado;
    
    @JoinColumn(name="idUsuario")
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Usuario usuario;

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isIngresado() {
        return ingresado;
    }

    public void setIngresado(boolean ingresado) {
        this.ingresado = ingresado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idPaciente;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.apellidos);
        hash = 97 * hash + Objects.hashCode(this.dni);
        hash = 97 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 97 * hash + (this.ingresado ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        if (this.idPaciente != other.idPaciente) {
            return false;
        }
        if (this.ingresado != other.ingresado) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Paciente{" + "idPaciente=" + idPaciente + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", ingresado=" + ingresado + ", usuario=" + usuario + '}';
    }
}
