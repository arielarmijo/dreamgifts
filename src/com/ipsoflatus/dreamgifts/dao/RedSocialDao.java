package com.ipsoflatus.dreamgifts.dao;

import com.ipsoflatus.dreamgifts.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.RedSocial;
import com.ipsoflatus.dreamgifts.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RedSocialDao {
    
    public List<RedSocial> findAll() {
        List<RedSocial> rrss = new ArrayList<>();
        String sql = "SELECT id, nombre, codigo FROM rrss";
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
        String sql = "SELECT id, nombre, codigo FROM rrss WHERE id = ?";
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
        String sql = "SELECT id, nombre, codigo FROM rrss WHERE codigo = ?";
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
        String sql = "SELECT id, nombre, codigo FROM rrss WHERE UPPER(nombre) LIKE ? OR UPPER(codigo) LIKE ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setReadOnly(true);
            ps.setString(1, "%" + terminoBuscado.toUpperCase() + "%");
            ps.setString(2, "%" + terminoBuscado.toUpperCase() + "%");
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
        String sql = "INSERT INTO rrss (nombre, codigo) VALUES (?, ?)";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setString(1, redSocial.getNombre());
            ps.setString(2, redSocial.getCodigo().toUpperCase());
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
        String sql = "UPDATE rrss SET nombre = ?, codigo = ? WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setString(1, redSocial.getNombre());
            ps.setString(2, redSocial.getCodigo());
            ps.setInt(3, redSocial.getId());
            System.out.println(ps);
            ps.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
    }
    
    public void delete(RedSocial redSocial) {
        String sql = "DELETE FROM rrss WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setInt(1, redSocial.getId());
            ps.executeUpdate();
            System.out.println(ps);
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DreamGiftsException(e.getMessage());
        }
    }
    
    private RedSocial rowMapper(ResultSet rs) throws SQLException {
        RedSocial redSocial = new RedSocial();
        redSocial.setId(rs.getInt(1));
        redSocial.setNombre(rs.getString(2));
        redSocial.setCodigo(rs.getString(3));
        return redSocial;
    }
   
}
