package controlador;

import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class IndexControlador implements Serializable{
    private Usuario usuario;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void inicia(){
        usuario = new Usuario();
    }
    
    public String verificarUsuario(){
        Usuario user = usuarioEJB.verificarUsuario(usuario);
        if(user == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error ", "Nombre de usuario y/o contraseña incorrecto/s."));
            return "";
        }else{
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
            String ruta = "";
            
            switch (user.getTipoUsuario()) {
                case "paciente":
                    ruta = "private/paciente/principal.xhtml";
                    break;
                case "doctor":
                    ruta = "private/doctor/principal.xhtml";
                    break;
                case "admin":
                    ruta = "private/admin/principal.xhtml";
                    break;
            }
            return ruta;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
