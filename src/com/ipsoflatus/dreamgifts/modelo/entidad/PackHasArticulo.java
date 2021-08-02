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
@Table(name = "pack_has_articulo")
@NamedQueries({
    @NamedQuery(name = "PackHasArticulo.findAll", query = "SELECT p FROM PackHasArticulo p")})
public class PackHasArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected PackHasArticuloPK packHasArticuloPK;
    
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "articulo_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Articulo articulo;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "pack_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Pack pack;

    public PackHasArticulo() {
    }

    public PackHasArticulo(PackHasArticuloPK packHasArticuloPK) {
        this.packHasArticuloPK = packHasArticuloPK;
    }

    public PackHasArticulo(PackHasArticuloPK packHasArticuloPK, int cantidad) {
        this.packHasArticuloPK = packHasArticuloPK;
        this.cantidad = cantidad;
    }

    public PackHasArticulo(int packId, int articuloId) {
        this.packHasArticuloPK = new PackHasArticuloPK(packId, articuloId);
    }

    public PackHasArticuloPK getPackHasArticuloPK() {
        return packHasArticuloPK;
    }

    public void setPackHasArticuloPK(PackHasArticuloPK packHasArticuloPK) {
        this.packHasArticuloPK = packHasArticuloPK;
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

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (packHasArticuloPK != null ? packHasArticuloPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PackHasArticulo)) {
            return false;
        }
        PackHasArticulo other = (PackHasArticulo) object;
        if ((this.packHasArticuloPK == null && other.packHasArticuloPK != null) || (this.packHasArticuloPK != null && !this.packHasArticuloPK.equals(other.packHasArticuloPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo[ packHasArticuloPK=" + packHasArticuloPK + " ]";
    }
    
}
