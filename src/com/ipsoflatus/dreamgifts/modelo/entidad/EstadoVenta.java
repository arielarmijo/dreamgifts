/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.modelo.entidad;

/**
 *
 * @author Usuario
 */
public class EstadoVenta {
    private Integer id;
    private String nombre;
    private String codigo;
    private Boolean estado;
    private String descripcion;

    public EstadoVenta(String codigo, String nombre, String descripcion, boolean b) {
   this.codigo = codigo;
   this.nombre = nombre;
   this.descripcion = descripcion;
   this.estado = b;
   }

    public EstadoVenta() {
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "EstadoVenta{" + "nombre=" + nombre + ", codigo=" + codigo + '}';
    }
    
}
