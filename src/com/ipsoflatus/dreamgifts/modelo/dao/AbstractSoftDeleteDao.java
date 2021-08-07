package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.SoftDelete;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class AbstractSoftDeleteDao<T extends SoftDelete> extends AbstractDao<T> implements SoftDeleteDao {

    public AbstractSoftDeleteDao(Class<T> typeClass) {
        super(typeClass);
    }
    
    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String sql = String.format("SELECT o FROM %s o WHERE o.id IN :ids", typeClass.getSimpleName());
        Query query = em.createQuery(sql);
        query.setParameter("ids", ids);
        List<T> items = query.getResultList();
        items.forEach(i -> i.setEstado(estado));
        em.getTransaction().commit();
        em.close();
    }
    
}
