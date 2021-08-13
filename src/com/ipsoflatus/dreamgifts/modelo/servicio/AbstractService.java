package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.dao.DAO;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.ipsoflatus.dreamgifts.modelo.dao.SoftDeleteDao;

public abstract class AbstractService<T> implements Service<T>, ObservableService<Observer<T>> {

    protected DAO<T> dao;
    private final List<Observer<T>> obs;
    private List<T> items = new ArrayList<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public AbstractService(DAO dao) {
        this.dao = dao;
        this.obs = new ArrayList<>();
        this.items = dao.findAll();
    }

    @Override
    public List<T> buscar() {
        return dao.findAll();
    }

    @Override
    public T buscar(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<T> buscar(String text) {
        return dao.findByTermLike(text);
    }

    @Override
    public void guardar(T t) {
        dao.save(t);
        notifyObservers();
    }

    @Override
    public void editar(T t) {
        try {
            dao.update(t);
            notifyObservers();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void cambiarEstado(List<Integer> ids, Boolean estado) {
        ((SoftDeleteDao) dao).updateStateByIds(ids, estado);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer<T> o) {
        this.obs.add(o);
        o.actualizar(items);
    }

    @Override
    public void notifyObservers() {
        items = dao.findAll();
        obs.forEach(o -> {
                System.out.println("notificando observador " + o.getClass().getSimpleName() + " con " + items);
                o.actualizar(items);
        });
    }

}
