package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "factura_detalle")
@NamedQueries({
    @NamedQuery(name = "FacturaDetalle.findAll", query = "SELECT f FROM FacturaDetalle f")})
public class FacturaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected FacturaDetallePK facturaDetallePK;
    
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    
    @Basic(optional = false)
    @Column(name = "valor_unitario")
    private int valorUnitario;
    
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    
    @ManyToOne(optional = false)
    @MapsId("articuloId")
    @JoinColumn(name = "articulo_id", referencedColumnName = "id")
    private Articulo articulo;
    
    @ManyToOne(optional = false)
    @MapsId("facturaId")
    @JoinColumn(name = "factura_id", referencedColumnName = "id")
    private Factura factura;

    public FacturaDetalle() {
        this.facturaDetallePK = new FacturaDetallePK();
    }

    public FacturaDetalle(FacturaDetallePK facturaDetallePK) {
        this.facturaDetallePK = facturaDetallePK;
    }

    public FacturaDetalle(FacturaDetallePK facturaDetallePK, String codigo, int cantidad, int valorUnitario) {
        this.facturaDetallePK = facturaDetallePK;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
    }

    public FacturaDetalle(int articuloId, int facturaId) {
        this.facturaDetallePK = new FacturaDetallePK(articuloId, facturaId);
    }

    public FacturaDetallePK getFacturaDetallePK() {
        return facturaDetallePK;
    }

    public void setFacturaDetallePK(FacturaDetallePK facturaDetallePK) {
        this.facturaDetallePK = facturaDetallePK;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.facturaDetallePK.setArticuloId(articulo.getId());
        this.articulo = articulo;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.facturaDetallePK.setFacturaId(factura.getId());
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.articulo);
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
        final FacturaDetalle other = (FacturaDetalle) obj;
        if (!Objects.equals(this.articulo, other.articulo)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "FacturaDetalle[" + facturaDetallePK + "]";
    }

}
