package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Planta;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Local
public interface PlantaFacadeLocal {

    void create(Planta planta);

    void edit(Planta planta);

    void remove(Planta planta);

    Planta find(Object id);

    List<Planta> findAll();

    List<Planta> findRange(int[] range);

    int count();
    
}
