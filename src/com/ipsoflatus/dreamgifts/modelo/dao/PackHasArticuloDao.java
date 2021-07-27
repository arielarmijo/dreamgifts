package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackHasArticuloDao implements DAO<PackHasArticulo> {
    
    private final String tableName = "pack_has_articulo";
    private final String atributos = "pack_id, articulo_id, cantidad";

    @Override
    public List<PackHasArticulo> findAll() {
        List<PackHasArticulo> result = new ArrayList<>();
        String sql = String.format("SELECT %s FROM %s", atributos, tableName);
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            conn.setReadOnly(true);
            System.out.println(ps);
            while (rs.next()) {
                result.add(rowMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
        return result;
    }

    @Override
    public List<PackHasArticulo> findByTermLike(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PackHasArticulo findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(PackHasArticulo t) {
        String sql = String.format("INSERT INTO %$1s (%$2s) VALUES (%$2s)", tableName, atributos);
        try (Connection conn = MySQLConection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getPackId());
            ps.setInt(2, t.getArticuloId());
            ps.setInt(3, t.getCantidad());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void update(PackHasArticulo t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private PackHasArticulo rowMapper(ResultSet rs) throws SQLException {
        PackHasArticulo pha = new PackHasArticulo();
        pha.setPackId(rs.getInt(1));
        pha.setArticuloId(rs.getInt(2));
        pha.setCantidad(rs.getInt(3));
        return pha;
    }

}
