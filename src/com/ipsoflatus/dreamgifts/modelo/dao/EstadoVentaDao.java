package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EstadoVentaDao implements DAO<EstadoVenta> {

    private final EntityManagerFactory emf;

    public EstadoVentaDao() {
        this( Persistence.createEntityManagerFactory("dreamgifts"));
    }

    public EstadoVentaDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public List<EstadoVenta> findAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT ev FROM EstadoVenta ev");
        List<EstadoVenta> eevv = query.getResultList();
        em.close();
        return eevv;
    }

    @Override
    public List<EstadoVenta> findByTermLike(String term) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT ev FROM EstadoVenta ev WHERE UPPER(ev.codigo) LIKE UPPER(:term) OR UPPER(ev.nombre) LIKE UPPER(:term)");
        query.setParameter("term", "%" + term + "%");
        List<EstadoVenta> eevv = query.getResultList();
        em.close();
        return eevv;
    }

    @Override
    public EstadoVenta findById(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT ev FROM EstadoVenta ev WHERE ev.id = :id");
        query.setParameter("id", id);
        EstadoVenta ev = (EstadoVenta) query.getSingleResult();
        em.close();
        return ev;
    }

    @Override
    public void save(EstadoVenta ev) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ev);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(EstadoVenta ev) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        EstadoVenta estadoVenta = em.find(EstadoVenta.class, ev.getId());
        estadoVenta.setCodigo(ev.getCodigo());
        estadoVenta.setNombre(ev.getNombre());
        estadoVenta.setEstado(ev.getEstado());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Query query = em.createQuery("SELECT ev FROM EstadoVenta ev WHERE ev.id IN :ids");
        query.setParameter("ids", ids);
        List<EstadoVenta> eevv = query.getResultList();
        eevv.forEach(c -> {
            c.setEstado(estado);
        });
        em.getTransaction().commit();
        em.close();
    }

}
