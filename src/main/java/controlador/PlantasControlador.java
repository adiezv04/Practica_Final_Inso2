package controlador;

import EJB.HabitacionFacadeLocal;
import EJB.PlantaFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import modelo.Habitacion;
import modelo.Planta;

/**
 *
 * @author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class PlantasControlador implements Serializable{
    @EJB
    private PlantaFacadeLocal plantaEJB;
    @EJB
    private HabitacionFacadeLocal habitacionEJB;
    
    private List<Planta> listaPlantas;
    private List<Habitacion> listaHabitaciones;
    
    @PostConstruct
    public void inicia(){
        listaPlantas = plantaEJB.findAll();
        listaHabitaciones = habitacionEJB.findAll();
    }
    
    public List<Habitacion> buscaHabitaciones(int idPlanta){
        List<Habitacion> lista = new ArrayList<>();
        for (Habitacion habitacion : listaHabitaciones) {
            if(habitacion.getPlanta().getIdPlanta() == idPlanta){
                lista.add(habitacion);
            }
        }
        return lista;
    }
    
    public void nuevaHabitacion(Planta planta) throws IOException{
        Habitacion habitacion = new Habitacion();
        
        habitacion.setPlanta(planta);
        
        
        List<Habitacion> lista = new ArrayList<>();
        for (Habitacion hab : listaHabitaciones) {
            if(hab.getPlanta().getIdPlanta() == planta.getIdPlanta()){
                lista.add(hab);
            }
        }
        
        habitacion.setNumero(lista.get(lista.size()-1).getNumero() + 1);
        
        habitacionEJB.create(habitacion);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public List<Planta> getListaPlantas() {
        return listaPlantas;
    }

    public void setListaPlantas(List<Planta> listaPlantas) {
        this.listaPlantas = listaPlantas;
    }

    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(List<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }
    
}
