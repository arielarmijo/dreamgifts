package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "packs")
@NamedQueries({
    @NamedQuery(name = "Pack.findByTermLike", query = "SELECT p FROM Pack p WHERE UPPER(p.nombre) LIKE UPPER(:term)")})
public class Pack implements Serializable, SoftDelete {

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
    @Column(name = "stock")
    private int stock;
    
    @Basic(optional = false)
    @Column(name = "costo")
    private int costo;
    
    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;
    
    @Column(name = "stock_critico")
    private Integer stockCritico;
    
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Column(name = "fecha_termino")
    @Temporal(TemporalType.DATE)
    private Date fechaTermino;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pack")
    private List<PackHasArticulo> articulos;

    public Pack() {
    }

    public Pack(Integer id) {
        this.id = id;
    }

    public Pack(Integer id, String nombre, int stock, int costo, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.costo = costo;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public Boolean getEstado() {
        return estado;
    }

    @Override
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getStockCritico() {
        return stockCritico;
    }

    public void setStockCritico(Integer stockCritico) {
        this.stockCritico = stockCritico;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public List<PackHasArticulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<PackHasArticulo> articulos) {
        this.articulos = articulos;
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
        if (!(object instanceof Pack)) {
            return false;
        }
        Pack other = (Pack) object;
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
