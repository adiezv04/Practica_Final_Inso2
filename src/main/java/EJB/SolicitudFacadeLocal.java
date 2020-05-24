package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Solicitud;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Local
public interface SolicitudFacadeLocal {

    void create(Solicitud solicitud);

    void edit(Solicitud solicitud);

    void remove(Solicitud solicitud);

    Solicitud find(Object id);

    List<Solicitud> findAll();

    List<Solicitud> findRange(int[] range);

    int count();
    
}
