package utils;

import EJB.NotificacionFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Notificacion;
import modelo.Usuario;

/**
 *
 * @author "adrian"
 */
public class CrearNotificacion{

    
    public void crea(Usuario usuario, String emisor, String texto, NotificacionFacadeLocal notificacionEJB){
        
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuario(usuario);
        notificacion.setEmisor(emisor);
        notificacion.setLeida(false);
        notificacion.setTexto(texto);
        notificacion.setFecha(new Date());

        notificacionEJB.create(notificacion);
    }
}
