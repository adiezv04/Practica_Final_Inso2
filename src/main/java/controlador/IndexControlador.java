/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author "adrian"
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
            return "private/principal.xhtml";
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
