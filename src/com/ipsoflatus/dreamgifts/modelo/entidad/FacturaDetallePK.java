package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FacturaDetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "articulo_id")
    private Integer articuloId;
    
    @Basic(optional = false)
    @Column(name = "factura_id")
    private Integer facturaId;

    public FacturaDetallePK() {
    }

    public FacturaDetallePK(Integer articuloId, Integer facturaId) {
        this.articuloId = articuloId;
        this.facturaId = facturaId;
    }

    public Integer getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Integer articuloId) {
        this.articuloId = articuloId;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) articuloId;
        hash += (int) facturaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaDetallePK)) {
            return false;
        }
        FacturaDetallePK other = (FacturaDetallePK) object;
        if (this.articuloId != other.articuloId) {
            return false;
        }
        if (this.facturaId != other.facturaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + articuloId + ", " + facturaId + "]";
    }

}
