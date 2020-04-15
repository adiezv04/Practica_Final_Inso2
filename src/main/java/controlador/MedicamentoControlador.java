/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.MedicamentoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Medicamento;

/**
 *
 * @author "adrian"
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
