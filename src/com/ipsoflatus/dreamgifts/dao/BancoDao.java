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
import java.util.ArrayList;
import java.util.List;

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
    
    public List<Banco> findAll() {
        List<Banco> bancos = new ArrayList<>();
        String sql = "SELECT nombre, codigo, estado FROM bancos";
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
    
    private Banco rowMapper(ResultSet rs) throws SQLException {
        Banco banco = new Banco();
        banco.setNombre(rs.getString(1));
        banco.setCodigo(rs.getString(2));
        banco.setEstado(rs.getBoolean(3));
        return banco;
    }
    
    public static void main(String[] args) {
        BancoDao bancoDao = new BancoDao();
        Banco banco1 = new Banco("Estado", "ED", true);
        //bancoDao.save(banco1);
        
        Banco banco2 = new Banco("Santander", "st", false);
        //bancoDao.save(banco2);
        
        bancoDao.findAll().forEach(System.out::println);
        
    }
    
}
