package com.ipsoflatus.dreamgifts.modelo.servicio;

import com.ipsoflatus.dreamgifts.modelo.Observer;
import com.ipsoflatus.dreamgifts.modelo.dao.BancoDao;
import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import java.util.ArrayList;
import java.util.List;

public class BancoService implements Service<Banco>, ObservableService<Observer<Banco>>{

    private final BancoDao dao;
    private final List<Observer<Banco>> obs;
    private static BancoService instance;
    
    private BancoService() {
        dao = new BancoDao();
        obs = new ArrayList<>();
    }
    
    public static BancoService getInstance() {
        if (instance == null)
            instance = new BancoService();
        return instance;
    }
    @Override
    public List<Banco> buscar() {
        return dao.findAll();
    }

    @Override
    public Banco buscar(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<Banco> buscar(String text) {
        return dao.findByTermLike(text);
    }

    @Override
    public void guardar(Banco banco) {
        dao.save(banco);
        notifyObservers();
    }

    @Override
    public void editar(Banco banco) {
        dao.update(banco);
        notifyObservers();
    }

    @Override
    public void cambiarEstado(List<Integer> ids, Boolean estado) {
        dao.updateStateByIds(ids, estado);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer<Banco> o) {
        this.obs.add(o);
        o.actualizar(dao.findAll());
    }

    @Override
    public void notifyObservers() {
        obs.forEach(o -> {
            o.actualizar(dao.findAll());
        });
    }
    
}
