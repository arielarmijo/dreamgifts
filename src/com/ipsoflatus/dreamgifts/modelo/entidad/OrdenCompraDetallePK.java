package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrdenCompraDetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "orden_compra_id")
    private int ordenCompraId;
    
    @Basic(optional = false)
    @Column(name = "articulo_id")
    private int articuloId;

    public OrdenCompraDetallePK() {
    }

    public OrdenCompraDetallePK(int ordenCompraId, int articuloId) {
        this.ordenCompraId = ordenCompraId;
        this.articuloId = articuloId;
    }

    public int getOrdenCompraId() {
        return ordenCompraId;
    }

    public void setOrdenCompraId(int ordenCompraId) {
        this.ordenCompraId = ordenCompraId;
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
        hash += (int) ordenCompraId;
        hash += (int) articuloId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenCompraDetallePK)) {
            return false;
        }
        OrdenCompraDetallePK other = (OrdenCompraDetallePK) object;
        if (this.ordenCompraId != other.ordenCompraId) {
            return false;
        }
        if (this.articuloId != other.articuloId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompraDetallePK[ ordenCompraId=" + ordenCompraId + ", articuloId=" + articuloId + " ]";
    }
    
}
