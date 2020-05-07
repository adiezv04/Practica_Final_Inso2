/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Cita;
import modelo.Doctor;
import modelo.Paciente;
import modelo.Usuario;

/**
 *
 * @author "adrian"
 */
@Stateless
public class CitaFacade extends AbstractFacade<Cita> implements CitaFacadeLocal {

    @PersistenceContext(unitName = "hospitalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaFacade() {
        super(Cita.class);
    }

    @Override
    public List<Cita> buscarCitas(Doctor doc) {
        String consulta = "FROM Cita c WHERE c.doctor=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", doc);
        
        List<Cita> resultado = query.getResultList();
        
        if(resultado.isEmpty()){
            return null;
        }else{
            return resultado;
        }
    }

    @Override
    public List<Cita> buscarCita(Paciente pac) {
        String consulta = "FROM Cita c WHERE c.paciente=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", pac);
        
        List<Cita> resultado = query.getResultList();
        
        if(resultado.isEmpty()){
            return null;
        }else{
            return resultado;
        }
    }
    
}
