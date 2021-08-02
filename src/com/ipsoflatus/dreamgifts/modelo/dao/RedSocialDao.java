package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.RedSocial;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class RedSocialDao implements DAO<RedSocial> {

    private final EntityManagerFactory emf;

    public RedSocialDao() {
        this(Persistence.createEntityManagerFactory("dreamgifts"));
    }

    public RedSocialDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public List<RedSocial> findAll() {
        EntityManager em = emf.createEntityManager();
        List<RedSocial> rrss;
        String sql = "SELECT rs FROM RedSocial rs";
        Query query = em.createQuery(sql);
        rrss = query.getResultList();
        em.close();
        return rrss;
    }

    @Override
    public List<RedSocial> findByTermLike(String term) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT rs FROM RedSocial rs WHERE UPPER(rs.codigo) LIKE UPPER(:term) OR UPPER(rs.nombre) LIKE UPPER(:term)");
        query.setParameter("term", "%" + term + "%");
        List<RedSocial> rrss = query.getResultList();
        em.close();
        return rrss;
    }

    @Override
    public RedSocial findById(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT rs FROM RedSocial rs WHERE rs.id = :id");
        query.setParameter("id", id);
        RedSocial rs = (RedSocial) query.getSingleResult();
        em.close();
        return rs;
    }

    @Override
    public void save(RedSocial rs) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();  
        em.persist(rs);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(RedSocial rs) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        RedSocial redSocial = em.find(RedSocial.class, rs.getId());
        redSocial.setCodigo(rs.getCodigo());
        redSocial.setNombre(rs.getNombre());
        redSocial.setEstado(rs.getEstado());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Query query = em.createQuery("SELECT rs FROM RedSocial rs WHERE rs.id IN :ids");
        query.setParameter("ids", ids);
        List<RedSocial> rrss = query.getResultList();
        rrss.forEach(rs -> {
            rs.setEstado(estado);
        });
        em.getTransaction().commit();
        em.close();
    }
    
}
