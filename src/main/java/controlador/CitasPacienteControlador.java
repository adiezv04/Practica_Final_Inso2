/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CitaFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import modelo.Cita;
import modelo.Paciente;

/**
 *
 * @author "adrian"
 */
@Named
@ViewScoped
public class CitasPacienteControlador implements Serializable{
    
    @EJB
    private CitaFacadeLocal citaEJB;
    private List<Cita> listaCitas;
    
    @PostConstruct
    public void inicia(){
        Paciente pac = (Paciente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("paciente");
        listaCitas = citaEJB.buscarCita(pac);
    }
    
    public void anularCita(Cita cita) throws IOException{
        citaEJB.remove(cita);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public String dameFecha(Date date){
        LocalDate localdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Locale esp = new Locale("es", "ES");
        String fecha = localdate.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy", esp));
        return fecha;
    }
    
    public String dameHora(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);   
        String fecha = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + ":";
        if(calendar.get(Calendar.MINUTE) < 10){
            fecha = fecha + "0" + String.valueOf(calendar.get(Calendar.MINUTE));
        }else{
            fecha = fecha + String.valueOf(calendar.get(Calendar.MINUTE));
        }
        return fecha;
    }

    public List<Cita> getListaCitas() {
        return listaCitas;
    }

    public void setListaCitas(List<Cita> listaCitas) {
        this.listaCitas = listaCitas;
    }
}
