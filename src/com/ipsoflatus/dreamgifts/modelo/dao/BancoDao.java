package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BancoDao implements DAO<Banco> {

    private final EntityManagerFactory emf;
    
    public BancoDao() {
        this( Persistence.createEntityManagerFactory("dreamgifts"));
    }

    public BancoDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public List<Banco> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Banco> bancos;
        Query query = em.createQuery("SELECT b FROM Banco b");
        bancos = query.getResultList();
        em.close();
        return bancos;
    }

    @Override
    public List<Banco> findByTermLike(String term) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT b FROM Banco b WHERE UPPER(b.codigo) LIKE UPPER(:term) OR UPPER(b.nombre) LIKE UPPER(:term)");
        query.setParameter("term", "%" + term + "%");
        List<Banco> bancos = query.getResultList();
        em.close();
        return bancos;
    }

    @Override
    public Banco findById(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT b FROM Banco b WHERE b.id = :id");
        query.setParameter("id", id);
        Banco banco = (Banco) query.getSingleResult();
        em.close();
        return banco;
    }

    @Override
    public void save(Banco b) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();  
        em.persist(b);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Banco b) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Banco banco = em.find(Banco.class, b.getId());
        banco.setCodigo(b.getCodigo());
        banco.setNombre(b.getNombre());
        banco.setEstado(b.getEstado());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Query query = em.createQuery("SELECT b FROM Banco b WHERE b.id IN :ids");
        query.setParameter("ids", ids);
        List<Banco> bancos = query.getResultList();
        bancos.forEach(b -> {
            b.setEstado(estado);
        });
        em.getTransaction().commit();
        em.close();
    }

}
