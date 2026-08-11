package crud;

import crud.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import objetos.Usuario;


public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;

    public EntityManager getEntityManager() {
        return logica.LogicaConexion.getMan();
    }

    //CRUD USUARIO CREAR
    public void create(Usuario usuario) {

        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

    }

    //CRUD USUARIO EDITAR
    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {

        em = getEntityManager();
        em.getTransaction().begin();
        usuario = em.merge(usuario);
        em.getTransaction().commit();

    }

    //CRUD USUARIO DESTUIR
    public void destroy(int id) throws NonexistentEntityException {

        em = getEntityManager();
        em.getTransaction().begin();
        Usuario usuario;

        usuario = em.getReference(Usuario.class, id);
        usuario.getId();

        em.remove(usuario);
        em.getTransaction().commit();

    }

    //CRUD USUARIO BUSCAR TODOS 
    public List<Usuario> findUsuarioEntities() {
        EntityManager em = getEntityManager();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Usuario.class));
        Query q = em.createQuery(cq);

        return q.getResultList();

    }

    //CRUD USUARIO BUSCAR UNO
    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();

        return em.find(Usuario.class, id);

    }

    //CRUD USUARIOS CONTAR
    public int getUsuarioCount() {
        EntityManager em = getEntityManager();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Usuario> rt = cq.from(Usuario.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();

    }

}
