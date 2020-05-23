package controlador;

import EJB.HabitacionFacadeLocal;
import EJB.NotificacionFacadeLocal;
import EJB.PacienteFacadeLocal;
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
import modelo.Habitacion;
import utils.CrearNotificacion;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class PacientesControlador implements Serializable{
    
    @EJB
    private HabitacionFacadeLocal habitacionEJB;
    @EJB
    private PacienteFacadeLocal pacienteEJB;
    @EJB
    NotificacionFacadeLocal notificacionEJB;
    
    private List<Habitacion> listaHabitaciones;
    private Doctor doctor;
    
    @PostConstruct
    public void inicia(){
        doctor = (Doctor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("doctor");
        listaHabitaciones = habitacionEJB.buscaHabitaciones(doctor);
    }
    
    public void darAlta(Habitacion habitacion){
        try{
            CrearNotificacion not = new CrearNotificacion();
            not.crea(habitacion.getPaciente().getUsuario(), "Dr. " + doctor.getApellidos(), "Usted ha sido dado de alta. ", notificacionEJB);
            
            habitacion.getPaciente().setIngresado(false);
            pacienteEJB.edit(habitacion.getPaciente());
            habitacion.setDoctor(null);
            habitacion.setPaciente(null);
            habitacion.setDetalles(null);
            habitacionEJB.edit(habitacion);
                    
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }catch(IOException e){
            System.out.println("Error al dar alta, " + e.getMessage());
        }
    }

    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(List<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    
}
