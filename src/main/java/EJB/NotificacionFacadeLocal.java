package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Notificacion;
import modelo.Usuario;

/**
 *
 * 
 */
@Local
public interface NotificacionFacadeLocal {

    void create(Notificacion notificacion);

    void edit(Notificacion notificacion);

    void remove(Notificacion notificacion);

    Notificacion find(Object id);

    List<Notificacion> findAll();

    List<Notificacion> findRange(int[] range);

    int count();

    public List<Notificacion> getList(Usuario usuario);
    
}
