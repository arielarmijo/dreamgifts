package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PackDao extends AbstractSoftDeleteDao<Pack> {

    public PackDao() {
        super(Pack.class);
    }

    @Override
    public void update(Pack pack) {
        EntityManager em = emf.createEntityManager();
        try {
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
            persistentPack.setCosto(pack.getCosto());
            persistentPack.setStock(persistentPack.getStock() + pack.getStock());
            if (pack.getStockCritico() != null)
                persistentPack.setStockCritico(pack.getStockCritico());
            if (pack.getFechaInicio()!= null)
                persistentPack.setFechaInicio(pack.getFechaInicio());
            if (pack.getFechaTermino()!= null)
                persistentPack.setFechaTermino(pack.getFechaTermino());
            
            em.getTransaction().commit();
        } catch (Exception e) {
            printError(e);
            throw new DreamGiftsException("Error al actualizar " + typeClass.getSimpleName());
        } finally {
            em.close();
        }
    }

}
