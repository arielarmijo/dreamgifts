package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PackHasArticuloPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "pack_id")
    private int packId;
    
    @Basic(optional = false)
    @Column(name = "articulo_id")
    private int articuloId;

    public PackHasArticuloPK() {
    }

    public PackHasArticuloPK(int packId, int articuloId) {
        this.packId = packId;
        this.articuloId = articuloId;
    }

    public int getPackId() {
        return packId;
    }

    public void setPackId(int packId) {
        this.packId = packId;
    }

    public int getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) packId;
        hash += (int) articuloId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PackHasArticuloPK)) {
            return false;
        }
        PackHasArticuloPK other = (PackHasArticuloPK) object;
        if (this.packId != other.packId) {
            return false;
        }
        if (this.articuloId != other.articuloId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticuloPK[ packId=" + packId + ", articuloId=" + articuloId + " ]";
    }
    
}
