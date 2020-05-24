package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Paciente;
import modelo.Receta;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Stateless
public class RecetaFacade extends AbstractFacade<Receta> implements RecetaFacadeLocal {

    @PersistenceContext(unitName = "hospitalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecetaFacade() {
        super(Receta.class);
    }

    @Override
    public List<Receta> recetasUsuario(Paciente paciente) {
        String consulta = "FROM Receta r WHERE r.paciente=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", paciente);
        
        List<Receta> resultado = query.getResultList();
        
        if(resultado.isEmpty()){
            return null;
        }else{
            return resultado;
        }
    }
    
}
