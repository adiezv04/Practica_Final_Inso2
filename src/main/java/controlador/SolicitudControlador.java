/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CitaFacadeLocal;
import EJB.DoctorFacadeLocal;
import EJB.SolicitudFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import modelo.Cita;
import modelo.Doctor;
import modelo.Paciente;
import modelo.Solicitud;

/**
 *
 * @author "adrian"
 */
@Named
@ViewScoped
public class SolicitudControlador implements Serializable{
    private List<Solicitud> listaSolicitudes;
    private List<Doctor> listaDoctores;
    
    @EJB
    private SolicitudFacadeLocal solicitudEJB;
    @EJB
    private DoctorFacadeLocal doctorEJB;
    @EJB
    private CitaFacadeLocal citaEJB;
    
    private Cita cita;
    private Doctor doctor;
    
    @PostConstruct
    public void inicia(){
        listaSolicitudes = solicitudEJB.findAll();
        listaDoctores = doctorEJB.findAll();
        cita = new Cita();
        doctor = new Doctor();
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
            cita.setFecha(new Date(2020, 2, 12));
            citaEJB.create(cita);
            solicitudEJB.remove(sol);
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
