package com.ipsoflatus.dreamgifts.dao;

import com.ipsoflatus.dreamgifts.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.entidad.RedSocial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RedSocialDao {
    
    public List<RedSocial> findAll() {
        List<RedSocial> rrss = new ArrayList<>();
        String sql = "SELECT id, codigo, nombre, estado FROM rrss";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            conn.setReadOnly(true);
            System.out.println(ps);
            while (rs.next()) {
                rrss.add(rowMapper(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
        return rrss;
    }
    
    public RedSocial findById(int id) {
        RedSocial redSocial = null;
        String sql = "SELECT id, codigo, nombre, estado FROM rrss WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setReadOnly(true);
            ps.setInt(1, id);
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
               if (rs.next()) {
                   redSocial = rowMapper(rs);
               }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
        return redSocial;
    }
    
    public RedSocial findByCode(String codigo) {
        RedSocial redSocial = null;
        String sql = "SELECT id, codigo, nombre, estado FROM rrss WHERE UPPER(codigo) = UPPER(?)";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setReadOnly(true);
            ps.setString(1, codigo);
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
               if (rs.next()) {
                   redSocial = rowMapper(rs);
               }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
        return redSocial;
    }
    
    public List<RedSocial> findByTermLike(String terminoBuscado) {
        List<RedSocial> rrss = new ArrayList<>();
        String sql = "SELECT id, codigo, nombre, estado FROM rrss WHERE UPPER(nombre) LIKE UPPER(?) OR UPPER(codigo) LIKE UPPER(?)";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setReadOnly(true);
            ps.setString(1, "%" + terminoBuscado + "%");
            ps.setString(2, "%" + terminoBuscado + "%");
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rrss.add(rowMapper(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
        return rrss;
    }
    
    public void save(RedSocial redSocial) {
        String sql = "INSERT INTO rrss (codigo, nombre, estado) VALUES (?, ?, ?)";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setString(1, redSocial.getCodigo().toUpperCase());
            ps.setString(2, redSocial.getNombre());
            ps.setBoolean(3, redSocial.isEstado());
            System.out.println(ps);
            ps.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
    }
    
    public void update(RedSocial redSocial) {
        String sql = "UPDATE rrss SET codigo = UPPER(?), nombre = ?, estado = ? WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setString(1, redSocial.getCodigo());
            ps.setString(2, redSocial.getNombre());
            ps.setBoolean(3, redSocial.isEstado());
            ps.setInt(4, redSocial.getId());
            System.out.println(ps);
            ps.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM rrss WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println(ps);
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DreamGiftsException(e.getMessage());
        }
    }
   
    public void activateByCodes(List<String> codigos, boolean estado) {
        String codes = codigos.stream().map(codigo -> "'" + codigo + "'").collect(Collectors.joining(", "));
        String sql = String.format("UPDATE rrss SET estado = %s WHERE codigo IN (%s)", estado, codes);
        System.out.println(sql);
        try (Connection conn = MySQLConection.getConnection();
             Statement s = conn.createStatement()) {
            conn.setAutoCommit(false);
            s.executeUpdate(sql);
            conn.commit();conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
    }
    
    private RedSocial rowMapper(ResultSet rs) throws SQLException {
        RedSocial redSocial = new RedSocial();
        redSocial.setId(rs.getInt(1));
        redSocial.setCodigo(rs.getString(2));
        redSocial.setNombre(rs.getString(3));
        redSocial.setEstado(rs.getBoolean(4));
        return redSocial;
    }
   
}
