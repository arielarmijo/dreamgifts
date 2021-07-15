package com.ipsoflatus.dreamgifts.modelo;

import java.util.Objects;

public class Usuario {
    
    private Integer id;
    private String nombre;
    private String clave;
    private Boolean estado;

    public Usuario() {
    }
    
    public Usuario(String nombre, String clave, boolean estado) {
        this(null, nombre, clave, estado);
    }

    public Usuario(String nombre, String clave) {
        this(null, nombre, clave, false);
    }
    
    public Usuario(Integer id, String nombre, String clave, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", activo=" + estado + '}';
    }

}
