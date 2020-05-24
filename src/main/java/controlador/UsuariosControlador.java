package controlador;

import EJB.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class UsuariosControlador implements Serializable{
    @EJB
    UsuarioFacadeLocal usuarioEJB;
    
    private List<Usuario> listaUsuarios;
    
    @PostConstruct
    public void inicia(){
        listaUsuarios = usuarioEJB.findAll();
    }
    
    public void eliminarUsuario(Usuario us){
        try{
            usuarioEJB.remove(us);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }catch(IOException e){
            System.out.println("Error al eliminar usuario " + e.getMessage());
        }
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

}
