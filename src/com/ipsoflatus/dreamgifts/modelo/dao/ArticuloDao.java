package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.entidad.Articulo;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDao extends AbstractDao<Articulo> {
    
    public ArticuloDao() {
        super("articulos");
        setAtributos("id, nombre, marca, stock, fecha_vencimiento, estado, categoria_articulo_id");
        setAtributosBusqueda("nombre, marca");
    }
    
    public List<Articulo> findByCategoryId(int categoriaArticuloId) {
        List<Articulo> result = new ArrayList<>();
        String sql = String.format("SELECT %s FROM %s WHERE categoria_articulo_id = %d", atributos, tableName, categoriaArticuloId);
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
    protected Articulo rowMapper(ResultSet rs) throws SQLException {
        Articulo articulo = new Articulo();
        articulo.setId(rs.getInt(1));
        articulo.setNombre(rs.getString(2));
        articulo.setMarca(rs.getString(3));
        articulo.setStock(rs.getInt(4));
        articulo.setFechaVencimiento(rs.getDate(5));
        articulo.setEstado(rs.getBoolean(6));
        articulo.setCategoriaId(rs.getInt(7));
        return articulo;
    }

    @Override
    protected void setInsertPS(PreparedStatement ps, Articulo a) throws SQLException {
        ps.setString(1, a.getNombre());
        ps.setString(2, a.getMarca());
        ps.setInt(3, a.getStock());
        ps.setDate(4, a.getFechaVencimiento());
        ps.setBoolean(5, a.getEstado());
        ps.setInt(6, a.getCategoriaId());
    }

    @Override
    protected void setUpdatePS(PreparedStatement ps, Articulo a) throws SQLException {
        setInsertPS(ps, a);
        ps.setInt(7, a.getId());
    }
    
}
