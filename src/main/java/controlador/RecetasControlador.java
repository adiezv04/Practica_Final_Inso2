/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.RecetaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Paciente;
import modelo.Receta;

/**
 *
 * @author "adrian"
 */
@Named
@ViewScoped
public class RecetasControlador implements Serializable{
    @EJB
    private RecetaFacadeLocal recetaEJB;
    
    private List<Receta> listaRecetas;
    
    @PostConstruct
    public void inicia(){
        Paciente pac = (Paciente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("paciente");
        listaRecetas = recetaEJB.recetasUsuario(pac);
    }

    public List<Receta> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }
    
     
}
