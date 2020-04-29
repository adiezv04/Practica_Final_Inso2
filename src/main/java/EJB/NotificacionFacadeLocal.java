/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Notificacion;
import modelo.Usuario;

/**
 *
 * @author "adrian"
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
