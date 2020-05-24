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
