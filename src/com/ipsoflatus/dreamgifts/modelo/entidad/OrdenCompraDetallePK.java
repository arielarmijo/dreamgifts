package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrdenCompraDetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "orden_compra_id")
    private Integer ordenCompraId;
    
    @Basic(optional = false)
    @Column(name = "articulo_id")
    private Integer articuloId;

    public OrdenCompraDetallePK() {
    }

    public OrdenCompraDetallePK(Integer ordenCompraId, Integer articuloId) {
        this.ordenCompraId = ordenCompraId;
        this.articuloId = articuloId;
    }

    public Integer getOrdenCompraId() {
        return ordenCompraId;
    }

    public void setOrdenCompraId(Integer ordenCompraId) {
        this.ordenCompraId = ordenCompraId;
    }

    public Integer getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Integer articuloId) {
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
        return "[" + ordenCompraId + ", " + articuloId + " ]";
    }
    
}
