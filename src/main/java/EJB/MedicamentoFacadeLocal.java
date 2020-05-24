package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Medicamento;

/**
 *
 * author "Adrián" , "Daniel"
 */
@Local
public interface MedicamentoFacadeLocal {

    void create(Medicamento medicamento);

    void edit(Medicamento medicamento);

    void remove(Medicamento medicamento);

    Medicamento find(Object id);

    List<Medicamento> findAll();

    List<Medicamento> findRange(int[] range);

    int count();
    
}
