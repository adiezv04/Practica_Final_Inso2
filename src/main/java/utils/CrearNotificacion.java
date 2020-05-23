package utils;

import EJB.NotificacionFacadeLocal;
import java.util.Date;
import modelo.Notificacion;
import modelo.Usuario;

/**
 *
 * author "Adrián" , "Daniel"
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
