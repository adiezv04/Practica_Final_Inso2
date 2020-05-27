package controlador;

import EJB.CitaFacadeLocal;
import EJB.DoctorFacadeLocal;
import EJB.NotificacionFacadeLocal;
import EJB.SolicitudFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import modelo.Cita;
import modelo.Doctor;
import modelo.Solicitud;
import utils.FechasUtil;
import utils.CrearNotificacion;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class CitaControlador implements Serializable{
    private List<Solicitud> listaSolicitudes;
    private List<Doctor> listaDoctores;
    
    @EJB
    private SolicitudFacadeLocal solicitudEJB;
    @EJB
    private DoctorFacadeLocal doctorEJB;
    @EJB
    private CitaFacadeLocal citaEJB;
    @EJB
    private NotificacionFacadeLocal notificacionEJB;
    
    private Cita cita;
    private Doctor doctor;
    private FechasUtil fechaU;
    
    @PostConstruct
    public void inicia(){
        listaSolicitudes = solicitudEJB.findAll();
        listaDoctores = doctorEJB.findAll();
        cita = new Cita();
        doctor = new Doctor();
        fechaU = new FechasUtil();
    }
    
    public void creaCita(Solicitud sol){
        doctor.setIdDoctor(Integer.parseInt(sol.getDescripcion().substring(16, sol.getDescripcion().indexOf(','))));
        try{
            for (int i = 0; i < listaDoctores.size(); i++) {
                if(doctor.getIdDoctor() == listaDoctores.get(i).getIdDoctor()){
                    doctor = listaDoctores.get(i);
                    break;
                }
            }
            cita.setPaciente(sol.getPaciente());
            cita.setDoctor(doctor);
            
            cita.setFecha(fechaU.buscaFecha(doctor, sol.getHorario(), citaEJB));      
            
            citaEJB.create(cita);
            solicitudEJB.remove(sol);
            
            CrearNotificacion not = new CrearNotificacion();
            not.crea(sol.getPaciente().getUsuario(), "Admin", "Tiene una nueva cita.", notificacionEJB);
            not.crea(cita.getDoctor().getUsuario(), "Admin", "Tiene una nueva cita.", notificacionEJB);
            
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }catch(IOException e){
            System.out.println("Error al crear cita. " + e.getMessage());
        }  
    }
    
    public int getEdad(Date date){
       Calendar fechaNacimiento = Calendar.getInstance();

       Calendar fechaActual = Calendar.getInstance();

       fechaNacimiento.setTime(date);

       int año = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
       int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
       int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);

       if(mes<0 || (mes==0 && dia<0)){
           año--;
       }

       return año;
    }
    
    public void ayuda(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ayuda", "Se mostrarán todas las solicitudes realizadas por pacientes para obtener una cita, deberá de asignarle a cada uno un doctor acorde a su descripción."));
    }

    public List<Solicitud> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<Solicitud> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public List<Doctor> getListaDoctores() {
        return listaDoctores;
    }

    public void setListaDoctores(List<Doctor> listaDoctores) {
        this.listaDoctores = listaDoctores;
    }
    
    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
