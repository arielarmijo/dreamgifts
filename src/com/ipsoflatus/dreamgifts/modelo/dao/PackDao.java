package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PackDao implements DAO<Pack> {

    public PackDao() {
    }

    @Override
    public List<Pack> findAll() {
        List<Pack> result = new ArrayList<>();
        String sql = "SELECT id, nombre, stock, costo, estado FROM packs";
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
    public List<Pack> findByTermLike(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pack findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Pack pack) {
        try (Connection conn = MySQLConection.getConnection()) {
            String insertPack = "INSERT INTO packs (nombre, stock, costo, estado) VALUES (?, ?, ?, ?)";
            try (PreparedStatement psPack = conn.prepareStatement(insertPack)) {
                conn.setAutoCommit(false);
                psPack.setString(1, pack.getNombre());
                psPack.setInt(2, pack.getStock());
                psPack.setInt(3, pack.getCosto());
                psPack.setBoolean(4, pack.getEstado());
                System.out.println(psPack);
                psPack.executeUpdate();
                String selectPack = "SELECT id FROM packs WHERE nombre = ?";
                try (PreparedStatement psPackId = conn.prepareStatement(selectPack)) {
                    psPackId.setString(1, pack.getNombre());
                    try (ResultSet rs = psPackId.executeQuery()) {
                        if (rs.next()) {
                            int packId = rs.getInt(1);
                            String insertPackHasArticulo = "INSERT INTO pack_has_articulo (pack_id, articulo_id, cantidad) VALUES (?, ?, ?)";
                            try (PreparedStatement psPackHasArticulo = conn.prepareCall(insertPackHasArticulo)) {
                                for (PackHasArticulo pha : pack.getArticulos()) {
                                    psPackHasArticulo.setInt(1, packId);
                                    psPackHasArticulo.setInt(2, pha.getArticuloId());
                                    psPackHasArticulo.setInt(3, pha.getCantidad());
                                    System.out.println(psPackHasArticulo);
                                    psPackHasArticulo.addBatch();
                                }
                                psPackHasArticulo.executeBatch();
                            }
                        }
                    }
                }
                conn.commit();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void update(Pack t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String attributes2values(String atributos, Function<String, String> mapper) {
        return Stream.of(atributos.split(", ")).map(mapper).collect(Collectors.joining(", "));
    }

    private Pack rowMapper(ResultSet rs) throws SQLException {
        Pack pack = new Pack();
        pack.setId(rs.getInt(1));
        pack.setNombre(rs.getString(2));
        pack.setStock(rs.getInt(3));
        pack.setCosto(rs.getInt(4));
        pack.setEstado(rs.getBoolean(5));
        return pack;
    }

}
