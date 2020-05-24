package controlador;

import EJB.DoctorFacadeLocal;
import EJB.UsuarioFacadeLocal;
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
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class MedicoControlador implements Serializable{
    @EJB
    private DoctorFacadeLocal doctorEJB;
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    private Doctor doctor;
    private Usuario usuario;
    
    @PostConstruct
    public void inicia(){
        doctor = new Doctor();
        usuario = new Usuario();
    }
    
    public void crearDoctor(){
        if(usuarioEJB.usuarioDisponible(usuario)==false){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Nombre de usuario no disponible.", ""));
        }else{
            try{
                usuario.setTipoUsuario("doctor");
                doctor.setUsuario(usuario);
                doctorEJB.create(doctor);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info Doctor registrado con éxito.", ""));
            }catch(Exception e){
                System.err.println("Error al insertar doctor " + e.getMessage());
            }
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
