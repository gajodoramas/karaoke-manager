package crud;

import crud.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import objetos.Cancion;


public class CancionJpaController implements Serializable {

    public CancionJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManager em;

    public EntityManager getEntityManager() {
        return logica.LogicaConexion.getMan();
    }

    //CRUD CANCION CREAR
    public void create(Cancion cancion) {

        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(cancion);
        em.getTransaction().commit();

    }
    
    //CRUD CANCION EDITAR
    public void edit(Cancion cancion) throws NonexistentEntityException, Exception {

        em = getEntityManager();
        em.getTransaction().begin();
        cancion = em.merge(cancion);
        em.getTransaction().commit();

    }

    //CRUD CANCION BORRAR
    public void destroy(int id) throws NonexistentEntityException {

        em = getEntityManager();
        em.getTransaction().begin();
        Cancion cancion;

        cancion = em.getReference(Cancion.class, id);
        cancion.getId();

        em.remove(cancion);
        em.getTransaction().commit();

    }

    //CRUD CANCION BUSCAR TODOS
    public List<Cancion> findCancionEntities() {
        EntityManager em = getEntityManager();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Cancion.class));
        Query q = em.createQuery(cq);

        return q.getResultList();

    }

    //CRUD CANCION BUSCAR 1
    public Cancion findCancion(int id) {
        EntityManager em = getEntityManager();

        return em.find(Cancion.class, id);

    }

    //CRUD CANCION OBTENER CUENTA
    public int getCancionCount() {
        EntityManager em = getEntityManager();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Cancion> rt = cq.from(Cancion.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();

    }

}
