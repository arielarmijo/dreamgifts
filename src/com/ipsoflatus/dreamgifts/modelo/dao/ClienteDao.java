package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ClienteDao extends AbstractSoftDeleteDao<Cliente> {

    public ClienteDao() {
        super(Cliente.class);
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
    protected void update(EntityManager em, Cliente c) {
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
    }

}
