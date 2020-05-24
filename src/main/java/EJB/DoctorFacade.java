package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Doctor;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Stateless
public class DoctorFacade extends AbstractFacade<Doctor> implements DoctorFacadeLocal {

    @PersistenceContext(unitName = "hospitalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DoctorFacade() {
        super(Doctor.class);
    }
    
}
