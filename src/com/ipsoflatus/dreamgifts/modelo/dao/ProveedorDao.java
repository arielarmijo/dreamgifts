package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProveedorDao implements DAO<Proveedor> {

    private final EntityManagerFactory emf;

    public ProveedorDao() {
        this(Persistence.createEntityManagerFactory("dreamgifts"));
    }

    public ProveedorDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Proveedor> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Proveedor> proveedores;
        Query query = em.createQuery("SELECT p FROM Proveedor p");
        proveedores = query.getResultList();
        em.close();
        return proveedores;
    }

    @Override
    public List<Proveedor> findByTermLike(String term) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Proveedor p WHERE UPPER(p.rut) LIKE UPPER(:term) OR UPPER(p.razonSocial) LIKE UPPER(:term)");
        query.setParameter("term", "%" + term + "%");
        List<Proveedor> proveedores = query.getResultList();
        em.close();
        return proveedores;
    }

    @Override
    public Proveedor findById(int id) {
        EntityManager em = emf.createEntityManager();
        Proveedor proveedor = em.find(Proveedor.class, id);
        em.close();
        return proveedor;
    }

    @Override
    public void save(Proveedor p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();  
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Proveedor p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Proveedor proveedor = em.find(Proveedor.class, p.getId());
        proveedor.setRut(p.getRut());
        proveedor.setRazonSocial(p.getRazonSocial());
        proveedor.setContacto(p.getContacto());
        proveedor.setDireccion(p.getDireccion());
        proveedor.setComuna(p.getComuna());
        proveedor.setTelefono(p.getTelefono());
        proveedor.setEmail(p.getEmail());
        proveedor.setEstado(p.getEstado());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Query query = em.createQuery("SELECT p FROM Proveedor p WHERE p.id IN :ids");
        query.setParameter("ids", ids);
        List<Proveedor> proveedores = query.getResultList();
        proveedores.forEach(p -> {
            p.setEstado(estado);
        });
        em.getTransaction().commit();
        em.close();
    }

}
