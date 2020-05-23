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
 * author "Adrián" , "Daniel"
 */

@Entity
@Table(name="cita")
public class Cita implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idCita;
    
    @JoinColumn(name="idDoctor")
    @ManyToOne(cascade=CascadeType.MERGE)
    private Doctor doctor;
    
    @JoinColumn(name="idPaciente")
    @ManyToOne(cascade=CascadeType.MERGE)
    private Paciente paciente;
    
    @Column(name="fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.idCita;
        hash = 83 * hash + Objects.hashCode(this.doctor);
        hash = 83 * hash + Objects.hashCode(this.paciente);
        hash = 83 * hash + Objects.hashCode(this.fecha);
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
        final Cita other = (Cita) obj;
        if (this.idCita != other.idCita) {
            return false;
        }
        if (!Objects.equals(this.doctor, other.doctor)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cita{" + "idCita=" + idCita + ", doctor=" + doctor + ", paciente=" + paciente + ", fecha=" + fecha + '}';
    }
}
