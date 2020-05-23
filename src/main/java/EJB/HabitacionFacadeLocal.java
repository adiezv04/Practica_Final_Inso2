/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Doctor;
import modelo.Habitacion;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Local
public interface HabitacionFacadeLocal {

    void create(Habitacion habitacion);

    void edit(Habitacion habitacion);

    void remove(Habitacion habitacion);

    Habitacion find(Object id);

    List<Habitacion> findAll();

    List<Habitacion> findRange(int[] range);

    int count();

    public List<Habitacion> buscaHabitaciones(Doctor doctor);
    
}
