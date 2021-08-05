package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "orden_compra_detalle")
@NamedQueries({
    @NamedQuery(name = "OrdenCompraDetalle.findAll", query = "SELECT o FROM OrdenCompraDetalle o")})
public class OrdenCompraDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected OrdenCompraDetallePK ordenCompraDetallePK;
    
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "articulo_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Articulo articulo;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "orden_compra_id", referencedColumnName = "id", insertable = false, updatable = false)
    private OrdenCompra ordenCompra;

    public OrdenCompraDetalle() {
    }

    public OrdenCompraDetalle(OrdenCompraDetallePK ordenCompraDetallePK) {
        this.ordenCompraDetallePK = ordenCompraDetallePK;
    }

    public OrdenCompraDetalle(OrdenCompraDetallePK ordenCompraDetallePK, int cantidad) {
        this.ordenCompraDetallePK = ordenCompraDetallePK;
        this.cantidad = cantidad;
    }

    public OrdenCompraDetalle(int ordenCompraId, int articuloId) {
        this.ordenCompraDetallePK = new OrdenCompraDetallePK(ordenCompraId, articuloId);
    }

    public OrdenCompraDetallePK getOrdenCompraDetallePK() {
        return ordenCompraDetallePK;
    }

    public void setOrdenCompraDetallePK(OrdenCompraDetallePK ordenCompraDetallePK) {
        this.ordenCompraDetallePK = ordenCompraDetallePK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordenCompraDetallePK != null ? ordenCompraDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenCompraDetalle)) {
            return false;
        }
        OrdenCompraDetalle other = (OrdenCompraDetalle) object;
        if ((this.ordenCompraDetallePK == null && other.ordenCompraDetallePK != null) || (this.ordenCompraDetallePK != null && !this.ordenCompraDetallePK.equals(other.ordenCompraDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipsoflatus.dreamgifts.modelo.entidad.OrdenCompraDetalle[ ordenCompraDetallePK=" + ordenCompraDetallePK + " ]";
    }
    
}
