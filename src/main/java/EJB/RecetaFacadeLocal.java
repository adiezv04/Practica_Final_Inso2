package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Paciente;
import modelo.Receta;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Local
public interface RecetaFacadeLocal {

    void create(Receta receta);

    void edit(Receta receta);

    void remove(Receta receta);

    Receta find(Object id);

    List<Receta> findAll();

    List<Receta> findRange(int[] range);

    int count();
    
    List<Receta> recetasUsuario(Paciente paciente);
    
}
