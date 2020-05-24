package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Planta;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Stateless
public class PlantaFacade extends AbstractFacade<Planta> implements PlantaFacadeLocal {

    @PersistenceContext(unitName = "hospitalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlantaFacade() {
        super(Planta.class);
    }
    
}
