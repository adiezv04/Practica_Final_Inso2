    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.MedicamentoFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import modelo.Medicamento;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped     
public class MedicamentoControlador implements Serializable{
    private List<Medicamento> listaMedicamentos;
    private Medicamento medicamento;
    
    @EJB
    private MedicamentoFacadeLocal medicamentoEJB;
    
    @PostConstruct
    public void incio(){
        medicamento = new Medicamento();
        listaMedicamentos = medicamentoEJB.findAll();
    }
    
    public void anadeMedicamento(){
        try{
            medicamentoEJB.create(medicamento);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }catch(IOException e){
            System.err.println("Error al insertar medicamento " + e.getMessage());
        }
    }
    
    public void eliminarMedicamento(Medicamento med) throws IOException{
        medicamentoEJB.remove(med);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public List<Medicamento> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public void setListaMedicamentos(List<Medicamento> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
}