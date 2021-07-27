package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.util.List;

public class Pack {

    private Integer id;
    private String nombre;
    private Integer stock;
    private Integer costo;
    private Boolean estado;
    private List<PackHasArticulo> articulos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<PackHasArticulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<PackHasArticulo> articulos) {
        this.articulos = articulos;
    }
    
}
