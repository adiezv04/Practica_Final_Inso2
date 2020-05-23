/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.NotificacionFacadeLocal;
import EJB.SolicitudFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Paciente;
import modelo.Solicitud;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class SolicitudControlador implements Serializable{
   
    @EJB
    private SolicitudFacadeLocal solicitudEJB;
    @EJB
    private NotificacionFacadeLocal notificacionEJB;
    private Solicitud solicitud;
    private Paciente paciente;

    @PostConstruct
    public void inicia(){
        paciente = (Paciente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("paciente");
        solicitud = new Solicitud();
    }
   
    public void crearSolicitud(){
        try{
            solicitud.setPaciente(paciente);
            solicitudEJB.create(solicitud);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cita solicitada con éxito."));
        }catch(Exception e){
            System.err.println("Error al crear solicitud "+e.getMessage());
        }
    }
    
    public void ayuda(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ayuda", "Para pedir cita simplemente describa su situación en el texto de abajo y pulse el botón. Lo más rápido posible le notificaremos la fecha de su cita con el doctor que consideremos adecuado según su descripción."));
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
