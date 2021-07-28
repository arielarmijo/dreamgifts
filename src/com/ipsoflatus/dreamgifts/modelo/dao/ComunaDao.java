package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import com.ipsoflatus.dreamgifts.modelo.entidad.Comuna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComunaDao extends AbstractDao<Comuna> {

    public ComunaDao() {
        super("comunas");
        setAtributos("id, codigo, nombre, estado");
        setAtributosBusqueda("codigo, nombre");
    }
    
     
   
   @Override
   protected Comuna rowMapper(ResultSet rs) throws SQLException {
       Comuna c = new Comuna();
        c.setId(rs.getInt(1));
        c.setCodigo(rs.getString(2));
        c.setNombre(rs.getString(3));
        c.setEstado(rs.getBoolean(4));
        return c;
    }

    @Override
    protected void setInsertPS(PreparedStatement ps, Comuna c) throws SQLException {
        ps.setString(1, c.getCodigo());
        ps.setString(2, c.getNombre());
        ps.setBoolean(3, c.getEstado());
    }

    @Override
    protected void setUpdatePS(PreparedStatement ps, Comuna c) throws SQLException {
        setInsertPS(ps, c);
        ps.setInt(4, c.getId());
    }

}

