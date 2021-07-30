/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.entidad.EstadoVenta;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Usuario
 */
public class EstadoVentaDao implements DAO<EstadoVenta> {

    @Override
    public List<EstadoVenta> findAll() {
    List<EstadoVenta> eevv = new ArrayList<>();
        String sql = "SELECT id, codigo, nombre, descripcion, estado FROM estados_venta";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println(ps);
            while (rs.next()) {
                eevv.add(rowMapper(rs));
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return eevv;
    }

    private EstadoVenta rowMapper(ResultSet rs) throws SQLException {
        EstadoVenta ev = new EstadoVenta();
        ev.setId(rs.getInt(1));
        ev.setCodigo(rs.getString(2));
        ev.setNombre(rs.getString(3));
        ev.setDescripcion(rs.getString(4));
        ev.setEstado(rs.getBoolean(5));
        return ev;
    }

    public EstadoVenta findByCode(String codigo) {
        EstadoVenta ev = null;
        String sql = "SELECT id, codigo, nombre, descripcion, estado FROM estados_venta WHERE UPPER(codigo) = UPPER(?)";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ev = rowMapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return ev;
    }
    
    @Override
    public List<EstadoVenta> findByTermLike(String termino) {
        List<EstadoVenta> eevv = new ArrayList<>();
        String sql = "SELECT id, codigo, nombre, descripcion, estado FROM estados_venta WHERE UPPER(codigo) LIKE UPPER(?) OR UPPER(nombre) LIKE UPPER(?)";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + termino + "%");
            ps.setString(2, "%" + termino + "%");
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    eevv.add(rowMapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return eevv;
}

    
    @Override
    public EstadoVenta findById(int id) {
        EstadoVenta ev = null;
        String sql = "SELECT id, codigo, nombre, descripcion, estado FROM estados_venta WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ev = rowMapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return ev;
    }

    @Override
    public void save(EstadoVenta ev) {
         String sql = "INSERT INTO estados_venta (codigo, nombre, descripcion, estado) VALUES (?, ?, ?,?)";
        try (Connection conn = MySQLConection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ev.getCodigo().toUpperCase());
            ps.setString(2, ev.getNombre());
            ps.setString(3, ev.getDescripcion());
            ps.setBoolean(4, ev.getEstado());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void update(EstadoVenta ev) {
        String sql = "UPDATE estados_venta SET codigo = ?, nombre = ?, descripcion = ?, estado = ? WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ev.getCodigo().toUpperCase());
            ps.setString(2, ev.getNombre());
            ps.setString(3, ev.getDescripcion());
            ps.setBoolean(4, ev.getEstado());
            ps.setInt(5, ev.getId());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        String in = ids.stream().map(id -> id.toString()).collect(Collectors.joining(", "));
        String sql = String.format("UPDATE estados_venta SET estado = %s WHERE id IN (%s)", estado, in);
        System.out.println(sql);
        try (Connection conn = MySQLConection.getConnection();
             Statement s = conn.createStatement()) {
            s.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
    }
    
}
