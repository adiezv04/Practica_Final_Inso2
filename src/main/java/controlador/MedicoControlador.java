/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.DoctorFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Doctor;
import modelo.Usuario;

/**
 *
 * @author "adrian"
 */
@Named
@ViewScoped
public class MedicoControlador implements Serializable{
    @EJB
    private DoctorFacadeLocal doctorEJB;
    
    private Doctor doctor;
    private Usuario usuario;
    
    @PostConstruct
    public void inicia(){
        doctor = new Doctor();
        usuario = new Usuario();
    }
    
    public void crearDoctor(){
        try{
            usuario.setTipoUsuario("doctor");
            doctor.setUsuario(usuario);
            doctorEJB.create(doctor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info Doctor registrado con éxito.", ""));
        }catch(Exception e){
            System.err.println("Error al insertar doctor " + e.getMessage());
        }
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
}
