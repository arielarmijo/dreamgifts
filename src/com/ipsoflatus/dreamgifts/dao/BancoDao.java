/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipsoflatus.dreamgifts.dao;

import com.ipsoflatus.dreamgifts.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.Banco;
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
public class BancoDao {
    
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
            System.out.println(e);
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
     
      public void activateBANCOByCodes(List<String> codigos, boolean estado) {
        String codes = codigos.stream().map(codigo -> "'" + codigo + "'").collect(Collectors.joining(", "));
        String sql = String.format("UPDATE banco SET estado = %s WHERE codigo IN (%s)", estado, codes);
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
    private Banco rowMapper(ResultSet rs) throws SQLException {
        Banco banco = new Banco();
        banco.setId(rs.getInt(1));
        banco.setNombre(rs.getString(2));
        banco.setCodigo(rs.getString(3));
        banco.setEstado(rs.getBoolean(4));
        return banco;
    }
    
    public static void main(String[] args) {
        BancoDao bancoDao = new BancoDao();
        //Banco banco1 = new Banco("Estado", "ED", true);
        //bancoDao.save(banco1);
        
        //Banco banco2 = new Banco("Santander", "st", false);
        //bancoDao.save(banco2);
        
        bancoDao.findAll().forEach(System.out::println);
        
    }
    
}
