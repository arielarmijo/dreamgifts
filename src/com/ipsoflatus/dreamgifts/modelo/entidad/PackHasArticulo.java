package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
    
    @Basic(optional = true)
    @Column(name = "cantidad")
    private int cantidad;
    
    @ManyToOne(optional = true)
    //@JoinColumn(name = "articulo_id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId("articuloId")
    private Articulo articulo;
    
    @ManyToOne(optional = true)
    //@JoinColumn(name = "pack_id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId("packId")
    private Pack pack;

    public PackHasArticulo() {
        packHasArticuloPK = new PackHasArticuloPK();
    }

    public PackHasArticulo(PackHasArticuloPK packHasArticuloPK) {
        this.packHasArticuloPK = packHasArticuloPK;
    }

    public PackHasArticulo(PackHasArticuloPK packHasArticuloPK, int cantidad) {
        this.packHasArticuloPK = packHasArticuloPK;
        this.cantidad = cantidad;
    }

    public PackHasArticulo(Integer packId, Integer articuloId) {
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
        this.packHasArticuloPK.setArticuloId(articulo.getId());
        this.articulo = articulo;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.packHasArticuloPK.setPackId(pack.getId());
        this.pack = pack;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.articulo);
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
        final PackHasArticulo other = (PackHasArticulo) obj;
        if (!Objects.equals(this.articulo, other.articulo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "<"+packHasArticuloPK+">";
    }
    
}
