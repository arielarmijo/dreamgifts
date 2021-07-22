package com.ipsoflatus.dreamgifts.modelo.entidad;

public class Banco {
    
    private Integer id;
    private String nombre;
    private String codigo;
    private boolean estado;

    public Banco() {
    }

    public Banco(String codigo, String nombre, boolean estado) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.estado = estado;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Banco{" + "nombre=" + nombre + ", codigo=" + codigo + ", estado=" + estado + '}';
    }

    public int getId() {
return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
