package com.ipsoflatus.dreamgifts.dao;

import com.ipsoflatus.dreamgifts.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.CategoriaArticulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriaArticuloDao {

    public void save(CategoriaArticulo ca) {
        String sql = "INSERT INTO categorias_articulo (codigo, nombre, estado) VALUES (?, ?, ?)";
        try (Connection conn = MySQLConection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ca.getCodigo().toUpperCase());
            ps.setString(2, ca.getNombre());
            ps.setBoolean(3, ca.getEstado());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
    }

    public void update(CategoriaArticulo ca) {
        String sql = "UPDATE categorias_articulo SET codigo = ?, nombre = ?, estado = ? WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ca.getCodigo().toUpperCase());
            ps.setString(2, ca.getNombre());
            ps.setBoolean(3, ca.getEstado());
            ps.setInt(4, ca.getId());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
    }

    public List<CategoriaArticulo> findAll() {
        List<CategoriaArticulo> ccaa = new ArrayList<>();
        String sql = "SELECT id, codigo, nombre, estado FROM categorias_articulo ORDER BY codigo";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println(ps);
            while (rs.next()) {
                ccaa.add(rowMapper(rs));
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return ccaa;
    }

    public List<CategoriaArticulo> findByTermLike(String termino) {
        List<CategoriaArticulo> ccaa = new ArrayList<>();
        String sql = "SELECT id, codigo, nombre, estado FROM categorias_articulo WHERE UPPER(codigo) LIKE UPPER(?) OR UPPER(nombre) LIKE UPPER(?)";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + termino + "%");
            ps.setString(2, "%" + termino + "%");
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ccaa.add(rowMapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return ccaa;
    }

    public CategoriaArticulo findByCode(String codigo) {
        CategoriaArticulo ca = null;
        String sql = "SELECT id, codigo, nombre, estado FROM categorias_articulo WHERE UPPER(codigo) = UPPER(?)";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ca = rowMapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return ca;
    }

    public void activateByCodes(List<String> codigos, boolean estado) {
        String codes = codigos.stream().map(codigo -> "'" + codigo + "'").collect(Collectors.joining(", "));
        String sql = String.format("UPDATE categorias_articulo SET estado = %s WHERE codigo IN (%s)", estado, codes);
        System.out.println(sql);
        try (Connection conn = MySQLConection.getConnection();
             Statement s = conn.createStatement()) {
            s.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
    }

    private CategoriaArticulo rowMapper(ResultSet rs) throws SQLException {
        CategoriaArticulo ca = new CategoriaArticulo();
        ca.setId(rs.getInt(1));
        ca.setCodigo(rs.getString(2));
        ca.setNombre(rs.getString(3));
        ca.setEstado(rs.getBoolean(4));
        return ca;
    }

}