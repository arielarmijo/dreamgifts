package com.ipsoflatus.dreamgifts.modelo;

public class RedSocial {
   
    private Integer id;
    private String nombre;
    private String codigo;

    public RedSocial() {
    }
    
    public RedSocial(String nombre, String codigo) {
        this(null, nombre, codigo);
    }

    public RedSocial(Integer id, String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "RRSS{" + "id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + '}';
    }
    
}
