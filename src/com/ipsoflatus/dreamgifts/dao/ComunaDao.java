/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.dao;

import com.ipsoflatus.dreamgifts.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.CategoriaArticulo;
import com.ipsoflatus.dreamgifts.modelo.Comuna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ComunaDao {
     public void save(Comuna comuna) {
        String sql = "INSERT INTO comunas (codigo, nombre, estado) VALUES (?, ?, ?)";
        try (Connection conn = MySQLConection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, comuna.getCodigo().toUpperCase());
            ps.setString(2, comuna.getNombre());
            ps.setBoolean(3, comuna.getEstado());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
    }
    public List<Comuna> findAll() {
        List<Comuna> comunas = new ArrayList<>();
        String sql = "SELECT id, codigo, nombre, estado FROM comunas ORDER BY codigo";
        System.out.println(sql);
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                comunas.add(rowMapper(rs));
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return comunas;
     
    }
     private Comuna rowMapper(ResultSet rs) throws SQLException {
       Comuna c = new Comuna();
        c.setId(rs.getInt(1));
        c.setCodigo(rs.getString(2));
        c.setNombre(rs.getString(3));
        c.setEstado(rs.getBoolean(4));
        return c;
    }
}

