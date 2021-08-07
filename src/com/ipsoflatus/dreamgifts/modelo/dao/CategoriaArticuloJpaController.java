/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Usuario
 */
public class CategoriaArticuloJpaController implements Serializable {

    public CategoriaArticuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaArticulo categoriaArticulo) {
        if (categoriaArticulo.getArticulos() == null) {
            categoriaArticulo.setArticulos(new ArrayList<Articulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Articulo> attachedArticulos = new ArrayList<Articulo>();
            for (Articulo articulosArticuloToAttach : categoriaArticulo.getArticulos()) {
                articulosArticuloToAttach = em.getReference(articulosArticuloToAttach.getClass(), articulosArticuloToAttach.getId());
                attachedArticulos.add(articulosArticuloToAttach);
            }
            categoriaArticulo.setArticulos(attachedArticulos);
            em.persist(categoriaArticulo);
            for (Articulo articulosArticulo : categoriaArticulo.getArticulos()) {
                CategoriaArticulo oldCategoriaArticuloOfArticulosArticulo = articulosArticulo.getCategoriaArticulo();
                articulosArticulo.setCategoriaArticulo(categoriaArticulo);
                articulosArticulo = em.merge(articulosArticulo);
                if (oldCategoriaArticuloOfArticulosArticulo != null) {
                    oldCategoriaArticuloOfArticulosArticulo.getArticulos().remove(articulosArticulo);
                    oldCategoriaArticuloOfArticulosArticulo = em.merge(oldCategoriaArticuloOfArticulosArticulo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaArticulo categoriaArticulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaArticulo persistentCategoriaArticulo = em.find(CategoriaArticulo.class, categoriaArticulo.getId());
            List<Articulo> articulosOld = persistentCategoriaArticulo.getArticulos();
            List<Articulo> articulosNew = categoriaArticulo.getArticulos();
            List<Articulo> attachedArticulosNew = new ArrayList<Articulo>();
            for (Articulo articulosNewArticuloToAttach : articulosNew) {
                articulosNewArticuloToAttach = em.getReference(articulosNewArticuloToAttach.getClass(), articulosNewArticuloToAttach.getId());
                attachedArticulosNew.add(articulosNewArticuloToAttach);
            }
            articulosNew = attachedArticulosNew;
            categoriaArticulo.setArticulos(articulosNew);
            categoriaArticulo = em.merge(categoriaArticulo);
            for (Articulo articulosOldArticulo : articulosOld) {
                if (!articulosNew.contains(articulosOldArticulo)) {
                    articulosOldArticulo.setCategoriaArticulo(null);
                    articulosOldArticulo = em.merge(articulosOldArticulo);
                }
            }
            for (Articulo articulosNewArticulo : articulosNew) {
                if (!articulosOld.contains(articulosNewArticulo)) {
                    CategoriaArticulo oldCategoriaArticuloOfArticulosNewArticulo = articulosNewArticulo.getCategoriaArticulo();
                    articulosNewArticulo.setCategoriaArticulo(categoriaArticulo);
                    articulosNewArticulo = em.merge(articulosNewArticulo);
                    if (oldCategoriaArticuloOfArticulosNewArticulo != null && !oldCategoriaArticuloOfArticulosNewArticulo.equals(categoriaArticulo)) {
                        oldCategoriaArticuloOfArticulosNewArticulo.getArticulos().remove(articulosNewArticulo);
                        oldCategoriaArticuloOfArticulosNewArticulo = em.merge(oldCategoriaArticuloOfArticulosNewArticulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoriaArticulo.getId();
                if (findCategoriaArticulo(id) == null) {
                    throw new NonexistentEntityException("The categoriaArticulo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaArticulo categoriaArticulo;
            try {
                categoriaArticulo = em.getReference(CategoriaArticulo.class, id);
                categoriaArticulo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaArticulo with id " + id + " no longer exists.", enfe);
            }
            List<Articulo> articulos = categoriaArticulo.getArticulos();
            for (Articulo articulosArticulo : articulos) {
                articulosArticulo.setCategoriaArticulo(null);
                articulosArticulo = em.merge(articulosArticulo);
            }
            em.remove(categoriaArticulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaArticulo> findCategoriaArticuloEntities() {
        return findCategoriaArticuloEntities(true, -1, -1);
    }

    public List<CategoriaArticulo> findCategoriaArticuloEntities(int maxResults, int firstResult) {
        return findCategoriaArticuloEntities(false, maxResults, firstResult);
    }

    private List<CategoriaArticulo> findCategoriaArticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaArticulo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CategoriaArticulo findCategoriaArticulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaArticulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaArticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaArticulo> rt = cq.from(CategoriaArticulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
