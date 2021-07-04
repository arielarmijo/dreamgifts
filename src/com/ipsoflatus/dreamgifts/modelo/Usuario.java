/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.modelo;

/**
 *
 * @author Usuario
 */
public class Usuario {
    
    private Integer id;
    private String nombre;
    private String clave;
    private boolean active;

    public Usuario() {
    }

    public Usuario(String nombre, String clave) {
        this(null, nombre, clave, false);
    }
    
    public Usuario(Integer id, String nombre, String clave, boolean isActive) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.active = isActive;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", activo=" + active + '}';
    }

}