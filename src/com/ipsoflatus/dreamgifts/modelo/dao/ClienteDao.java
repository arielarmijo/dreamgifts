package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ClienteDao implements DAO<Cliente> {

    private final EntityManagerFactory emf;

    public ClienteDao() {
        this( Persistence.createEntityManagerFactory("dreamgifts"));
    }

    public ClienteDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public Cliente findByRut(String rut) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.rut = :rut");
        query.setParameter("rut", rut);
        Cliente cliente = (Cliente) query.getSingleResult();
        em.close();
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Cliente> clientes;
        String sql = "SELECT c FROM Cliente c";
        Query query = em.createQuery(sql);
        clientes = query.getResultList();
        em.close();
        return clientes;
    }

    @Override
    public List<Cliente> findByTermLike(String term) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE UPPER(c.nombre) LIKE UPPER(:term) OR UPPER(c.apellido) LIKE UPPER(:term) OR c.rut LIKE :term");
        query.setParameter("term", "%" + term + "%");
        List<Cliente> clientes = query.getResultList();
        em.close();
        return clientes;
    }

    @Override
    public Cliente findById(int id) {
        EntityManager em = emf.createEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        em.close();
        return cliente;
    }

    @Override
    public void save(Cliente c) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();  
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Cliente c) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Cliente cliente = em.find(Cliente.class, c.getId());
        cliente.setRut(c.getRut());
        cliente.setNombre(c.getNombre());
        cliente.setApellido(c.getApellido());
        cliente.setCorreo(c.getCorreo());
        cliente.setDireccion(c.getDireccion());
        cliente.setComuna(c.getComuna());
        cliente.setTelefono(c.getTelefono());
        cliente.setCelular(c.getCelular());
        cliente.setFechaNacimiento(c.getFechaNacimiento());
        cliente.setEstado(c.getEstado());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.id IN :ids");
        query.setParameter("ids", ids);
        List<Cliente> clientes = query.getResultList();
        clientes.forEach(c -> {
            c.setEstado((short) (estado ? 1 : 0));
        });
        em.getTransaction().commit();
        em.close();
    }

}
