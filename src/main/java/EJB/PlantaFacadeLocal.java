/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Planta;

/**
 *
 * @author "adrian"
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
