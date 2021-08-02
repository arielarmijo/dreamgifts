package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ArticuloDao implements DAO<Articulo> {

    private final EntityManagerFactory emf;
    
    public ArticuloDao() {
        this( Persistence.createEntityManagerFactory("dreamgifts"));
    }

    public ArticuloDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    
    @Override
    public List<Articulo> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Articulo> articulos;
        Query query = em.createQuery("SELECT a FROM Articulo a");
        articulos = query.getResultList();
        em.close();
        return articulos;
    }

    @Override
    public List<Articulo> findByTermLike(String term) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT a FROM Articulo a WHERE UPPER(a.codigo) LIKE UPPER(:term) OR UPPER(a.nombre) LIKE UPPER(:term)");
        query.setParameter("term", "%" + term + "%");
        List<Articulo> articulos = query.getResultList();
        em.close();
        return articulos;
    }

    @Override
    public Articulo findById(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT a FROM Articulo a WHERE a.id = :id");
        query.setParameter("id", id);
        Articulo articulo = (Articulo) query.getSingleResult();
        em.close();
        return articulo;
    }

    @Override
    public void save(Articulo a) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();  
        em.persist(a);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Articulo a) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Articulo articulo = em.find(Articulo.class, a.getId());
        articulo.setNombre(a.getNombre());
        articulo.setMarca(a.getMarca());
        articulo.setStock(a.getStock());
        articulo.setFechaVencimiento(a.getFechaVencimiento());
        articulo.setEstado(a.getEstado());
        articulo.setCategoriaArticulo(a.getCategoriaArticulo());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Query query = em.createQuery("SELECT a FROM Articulo a WHERE a.id IN :ids");
        query.setParameter("ids", ids);
        List<Articulo> articulos = query.getResultList();
        articulos.forEach(a -> {
            a.setEstado(estado);
        });
        em.getTransaction().commit();
        em.close();
    }
    
}
