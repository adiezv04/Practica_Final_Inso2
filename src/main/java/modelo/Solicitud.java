/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author "adrian"
 */

@Entity
@Table(name="solicitud")
public class Solicitud implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idSolicitud;
    
    @JoinColumn(name="idPaciente")
    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REMOVE})
    private Paciente paciente;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="horario")
    private String horario;

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.idSolicitud;
        hash = 59 * hash + Objects.hashCode(this.paciente);
        hash = 59 * hash + Objects.hashCode(this.descripcion);
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
        final Solicitud other = (Solicitud) obj;
        if (this.idSolicitud != other.idSolicitud) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "idSolicitud=" + idSolicitud + ", paciente=" + paciente + ", descripcion=" + descripcion + '}';
    }
}
