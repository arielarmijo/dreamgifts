package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.entidad.Pack;
import com.ipsoflatus.dreamgifts.modelo.entidad.PackHasArticulo;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PackDao implements DAO<Pack> {

    public PackDao() {
    }

    @Override
    public List<Pack> findAll() {
        
        List<Pack> result = new ArrayList<>();
        
        String selectPack = "SELECT id, nombre, stock, costo, estado FROM packs";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement packPs = conn.prepareStatement(selectPack);
             ResultSet packRs = packPs.executeQuery()) {
            
            conn.setReadOnly(true);
            
            System.out.println(packPs);
            
            while (packRs.next()) {
                
                Pack pack = rowMapper(packRs);
                
                String selectPackHasArticulo = "SELECT pack_id, articulo_id, cantidad FROM pack_has_articulo WHERE pack_id = ?";
                try (PreparedStatement packHasArticuloPs = conn.prepareStatement(selectPackHasArticulo)) {
                    packHasArticuloPs.setInt(1, pack.getId());
                    System.out.println(packHasArticuloPs);
                    try (ResultSet packHasArticuloRs = packHasArticuloPs.executeQuery()) {
                        List<PackHasArticulo> articulos = new ArrayList<>();
                        while (packHasArticuloRs.next()) {
                            PackHasArticulo pha = new PackHasArticulo();
                            pha.setPackId(packHasArticuloRs.getInt(1));
                            pha.setArticuloId(packHasArticuloRs.getInt(2));
                            pha.setCantidad(packHasArticuloRs.getInt(3));
                            articulos.add(pha);
                        }
                        pack.setArticulos(articulos);
                    }
                }
                
                result.add(pack);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
        
        return result;
        
    }

    @Override
    public List<Pack> findByTermLike(String term) {
        List<Pack> result = new ArrayList<>();
        String selectPack = "SELECT id, nombre, stock, costo, estado FROM packs WHERE UPPER(nombre) LIKE UPPER(?)";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement packPs = conn.prepareStatement(selectPack)) {
            conn.setReadOnly(true);
            packPs.setString(1, "%" + term + "%");
            System.out.println(packPs);
            try (ResultSet packRs = packPs.executeQuery()) {
                while (packRs.next()) {
                    Pack pack = rowMapper(packRs);
                    String selectPackHasArticulo = "SELECT pack_id, articulo_id, cantidad FROM pack_has_articulo WHERE pack_id = ?";
                    try (PreparedStatement packHasArticuloPs = conn.prepareStatement(selectPackHasArticulo)) {
                        packHasArticuloPs.setInt(1, pack.getId());
                        System.out.println(packHasArticuloPs);
                        try (ResultSet packHasArticuloRs = packHasArticuloPs.executeQuery()) {
                            List<PackHasArticulo> articulos = new ArrayList<>();
                            while (packHasArticuloRs.next()) {
                                PackHasArticulo pha = new PackHasArticulo();
                                pha.setPackId(packHasArticuloRs.getInt(1));
                                pha.setArticuloId(packHasArticuloRs.getInt(2));
                                pha.setCantidad(packHasArticuloRs.getInt(3));
                                articulos.add(pha);
                            }
                            pack.setArticulos(articulos);
                        }
                    }
                    result.add(pack);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
        return result;
    }

    @Override
    public Pack findById(int id) {
        Pack result = null;
        String sql = "SELECT id, nombre, stock, costo, estado FROM packs WHERE id = ?";
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rowMapper(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
        return result;
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
                    System.out.println(psPackId);
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
    public void update(Pack pack) {
        
        try (Connection conn = MySQLConection.getConnection()) {
            
            // Inicia transaccón
            conn.setAutoCommit(false);
            
            // Actualiza pack
            String update = "UPDATE packs SET nombre = ?, stock = ?, costo = ?, estado =? WHERE id = ?";
            try (PreparedStatement packPs = conn.prepareStatement(update)) {
                packPs.setString(1, pack.getNombre());
                packPs.setInt(2, pack.getStock());
                packPs.setInt(3, pack.getCosto());
                packPs.setBoolean(4, pack.getEstado());
                packPs.setInt(5, pack.getId());
                System.out.println(packPs);
                packPs.executeUpdate();
                
                // Borra articulos asociados al pack
                String delete = "DELETE FROM pack_has_articulo WHERE pack_id = ?";
                try (PreparedStatement ps = conn.prepareStatement(delete)) {
                    ps.setInt(1, pack.getId());
                    System.out.println(ps);
                    ps.executeUpdate();
                }
                
                // Agrega articulos
                List<PackHasArticulo> articulos = pack.getArticulos();
                String insert = "INSERT INTO pack_has_articulo (pack_id, articulo_id, cantidad) VALUES(?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(insert)) {
                    for (PackHasArticulo articulo : articulos) {
                        ps.setInt(1, pack.getId());
                        ps.setInt(2, articulo.getArticuloId());
                        ps.setInt(3, articulo.getCantidad());
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }
                
                // Confirma cambios
                conn.commit();
                conn.setAutoCommit(true);
                
            } catch (SQLException e) {
                // Revierte cambios
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        String in = ids.stream().map(id -> id.toString()).collect(Collectors.joining(", "));
        String sql = String.format("UPDATE packs SET estado = %s WHERE id IN (%s)", estado, in);
        System.out.println(sql);
        try (Connection conn = MySQLConection.getConnection();
             Statement s = conn.createStatement()) {
            s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
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
