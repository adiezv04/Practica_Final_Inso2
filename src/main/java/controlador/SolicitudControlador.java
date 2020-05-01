/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CitaFacadeLocal;
import EJB.DoctorFacadeLocal;
import EJB.NotificacionFacadeLocal;
import EJB.SolicitudFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import modelo.Notificacion;
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
    @EJB
    private NotificacionFacadeLocal notificacionEJB;
    
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
            cita.setFecha(buscaFecha(doctor, sol.getHorario()));
            citaEJB.create(cita);
            solicitudEJB.remove(sol);
            creaNotificacion(sol.getPaciente());
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }catch(IOException e){
            System.out.println("Error al crear cita. " + e.getMessage());
        }  
    }
    
    public void creaNotificacion(Paciente pac){
        Notificacion notificacion = new Notificacion();
        notificacion.setFecha(new Date());
        notificacion.setEmisor("Admin");
        notificacion.setLeida(false);
        notificacion.setUsuario(pac.getUsuario());
        notificacion.setTexto("Tiene una nueva cita.");
        notificacionEJB.create(notificacion);
    }
    
    public Date buscaFecha(Doctor doc, String horario){
        Date fecha = null;
        List<Cita> citasDoctor = citaEJB.buscarCitas(doc);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date hoy = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(hoy);
        c.add(Calendar.HOUR, 24);
        int unroundedMinutes = c.get(Calendar.MINUTE); 
        int mod = unroundedMinutes % 30; 
        c.add(Calendar.MINUTE, mod < 8 ? -mod : (30-mod));
        
        if(citasDoctor == null){
            switch (horario) {
                case "Ambas":          
                    fecha = c.getTime();
                break;
                case "Mañanas":
                    if(c.get(Calendar.HOUR_OF_DAY) >= 9 && c.get(Calendar.HOUR_OF_DAY) <= 13){
                        fecha = c.getTime();
                    }else{
                        c.add(Calendar.DAY_OF_MONTH, 1);
                        c.setTime(new Date(2020, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 9, 0));
                        fecha = c.getTime();
                    }     
                break;
                default:
                    if(c.get(Calendar.HOUR_OF_DAY) >= 16 && c.get(Calendar.HOUR_OF_DAY) <= 20){
                        fecha = c.getTime();
                    }else{
                        c.add(Calendar.DAY_OF_MONTH, 1);
                        c.setTime(new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 16, 0));
                        fecha = c.getTime();
                    } 
                break;
            }
        }else{
            switch (horario) {
                case "Ambas":
                    do{
                        boolean libre = true;
                        for(int i = 0; i < citasDoctor.size(); i++){
                            if(c.getTime().getDate() == citasDoctor.get(i).getFecha().getDate() && c.getTime().getHours() == citasDoctor.get(i).getFecha().getHours() && c.getTime().getMinutes() == citasDoctor.get(i).getFecha().getMinutes() && c.getTime().getMonth() == citasDoctor.get(i).getFecha().getMonth()){
                                libre = false;
                            }
                        }
                        if(libre == true && (c.get(Calendar.HOUR_OF_DAY) >= 9 && c.get(Calendar.HOUR_OF_DAY) <= 20)) {
                            fecha = c.getTime();
                        }else{
                            c.add(Calendar.MINUTE, 30);
                        }
                    }while(fecha == null);
                break;
                case "Mañanas":
                    do{
                        boolean libre = true;
                        for(int i = 0; i < citasDoctor.size(); i++){
                            if(c.getTime().getDate() == citasDoctor.get(i).getFecha().getDate() && c.getTime().getHours() == citasDoctor.get(i).getFecha().getHours() && c.getTime().getMinutes() == citasDoctor.get(i).getFecha().getMinutes() && c.getTime().getMonth() == citasDoctor.get(i).getFecha().getMonth()){
                                libre = false;
                            }
                        }
                        if(libre == true && (c.get(Calendar.HOUR_OF_DAY) >= 9 && c.get(Calendar.HOUR_OF_DAY) <= 13)) {
                            fecha = c.getTime();
                        }else{
                            c.add(Calendar.MINUTE, 30);
                        }
                    }while(fecha == null);
                break;
                default:
                    do{
                        boolean libre = true;
                        for(int i = 0; i < citasDoctor.size(); i++){
                            if(c.getTime().getDate() == citasDoctor.get(i).getFecha().getDate() && c.getTime().getHours() == citasDoctor.get(i).getFecha().getHours() && c.getTime().getMinutes() == citasDoctor.get(i).getFecha().getMinutes() && c.getTime().getMonth() == citasDoctor.get(i).getFecha().getMonth()){
                                libre = false;
                            }
                        }
                        if(libre == true && (c.get(Calendar.HOUR_OF_DAY) >= 16 && c.get(Calendar.HOUR_OF_DAY) <= 20)) {
                            fecha = c.getTime();
                        }else{
                            c.add(Calendar.MINUTE, 30);
                        }
                    }while(fecha == null);
                break;
            }
        }
        return fecha;
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
