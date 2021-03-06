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
@Table(name = "facturas")
@NamedQueries({
    @NamedQuery(name = "Factura.findByNumber", query = "SELECT f FROM Factura f WHERE f.numero = :numero"),
    @NamedQuery(name = "Factura.findByProveedorIn", query = "SELECT f FROM Factura f WHERE f.ordenCompra.proveedor IN :proveedor"),
    @NamedQuery(name = "Factura.findMinDate", query = "SELECT MIN(f.fecha) FROM Factura f"),
    @NamedQuery(name = "Factura.findMaxDate", query = "SELECT MAX(f.fecha) FROM Factura f"),
    @NamedQuery(name = "Factura.findByDateBetween", query = "SELECT f FROM Factura f WHERE f.fecha BETWEEN :desde AND :hasta")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "factura", cascade = CascadeType.ALL)
    private List<FacturaDetalle> articulos = new ArrayList<>();
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "orden_compra_id", referencedColumnName = "id")
    private OrdenCompra ordenCompra;
    
    public Factura() {
    }

    public Factura(Integer id) {
        this.id = id;
    }

    public Factura(Integer id, int numero, Date fecha) {
        this.id = id;
        this.numero = numero;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<FacturaDetalle> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<FacturaDetalle> articulos) {
        this.articulos = articulos;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Factura[ id=" + id + " ]";
    }

}
