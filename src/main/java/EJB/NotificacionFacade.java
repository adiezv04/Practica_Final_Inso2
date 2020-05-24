package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Notificacion;
import modelo.Usuario;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Stateless
public class NotificacionFacade extends AbstractFacade<Notificacion> implements NotificacionFacadeLocal {

    @PersistenceContext(unitName = "hospitalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificacionFacade() {
        super(Notificacion.class);
    }

    @Override
    public List<Notificacion> getList(Usuario usuario) {
        String consulta = "FROM Notificacion n WHERE n.usuario=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", usuario);
        
        List<Notificacion> resultado = query.getResultList();
        
        if(resultado.isEmpty()){
            return null;
        }else{
            return resultado;
        }
    }
    
}
