package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.dao.DAO;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractService<T> implements Service<T>, ObservableService<Observer<T>> {

    protected DAO<T> dao;
    private final List<Observer<T>> obs;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public AbstractService(DAO dao) {
        this.dao = dao;
        this.obs = new ArrayList<>();
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
        dao.update(t);
        notifyObservers();
    }

    @Override
    public void cambiarEstado(List<Integer> ids, Boolean estado) {
        dao.updateStateByIds(ids, estado);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer<T> o) {
        this.obs.add(o);
        executor.execute(() -> {
            o.actualizar(dao.findAll());
        });
    }

    @Override
    public void notifyObservers() {
        executor.execute(() -> {
            obs.forEach(o -> {
                o.actualizar(dao.findAll());
            });
        });
    }

}
