package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.entidad.CategoriaArticulo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class CategoriaArticuloDao extends AbstractSoftDeleteDao<CategoriaArticulo> {
    
    public CategoriaArticuloDao() {
        super(CategoriaArticulo.class);
    }

    public void updateEntity(CategoriaArticulo categoriaArticulo) {

//        CategoriaArticulo persistentCategoriaArticulo = em.find(CategoriaArticulo.class, categoriaArticulo.getId());
//        List<Articulo> articulosOld = persistentCategoriaArticulo.getArticulos();
//        List<Articulo> articulosNew = categoriaArticulo.getArticulos();
//        List<Articulo> attachedArticulosNew = new ArrayList<Articulo>();
//        for (Articulo articulosNewArticuloToAttach : articulosNew) {
//            articulosNewArticuloToAttach = em.getReference(articulosNewArticuloToAttach.getClass(), articulosNewArticuloToAttach.getId());
//            attachedArticulosNew.add(articulosNewArticuloToAttach);
//        }
//        articulosNew = attachedArticulosNew;
//        categoriaArticulo.setArticulos(articulosNew);
//        categoriaArticulo = em.merge(categoriaArticulo);
//        for (Articulo articulosOldArticulo : articulosOld) {
//            if (!articulosNew.contains(articulosOldArticulo)) {
//                articulosOldArticulo.setCategoriaArticulo(null);
//                articulosOldArticulo = em.merge(articulosOldArticulo);
//            }
//        }
//        for (Articulo articulosNewArticulo : articulosNew) {
//            if (!articulosOld.contains(articulosNewArticulo)) {
//                CategoriaArticulo oldCategoriaArticuloOfArticulosNewArticulo = articulosNewArticulo.getCategoriaArticulo();
//                articulosNewArticulo.setCategoriaArticulo(categoriaArticulo);
//                articulosNewArticulo = em.merge(articulosNewArticulo);
//                if (oldCategoriaArticuloOfArticulosNewArticulo != null && !oldCategoriaArticuloOfArticulosNewArticulo.equals(categoriaArticulo)) {
//                    oldCategoriaArticuloOfArticulosNewArticulo.getArticulos().remove(articulosNewArticulo);
//                    oldCategoriaArticuloOfArticulosNewArticulo = em.merge(oldCategoriaArticuloOfArticulosNewArticulo);
//                }
//            }
//        }
        
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