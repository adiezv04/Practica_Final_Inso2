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
 * author "Adrián" , "Daniel"
 */

@Entity
@Table(name="habitacion")
public class Habitacion implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idHabitacion;
    
    @Column(name="numero")
    private int numero;
    
    @JoinColumn(name="idPaciente")
    @OneToOne(cascade=CascadeType.PERSIST)
    private Paciente paciente;
    
    @JoinColumn(name="idPlanta")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Planta planta;
    
    @JoinColumn(name="idDoctor")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Doctor doctor;
    
    @Column
    private String detalles;

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idHabitacion;
        hash = 37 * hash + this.numero;
        hash = 37 * hash + Objects.hashCode(this.paciente);
        hash = 37 * hash + Objects.hashCode(this.planta);
        hash = 37 * hash + Objects.hashCode(this.doctor);
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
        final Habitacion other = (Habitacion) obj;
        if (this.idHabitacion != other.idHabitacion) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        if (!Objects.equals(this.planta, other.planta)) {
            return false;
        }
        if (!Objects.equals(this.doctor, other.doctor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "idHabitacion=" + idHabitacion + ", numero=" + numero + ", paciente=" + paciente + ", planta=" + planta + ", doctor=" + doctor + '}';
    }
}
