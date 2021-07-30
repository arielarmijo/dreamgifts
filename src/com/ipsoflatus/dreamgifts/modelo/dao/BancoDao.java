package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.entidad.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BancoDao implements DAO<Banco> {
    
    @Override
    public List<Banco> findAll() {
        List<Banco> bancos = new ArrayList<>();
        String sql = "SELECT id, nombre, codigo, estado FROM bancos";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            conn.setReadOnly(true);
            System.out.println(ps);
            while (rs.next()) {
                bancos.add(rowMapper(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
        return bancos;
    }
    
    public Banco findByCode(String codigo) {
        Banco bancos = null;
        String sql = "SELECT id, nombre, codigo, estado FROM bancos WHERE UPPER(codigo) = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setReadOnly(true);
            ps.setString(1, codigo.toUpperCase());
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
               if (rs.next()) {
                   bancos = rowMapper(rs);
               }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
        return bancos;
    }
    
    @Override
     public List<Banco> findByTermLike(String terminoBuscado) {
        List<Banco> bancos = new ArrayList<>();
        String sql = "SELECT id, nombre, codigo, estado FROM bancos WHERE UPPER(nombre) LIKE ? OR UPPER(codigo) LIKE ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setReadOnly(true);
            ps.setString(1, "%" + terminoBuscado.toUpperCase() + "%");
            ps.setString(2, "%" + terminoBuscado.toUpperCase() + "%");
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bancos.add(rowMapper(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
        return bancos;
    }
     
     @Override
    public Banco findById(int id) {
        Banco banco = null;
        String sql = "SELECT id, codigo, nombre, estado FROM bancos WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    banco = rowMapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
        return banco;
    }
     
     @Override
    public void save(Banco banco) {
        String sql = "INSERT INTO bancos (nombre, codigo, estado) VALUES (?, ?, ?)";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setString(1, banco.getNombre());
            ps.setString(2, banco.getCodigo().toUpperCase());
            ps.setBoolean(3, banco.isEstado());
            System.out.println(ps);
            ps.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DreamGiftsException(e.getMessage());
        }
    }
    
    @Override
    public void update(Banco banco) {
        String sql = "UPDATE bancos SET nombre = ?, codigo = ?, estado = ? WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setString(1, banco.getNombre());
            ps.setString(2, banco.getCodigo().toUpperCase());
            ps.setBoolean(3, banco.isEstado());
            ps.setInt(4, banco.getId());
            System.out.println(ps);
            ps.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
    }
    
    public void delete(Banco banco) {
        String sql = "DELETE FROM bancos WHERE codigo = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setString (1, banco.getCodigo().toUpperCase());
            ps.executeUpdate();
            System.out.println(ps);
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DreamGiftsException(e.getMessage());
        }
    }  

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        String in = ids.stream().map(id -> id.toString()).collect(Collectors.joining(", "));
        String sql = String.format("UPDATE bancos SET estado = %s WHERE id IN (%s)", estado, in);
        System.out.println(sql);
        try (Connection conn = MySQLConection.getConnection();
             Statement s = conn.createStatement()) {
            s.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DreamGiftsException(e.getMessage());
        }
    }

    private Banco rowMapper(ResultSet rs) throws SQLException {
        Banco banco = new Banco();
        banco.setId(rs.getInt(1));
        banco.setNombre(rs.getString(2));
        banco.setCodigo(rs.getString(3));
        banco.setEstado(rs.getBoolean(4));
        return banco;
    }
    
}
