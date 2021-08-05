package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "articulos")
@NamedQueries({
    @NamedQuery(name = "Articulo.findByTermLike", query = "SELECT a FROM Articulo a WHERE UPPER(a.nombre) LIKE UPPER(:term) OR UPPER(a.marca) LIKE UPPER(:term) OR UPPER(a.categoriaArticulo.nombre) LIKE UPPER(:term)")})
public class Articulo implements Serializable, SoftDelete {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @Column(name = "marca")
    private String marca;
    
    @Column(name = "stock")
    private Integer stock;
    
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    
    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "categoria_articulo_id", referencedColumnName = "id")
    private CategoriaArticulo categoriaArticulo;
    
    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PackHasArticulo> packs = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private List<OrdenCompraDetalle> ordenesCompra;

    public Articulo() {
    }

    public Articulo(Integer id) {
        this.id = id;
    }

    public Articulo(Integer id, String nombre, String marca, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    
    public Boolean getEstado() {
        return estado;
    }

    @Override
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public CategoriaArticulo getCategoriaArticulo() {
        return categoriaArticulo;
    }

    public void setCategoriaArticulo(CategoriaArticulo categoriaArticulo) {
        this.categoriaArticulo = categoriaArticulo;
    }
    
    public List<PackHasArticulo> getPacks() {
        return packs;
    }

    public void setPacks(List<PackHasArticulo> packs) {
        this.packs = packs;
    }
    
     public List<OrdenCompraDetalle> getOrdenesCompra() {
        return ordenesCompra;
    }

    public void setOrdenesCompra(List<OrdenCompraDetalle> ordenesCompra) {
        this.ordenesCompra = ordenesCompra;
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
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
