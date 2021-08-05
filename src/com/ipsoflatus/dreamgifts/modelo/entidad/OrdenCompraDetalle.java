package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
    @MapsId("articuloId")
    @JoinColumn(name = "articulo_id", referencedColumnName = "id")
    private Articulo articulo;
    
    @ManyToOne(optional = false)
    @MapsId("ordenCompraId")
    @JoinColumn(name = "orden_compra_id", referencedColumnName = "id")
    private OrdenCompra ordenCompra;

    public OrdenCompraDetalle() {
        this.ordenCompraDetallePK = new OrdenCompraDetallePK();
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
        this.ordenCompraDetallePK.setArticuloId(articulo.getId());
        this.articulo = articulo;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompraDetallePK.setOrdenCompraId(ordenCompra.getId());
        this.ordenCompra = ordenCompra;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.articulo);
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
        final OrdenCompraDetalle other = (OrdenCompraDetalle) obj;
        if (!Objects.equals(this.articulo, other.articulo)) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "OrdenCompraDetalle[" + ordenCompraDetallePK + "]";
    }
    
}
