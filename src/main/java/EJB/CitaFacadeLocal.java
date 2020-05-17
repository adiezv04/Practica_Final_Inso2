/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Cita;
import modelo.Doctor;
import modelo.Paciente;

/**
 *
 * @author "adrian"
 */
@Local
public interface CitaFacadeLocal {

    void create(Cita cita);

    void edit(Cita cita);

    void remove(Cita cita);

    Cita find(Object id);

    List<Cita> findAll();

    List<Cita> findRange(int[] range);

    int count();

    public List<Cita> buscarCitas(Doctor doc);

    public List<Cita> buscarCita(Paciente pac);
    
}
