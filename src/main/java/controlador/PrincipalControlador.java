package controlador;

import EJB.DoctorFacadeLocal;
import EJB.PacienteFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Doctor;
import modelo.Paciente;
import modelo.Usuario;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class PrincipalControlador implements Serializable{
    private Usuario usuario;
    private Paciente paciente;
    private Doctor doctor;
    
    @EJB
    private PacienteFacadeLocal pacienteEJB;
    
    @EJB
    private DoctorFacadeLocal doctorEJB;
    
    private List<Paciente> listaPacientes;
    private List<Doctor> listaDoctores;
    
    @PostConstruct
    public void inicia(){
        listaPacientes = pacienteEJB.findAll();
        listaDoctores = doctorEJB.findAll();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        if(usuario.getTipoUsuario().equals("paciente")){
            buscaPaciente();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("paciente", paciente);
        }else if(usuario.getTipoUsuario().equals("doctor")){
            buscaDoctor();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("doctor", doctor);
        }
        
    }
    
    public String cerrarSesion() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml";
    }
    
    public void buscaPaciente(){
        for(int i = 0; i < listaPacientes.size(); i++){
            if(listaPacientes.get(i).getUsuario().getIdUsuario() == usuario.getIdUsuario()){
                paciente = listaPacientes.get(i);
                break;
            }
        }
    }
    
    public void buscaDoctor(){
        for(int i = 0; i < listaDoctores.size(); i++){
            if(listaDoctores.get(i).getUsuario().getIdUsuario() == usuario.getIdUsuario()){
                doctor = listaDoctores.get(i);
                break;
            }
        }
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public List<Doctor> getListaDoctores() {
        return listaDoctores;
    }

    public void setListaDoctores(List<Doctor> listaDoctores) {
        this.listaDoctores = listaDoctores;
    }
    
    
}
