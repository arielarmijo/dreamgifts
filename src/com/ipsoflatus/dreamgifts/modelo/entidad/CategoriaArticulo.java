package com.ipsoflatus.dreamgifts.modelo.entidad;

public class CategoriaArticulo {
    
    private Integer id;
    private String codigo;
    private String nombre;
    private Boolean estado;

    public CategoriaArticulo() {
    }
    
    public CategoriaArticulo(String codigo, String nombre) {
        this(null, codigo, nombre, true);
    }
    
    public CategoriaArticulo(String codigo, String nombre, Boolean estado) {
        this(null, codigo, nombre, estado);
    }
    
    public CategoriaArticulo(Integer id, String codigo, String nombre, Boolean estado) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CategoriaArticulo{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", estado=" + estado + '}';
    }
    
}
