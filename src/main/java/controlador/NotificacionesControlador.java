package controlador;

import EJB.NotificacionFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import modelo.Doctor;
import modelo.Notificacion;
import modelo.Paciente;
import modelo.Usuario;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class NotificacionesControlador implements Serializable{
    
    private List<Notificacion> listaNotificaciones;
    
    @EJB
    private NotificacionFacadeLocal notifiacionEJB;
    
    private Usuario usuario;
    private Doctor doctor;
    private Paciente paciente;
    
    
    @PostConstruct
    public void inicia(){
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        if(usuario.getTipoUsuario().equals("doctor")){
            doctor = (Doctor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("doctor");
        }else if(usuario.getTipoUsuario().equals("paciente")){
            paciente = (Paciente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("paciente");
        }
        listaNotificaciones = notifiacionEJB.getList(usuario);
    }
    
    public void borrar(Notificacion notif) throws IOException{
        notifiacionEJB.remove(notif);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public List<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }

    public void setListaNotificaciones(List<Notificacion> listaNotificaciones) {
        this.listaNotificaciones = listaNotificaciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
}
