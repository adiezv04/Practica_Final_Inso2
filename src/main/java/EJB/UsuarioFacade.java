package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Usuario;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "hospitalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario verificarUsuario(Usuario user){
        String consulta = "FROM Usuario u WHERE u.nombreUsuario=:param1 and u.clave=:param2";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", user.getNombreUsuario());
        query.setParameter("param2", user.getClave());
        
        List<Usuario> resultado = query.getResultList();
        
        if(resultado.isEmpty()){
            return null;
        }else{
            return resultado.get(0);
        }
    }
    
    @Override
    public boolean usuarioDisponible(Usuario user){
        String consulta = "FROM Usuario u WHERE u.nombreUsuario=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", user.getNombreUsuario());
        
        List<Usuario> resultado = query.getResultList();
        
        return resultado.isEmpty();
    }
}
