package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "proveedores")
@NamedQueries({
    @NamedQuery(name = "Proveedor.findByTermLike", query = "SELECT p FROM Proveedor p WHERE UPPER(p.rut) LIKE UPPER(:term) OR UPPER(p.razonSocial) LIKE UPPER(:term)")})
public class Proveedor implements Serializable, SoftDelete {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "rut")
    private String rut;
    
    @Basic(optional = false)
    @Column(name = "razon_social")
    private String razonSocial;
    
    @Basic(optional = false)
    @Column(name = "contacto")
    private String contacto;
    
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    
    @Basic(optional = false)
    @Column(name = "estado")
    private Boolean estado;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "comuna_id", referencedColumnName = "id")
    private Comuna comuna;

    public Proveedor() {
    }

    public Proveedor(Integer id) {
        this.id = id;
    }

    public Proveedor(Integer id, String rut, String razonSocial, String contacto, String direccion, String telefono, String email, Boolean estado) {
        this.id = id;
        this.rut = rut;
        this.razonSocial = razonSocial;
        this.contacto = contacto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comunaId) {
        this.comuna = comunaId;
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
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return rut;
    }
    
}
