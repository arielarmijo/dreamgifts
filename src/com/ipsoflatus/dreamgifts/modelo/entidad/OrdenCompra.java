package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ordenes_compra")
@NamedQueries({
    @NamedQuery(name = "OrdenCompra.findAll", query = "SELECT o FROM OrdenCompra o")})
public class OrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SELECCIONE_OC = "Seleccione oc";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "fecha_orden")
    @Temporal(TemporalType.DATE)
    private Date fechaOrden;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "proveedor_id", referencedColumnName = "id")
    private Proveedor proveedor;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "ordenCompra")
    private List<OrdenCompraDetalle> articulos = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenCompra")
    private List<Factura> facturas;

    public OrdenCompra() {
    }

    public OrdenCompra(Integer id) {
        this.id = id;
    }

    public OrdenCompra(Integer id, Date fechaOrden) {
        this.id = id;
        this.fechaOrden = fechaOrden;
    }
    
    public String getSeleccioneOC() {
        return SELECCIONE_OC;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<OrdenCompraDetalle> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<OrdenCompraDetalle> articulos) {
        this.articulos = articulos;
    }
    
    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenCompra)) {
            return false;
        }
        OrdenCompra other = (OrdenCompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id == null ? SELECCIONE_OC : id.toString();
    }

}
