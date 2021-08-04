package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.dao.exceptions.NonexistentEntityException;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class CategoriaArticuloDao extends AbstractSoftDeleteDao<CategoriaArticulo> {
    
    public CategoriaArticuloDao() {
        super(CategoriaArticulo.class);
    }

    @Override
    public void update(CategoriaArticulo categoriaArticulo) throws NonexistentEntityException, Exception {
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
        
//        EntityManager em = getEntityManager();
//        em.getTransaction().begin();
//        CategoriaArticulo pca = em.find(CategoriaArticulo.class, ca.getId());
//        pca.setCodigo(ca.getCodigo());
//        pca.setNombre(ca.getNombre());
//        pca.setEstado(ca.getEstado());
//        List<Articulo> articulos = pca.getArticulos();
//        for (Articulo articulo : articulos) {
//            Articulo pa = em.find(Articulo.class, articulo.getId());
//            pa.setCategoriaArticulo(pca);
//        }
//        em.getTransaction().commit();
//        em.close();
    }
    
    public CategoriaArticulo findCategoriaArticulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaArticulo.class, id);
        } finally {
            em.close();
        }
    }

}