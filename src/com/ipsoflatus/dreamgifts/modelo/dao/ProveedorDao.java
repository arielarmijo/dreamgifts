package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.entidad.Proveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveedorDao extends AbstractDao<Proveedor> {

    public ProveedorDao() {
        super("proveedores");
        setAtributos("id, rut, razon_social, contacto, direccion, comunas_id, telefono, email, estado");
        setAtributosBusqueda("rut, razon_social, contacto");
    }
    
    @Override
    protected void setInsertPS(PreparedStatement ps, Proveedor p) throws SQLException {
        ps.setString(1, p.getRut());
        ps.setString(2, p.getRazonSocial());
        ps.setString(3, p.getContacto());
        ps.setString(4, p.getDireccion());
        ps.setInt(5, p.getComunaId());
        ps.setString(6, p.getTelefono());
        ps.setString(7, p.getEmail());
        ps.setBoolean(8, p.getEstado());
    }

    @Override
    protected void setUpdatePS(PreparedStatement ps, Proveedor p) throws SQLException {
        setInsertPS(ps, p);
        ps.setInt(9, p.getId());
    }

    @Override
    protected Proveedor rowMapper(ResultSet rs) throws SQLException {
        Proveedor p = new Proveedor();
        p.setId(rs.getInt(1));
        p.setRut(rs.getString(2));
        p.setRazonSocial(rs.getString(3));
        p.setContacto(rs.getString(4));
        p.setDireccion(rs.getString(5));
        p.setComunaId(rs.getInt(6));
        p.setTelefono(rs.getString(7));
        p.setEmail(rs.getString(8));
        p.setEstado(rs.getBoolean(9));
        return p;
    }
    
    public static void main(String[] args) {
        ProveedorDao dao = new ProveedorDao();
        Proveedor p = new Proveedor();
        p.setRut("11-3");
        p.setRazonSocial("Testing");
        p.setContacto("juan");
        p.setDireccion("test 1234");
        p.setComunaId(1);
        p.setTelefono("5555");
        p.setEmail("email@com");
        p.setEstado(Boolean.TRUE);
        dao.save(p);
        dao.findAll().forEach(proveedor -> {
            System.out.println(proveedor);
        });
    }
           

}
