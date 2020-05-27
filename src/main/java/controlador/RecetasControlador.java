package controlador;

import EJB.MedicamentoFacadeLocal;
import EJB.NotificacionFacadeLocal;
import EJB.PacienteFacadeLocal;
import EJB.RecetaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Doctor;
import modelo.Medicamento;
import modelo.Paciente;
import modelo.Receta;
import modelo.Usuario;
import utils.CrearNotificacion;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Named
@ViewScoped
public class RecetasControlador implements Serializable{
    @EJB
    private RecetaFacadeLocal recetaEJB;
    @EJB
    private PacienteFacadeLocal pacienteEJB;
    @EJB
    private MedicamentoFacadeLocal medicamentoEJB;
    @EJB
    private NotificacionFacadeLocal notificacionEJB;
    
    private List<Receta> listaRecetas;
    private List<Medicamento> listaMedicamentos;
    private List<Paciente> listaPacientes;
    
    private Doctor doctor;
    private Receta receta;
    private Paciente paciente;
    private Medicamento medicamento;
    
    @PostConstruct
    public void inicia(){
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(usuario.getTipoUsuario().equals("paciente")){
            paciente = (Paciente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("paciente");
            listaRecetas = recetaEJB.recetasUsuario(paciente);
        }else{
            receta = new Receta();
            listaMedicamentos = medicamentoEJB.findAll();
            listaPacientes = pacienteEJB.findAll();
            medicamento = new Medicamento();
            paciente = new Paciente();
            doctor = (Doctor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("doctor");
        } 
    }
    
    public void crearReceta(){
        try{
            for (Paciente pac : listaPacientes) {
                if(pac.getIdPaciente() == paciente.getIdPaciente()){
                    paciente = pac;
                    break;
                }
            }
            for (Medicamento med : listaMedicamentos) {
                if(med.getIdMedicamento() == medicamento.getIdMedicamento()){
                    medicamento = med;
                    break;
                }
            }
            receta.setMedicamento(medicamento);
            receta.setPaciente(paciente);
            recetaEJB.create(receta);
            CrearNotificacion not = new CrearNotificacion();
            not.crea(receta.getPaciente().getUsuario(), "Dr. " + doctor.getApellidos(), "Tiene una nueva receta.", notificacionEJB);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Receta creada correctamente."));
        }catch(Exception e){
            System.out.println("Error al insertar receta " + e.getMessage());
        }
    }

    public List<Receta> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    public List<Medicamento> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public void setListaMedicamentos(List<Medicamento> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
