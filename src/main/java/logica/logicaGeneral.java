package logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class logicaGeneral {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
    private static EntityManager man = emf.createEntityManager();
    
}
