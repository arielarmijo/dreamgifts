package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FacturaDetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "articulo_id")
    private int articuloId;
    
    @Basic(optional = false)
    @Column(name = "factura_id")
    private int facturaId;

    public FacturaDetallePK() {
    }

    public FacturaDetallePK(int articuloId, int facturaId) {
        this.articuloId = articuloId;
        this.facturaId = facturaId;
    }

    public int getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
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
