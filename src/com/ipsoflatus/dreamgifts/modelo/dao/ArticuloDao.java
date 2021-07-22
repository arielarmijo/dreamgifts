package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticuloDao implements DAO<Articulo> {

    @Override
    public List<Articulo> findAll() {
        List<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT id, nombre, marca, stock, fecha_vencimiento, estado, categoria_articulo_id FROM articulos";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            conn.setReadOnly(true);
            System.out.println(ps);
            while (rs.next()) {
                articulos.add(rowMapper(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
        return articulos;
    }
    
    @Override
    public List<Articulo> findByTermLike(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Articulo findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Articulo articulo) {
        String sql = "INSERT INTO articulos (nombre, marca, estado, categoria_articulo_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = MySQLConection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, articulo.getNombre());
            ps.setString(2, articulo.getMarca());
            ps.setBoolean(3, articulo.getEstado());
            ps.setInt(4, articulo.getCategoriaId());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void update(Articulo articulo) {
        String sql = "UPDATE articulos SET nombre = ?, marca = ?, stock = ?, fecha_vencimiento = ?, estado = ?, categoria_articulo_id = ? WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, articulo.getNombre());
            ps.setString(2, articulo.getMarca());
            ps.setInt(3, articulo.getStock());
            ps.setDate(4, articulo.getFechaVencimiento());
            ps.setBoolean(5, articulo.getEstado());
            ps.setInt(6, articulo.getCategoriaId());
            ps.setInt(7, articulo.getId());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        String in = ids.stream().map(id -> id.toString()).collect(Collectors.joining(", "));
        String sql = String.format("UPDATE articulos SET estado = %s WHERE id IN (%s)", estado, in);
        System.out.println(sql);
        try (Connection conn = MySQLConection.getConnection();
             Statement s = conn.createStatement()) {
            s.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
    }
    
    private Articulo rowMapper(ResultSet rs) throws SQLException {
        Articulo articulo = new Articulo();
        articulo.setId(rs.getInt(1));
        articulo.setNombre(rs.getString(2));
        articulo.setMarca(rs.getString(3));
        articulo.setStock(rs.getInt(4));
        articulo.setFechaVencimiento(rs.getDate(5));
        articulo.setEstado(rs.getBoolean(6));
        articulo.setCategoriaId(rs.getInt(7));
        return articulo;
    }
    
}
