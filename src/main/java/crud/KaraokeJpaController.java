package crud;

import crud.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import objetos.Karaoke;


public class KaraokeJpaController implements Serializable {

    public KaraokeJpaController(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;

    public EntityManager getEntityManager() {
        return logica.LogicaConexion.getMan();
    }

    //CRUD KARAOKE CREAR
    public void create(Karaoke karaoke) {

        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(karaoke);
        em.getTransaction().commit();

    }

    //CRUD KARAOKE EDITAR
    public void edit(Karaoke karaoke) throws NonexistentEntityException, Exception {

        em = getEntityManager();
        em.getTransaction().begin();
        karaoke = em.merge(karaoke);
        em.getTransaction().commit();

    }

    //CRUD KARAOKE BORRAR
    public void destroy(int id) throws NonexistentEntityException {

        em = getEntityManager();
        em.getTransaction().begin();
        Karaoke karaoke;

        karaoke = em.getReference(Karaoke.class, id);
        karaoke.getId();

        em.remove(karaoke);
        em.getTransaction().commit();

    }

    //CRUD KARAOKE BUSCAR TODOS
    public List<Karaoke> findKaraokeEntities() {
        EntityManager em = getEntityManager();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Karaoke.class));
        Query q = em.createQuery(cq);

        return q.getResultList();

    }

    //CRUD KARAOKE BUSCAR UNO
    public Karaoke findKaraoke(int id) {
        EntityManager em = getEntityManager();

        return em.find(Karaoke.class, id);

    }

    //CRUD KARAOKE CONTAR KARAOKES
    public int getKaraokeCount() {
        EntityManager em = getEntityManager();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Karaoke> rt = cq.from(Karaoke.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();

    }

}
