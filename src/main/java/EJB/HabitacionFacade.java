package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Doctor;
import modelo.Habitacion;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Stateless
public class HabitacionFacade extends AbstractFacade<Habitacion> implements HabitacionFacadeLocal {

    @PersistenceContext(unitName = "hospitalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HabitacionFacade() {
        super(Habitacion.class);
    }

    @Override
    public List<Habitacion> buscaHabitaciones(Doctor doctor) {
        String consulta = "FROM Habitacion h WHERE h.doctor=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", doctor);
        
        List<Habitacion> resultado = query.getResultList();
        
        if(resultado.isEmpty()){
            return null;
        }else{
            return resultado;
        }
    }
    
}
