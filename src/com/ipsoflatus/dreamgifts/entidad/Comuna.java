package com.ipsoflatus.dreamgifts.entidad;

public class Comuna {
    private Integer id;
    private String nombre;
    private String codigo;
    private Boolean estado;

    public Comuna() {
    }
    

    public Comuna(Integer id, String nombre, String codigo, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.estado = estado;
    }

    public Comuna(String nombre, String codigo) {
       this(null, nombre, codigo, true);
       
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
