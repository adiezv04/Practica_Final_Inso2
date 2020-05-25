package controlador;

import EJB.PacienteFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Paciente;
import modelo.Usuario;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class RegistroControlador implements Serializable{
    private Usuario usuario;
    private Paciente paciente;
    
    @EJB
    private PacienteFacadeLocal pacienteEJB;
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void inicia(){
        usuario = new Usuario();
        paciente = new Paciente();
    }
    
    public void registra(){
        if(Pattern.matches("\\d{8}[[A-H]|[J-N]|[P-T]|[V-Z]]", paciente.getDni()) == false){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error. Formato de DNI incorrecto. (La letra debe estar en mayúscula.)"));
        }else{
            if(usuarioEJB.usuarioDisponible(usuario) == false){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error. Nombre de usuario no disponible"));
            }else{
                try{
                    usuario.setTipoUsuario("paciente");
                    paciente.setUsuario(usuario);
                    paciente.setIngresado(false);
                    pacienteEJB.create(paciente);

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Info. Usuario registrado con éxito."));
                }catch(Exception e){
                    System.out.println("Error al insertar paciente "+e.getMessage());
                }  
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
}
