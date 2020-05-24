package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Paciente;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Local
public interface PacienteFacadeLocal {

    void create(Paciente paciente);

    void edit(Paciente paciente);

    void remove(Paciente paciente);

    Paciente find(Object id);

    List<Paciente> findAll();

    List<Paciente> findRange(int[] range);

    int count();
    
}
