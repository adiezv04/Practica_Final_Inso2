
package controlador;

import EJB.HabitacionFacadeLocal;
import EJB.NotificacionFacadeLocal;
import EJB.PacienteFacadeLocal;
import EJB.PlantaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Doctor;
import modelo.Habitacion;
import modelo.Paciente;
import modelo.Planta;
import utils.CrearNotificacion;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class IngresoControlador implements Serializable{
    @EJB
    PacienteFacadeLocal pacienteEJB;
    @EJB
    HabitacionFacadeLocal habitacionEJB;
    @EJB
    PlantaFacadeLocal plantaEJB;
    @EJB
    NotificacionFacadeLocal notificacionEJB;
    
    private List<Paciente> listaPacientes;
    private List<Habitacion> listaHabitaciones;
    private List<Planta> listaPlantas;
    private Paciente paciente;
    private Doctor doctor;
    private String detalles;
    
    @PostConstruct
    public void inicia(){
        listaPacientes = pacienteEJB.findAll();
        listaHabitaciones = habitacionEJB.findAll();
        listaPlantas = plantaEJB.findAll();
        doctor = (Doctor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("doctor");
        paciente = new Paciente();
    }
    
    public void ingresa(int numeroHabitacion){
        Habitacion habitacionObj = new Habitacion();
        boolean libre = true;
        for (Habitacion habitacion : listaHabitaciones) {
            if(habitacion.getNumero() == numeroHabitacion){
                if(habitacion.getPaciente()!=null){
                    libre = false;
                }else{
                    habitacionObj = habitacion;
                }
            }
        }
        
        for (Paciente pac : listaPacientes) {
            if(pac.getIdPaciente() == paciente.getIdPaciente()){
                paciente = pac;
                break;
            }
        }
        if(paciente.isIngresado()==false){
            if(libre==true){
                try{
                    paciente.setIngresado(true);
                    pacienteEJB.edit(paciente);
                    habitacionObj.setPaciente(paciente);
                    habitacionObj.setDoctor(doctor);
                    habitacionEJB.edit(habitacionObj);
                    
                    CrearNotificacion not = new CrearNotificacion();
                    not.crea(paciente.getUsuario(), "Dr. " + doctor.getApellidos(), "Usted debe ingresar en la habitacion " + habitacionObj.getNumero(), notificacionEJB);
                    
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Paciente ingresado correctamete."));
                }catch(Exception e){
                    System.out.println("Error al ingresar paciente en habitacion " + e.getMessage());
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Habitación ocupada, pruebe con otra."));
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este paciente ya está ingresado."));
        }  
    }
    
    public List<Habitacion> buscaHabitaciones(int idPlanta){
        List<Habitacion> lista = new ArrayList<>();
        for (Habitacion habitacion : listaHabitaciones) {
            if(habitacion.getPlanta().getIdPlanta() == idPlanta){
                lista.add(habitacion);
            }
        }
        return lista;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(List<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }

    public List<Planta> getListaPlantas() {
        return listaPlantas;
    }

    public void setListaPlantas(List<Planta> listaPlantas) {
        this.listaPlantas = listaPlantas;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

}
