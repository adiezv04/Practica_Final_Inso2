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
@Table(name="receta")
public class Receta implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idReceta;
    
    @JoinColumn(name="idPaciente")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Paciente paciente;
    
    @JoinColumn(name="idMedicamento")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Medicamento medicamento;

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.idReceta;
        hash = 79 * hash + Objects.hashCode(this.paciente);
        hash = 79 * hash + Objects.hashCode(this.medicamento);
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
        final Receta other = (Receta) obj;
        if (this.idReceta != other.idReceta) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        if (!Objects.equals(this.medicamento, other.medicamento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Receta{" + "idReceta=" + idReceta + ", paciente=" + paciente + ", medicamento=" + medicamento + '}';
    }
}