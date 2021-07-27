package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.util.Objects;

public class PackHasArticulo {

    private Integer packId;
    private Integer articuloId;
    private Integer cantidad;

    public PackHasArticulo() {
    }
    
    public PackHasArticulo(Integer packId, Integer articuloId, Integer cantidad) {
        this.packId = packId;
        this.articuloId = articuloId;
        this.cantidad = cantidad;
    }
    
    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
    }

    public Integer getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Integer articuloId) {
        this.articuloId = articuloId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.packId);
        hash = 79 * hash + Objects.hashCode(this.articuloId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PackHasArticulo other = (PackHasArticulo) obj;
        if (!Objects.equals(this.packId, other.packId)) {
            return false;
        }
        if (!Objects.equals(this.articuloId, other.articuloId)) {
            return false;
        }
        return true;
    }
    
}
