package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PackHasArticuloPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pack_id")
    private Integer packId;
    
    @Basic(optional = false)
    @Column(name = "articulo_id")
    private Integer articuloId;

    public PackHasArticuloPK() {
    }

    public PackHasArticuloPK(Integer packId, Integer articuloId) {
        this.packId = packId;
        this.articuloId = articuloId;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.packId);
        hash = 71 * hash + Objects.hashCode(this.articuloId);
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
        final PackHasArticuloPK other = (PackHasArticuloPK) obj;
        if (!Objects.equals(this.packId, other.packId)) {
            return false;
        }
        if (!Objects.equals(this.articuloId, other.articuloId)) {
            return false;
        }
        return true;
    }





    @Override
    public String toString() {
        return "[" + packId + ", " + articuloId + "]";
    }
    
}
