package com.ipsoflatus.dreamgifts.modelo.entidad;

public class RedSocial {
   
    private Integer id;
    private String codigo;
    private String nombre;
    private boolean estado;

    public RedSocial() {
    }
    
    public RedSocial(String codigo, String nombre) {
        this(null, codigo, nombre, true);
    }

    public RedSocial(Integer id, String codigo, String nombre, boolean estado) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
    }

    public RedSocial(String nombre) {
        this(null, null, nombre, false);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
}
