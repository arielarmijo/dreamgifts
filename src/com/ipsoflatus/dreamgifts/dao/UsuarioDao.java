    package com.ipsoflatus.dreamgifts.dao;

import com.ipsoflatus.dreamgifts.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.entidad.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDao {

    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nombre, clave, estado FROM usuarios";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            conn.setReadOnly(true);
            System.out.println(ps);
            while (rs.next()) {
                usuarios.add(rowMapper(rs));
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return usuarios;
    }
    
    public List<Usuario> findByIdIn(List<Usuario> users) {
        List<Usuario> usuarios = new ArrayList<>();
        String ids = users.stream().map(u -> u.getId().toString()).collect(Collectors.joining(", "));
        String sql = String.format("SELECT id, nombre, clave, estado FROM usuarios WHERE id IN (%s)", ids);
        System.out.println(sql);
        try (Connection conn = MySQLConection.getConnection();
             Statement stm = conn.createStatement()) {
            try (ResultSet rs = stm.executeQuery(sql)) {
                while (rs.next()) {
                    usuarios.add(rowMapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return usuarios;
    }
    
    public List<Usuario> findByNameIn(List<String> names) {
        List<Usuario> usuarios = new ArrayList<>();
        String nombres = names.stream().map(nombre -> "'" + nombre + "'").collect(Collectors.joining(", "));
        String sql = String.format("SELECT id, nombre, clave, estado FROM usuarios WHERE nombre IN (%s)", nombres);
        System.out.println(sql);
        try (Connection conn = MySQLConection.getConnection();
             Statement stm = conn.createStatement()) {
            try (ResultSet rs = stm.executeQuery(sql)) {
                while (rs.next()) {
                    usuarios.add(rowMapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return usuarios;
    }
    
    public List<Usuario> findByNameLike(String nombre) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nombre, clave, estado FROM usuarios WHERE nombre LIKE ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setReadOnly(true);
            ps.setString(1, "%" + nombre + "%");
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(rowMapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return usuarios;
    }
    
    public Usuario findById(int id) {
        Usuario usuario = null;
        String sql = "SELECT id, nombre, clave, estado FROM usuarios WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setReadOnly(true);
            ps.setInt(1, id);
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
               if (rs.next()) {
                   usuario = rowMapper(rs);
               }
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return usuario;
    }
    
    public Usuario findByName(String nombre) {
        Usuario usuario = null;
        String sql = "SELECT id, nombre, clave, estado FROM usuarios WHERE nombre = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setReadOnly(true);
            ps.setString(1, nombre);
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = rowMapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return usuario;
    }   

    public Usuario save(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, clave, estado) VALUES (?, ?, ?)";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getClave());
            ps.setBoolean(3, usuario.getEstado());
            System.out.println(ps);
            ps.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
        return findByName(usuario.getNombre());
    }
    
    public Usuario update(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, clave = ?, estado = ? WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getClave());
            ps.setBoolean(3, usuario.getEstado());
            ps.setInt(4, usuario.getId());
            System.out.println(ps);
            ps.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
        return findById(usuario.getId());
    }

    public void delete(int id) {
        Usuario usuario = findById(id);
        String sql = "DELETE FROM usuarios WHERE id = ?";
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
    
    public List<Usuario> activateByNames(List<String> nombreUsuarios, boolean estado) {
        String nombres = nombreUsuarios.stream().map(nombre -> "'" + nombre + "'").collect(Collectors.joining(", "));
        String sql = String.format("UPDATE usuarios SET estado = %s WHERE nombre IN (%s)", estado, nombres);
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
        return findByNameIn(nombreUsuarios);
    }
    
    private Usuario rowMapper(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt(1));
        usuario.setNombre(rs.getString(2));
        usuario.setClave(rs.getString(3));
        usuario.setEstado(rs.getBoolean(4));
        return usuario;
    }
    
}
