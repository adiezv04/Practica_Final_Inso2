package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Usuario;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    Usuario verificarUsuario(Usuario user);
    
    boolean usuarioDisponible(Usuario user);
}
