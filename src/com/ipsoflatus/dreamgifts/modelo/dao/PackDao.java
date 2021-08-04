package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PackDao extends AbstractSoftDeleteDao<Pack> {

    public PackDao() {
        super(Pack.class);
    }
    
    @Override
    public void update(Pack pack) {

        if (pack.getArticulos() == null) {
            pack.setArticulos(new ArrayList<PackHasArticulo>());
        }
        
        EntityManager em = null;
        
        try {
            
            em = getEntityManager();
            em.getTransaction().begin();
            
            Pack persistentPack = em.find(Pack.class, pack.getId());
            Query query = em.createQuery("DELETE FROM PackHasArticulo pha WHERE pha.pack.id = :id");
            query.setParameter("id", persistentPack.getId());
            query.executeUpdate();
            
            List<PackHasArticulo> articulos = pack.getArticulos();
            for (PackHasArticulo articulo : articulos) {
                articulo.setPack(persistentPack);
                em.persist(articulo);
            }
            persistentPack.setArticulos(articulos);
            em.merge(persistentPack);
            
            em.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }    
        finally {
            if (em != null) {
                em.close();
            }
        }
    }


}
