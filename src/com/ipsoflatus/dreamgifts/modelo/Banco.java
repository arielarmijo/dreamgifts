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
public class Banco {
    
    private String nombre;
    private String codigo;
    private boolean estado;

    public Banco() {
    }

    public Banco(String nombre, String codigo, boolean estado) {
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
    
}
