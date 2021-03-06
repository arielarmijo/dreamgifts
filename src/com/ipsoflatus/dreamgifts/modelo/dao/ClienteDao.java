package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ClienteDao extends AbstractSoftDeleteDao<Cliente> {

    public ClienteDao() {
        super(Cliente.class);
    }
    
    public void updateEntity(Cliente c) {
        Cliente cliente = findById(c.getId());
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
    
    public Cliente findByRut(String rut) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.rut = :rut");
            query.setParameter("rut", rut);
            Cliente cliente = (Cliente) query.getSingleResult();
        return cliente;
        } catch (NoResultException e) {
            printError(e);
            throw new DreamGiftsException("Cliente no existe.");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
