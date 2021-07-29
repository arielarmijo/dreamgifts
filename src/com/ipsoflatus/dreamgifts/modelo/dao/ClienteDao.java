package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.entidad.Cliente;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ClienteDao extends AbstractDao<Cliente> {

    public ClienteDao() {
        super("clientes");
        setAtributos("id, rut, nombre, apellido, correo, direccion, comuna_id, telefono, celular, fecha_nacimiento, estado");
        setAtributosBusqueda("rut, nombre, apellido");
    }
    
    @Override
    protected void setInsertPS(PreparedStatement ps, Cliente c) throws SQLException {
        ps.setString(1, c.getRut());
        ps.setString(2, c.getNombre());
        ps.setString(3, c.getApellido());
        ps.setString(4, c.getCorreo());
        ps.setString(5, c.getDireccion());
        ps.setInt(6, c.getComunaId());
        ps.setString(7, c.getTelefono());
        ps.setString(8, c.getCelular());
        ps.setDate(9, c.getFechaNacimiento());
        ps.setBoolean(10, c.getEstado());
    }

    @Override
    protected void setUpdatePS(PreparedStatement ps, Cliente c) throws SQLException {
        ps.setString(1, c.getRut());
        ps.setString(2, c.getNombre());
        ps.setString(3, c.getApellido());
        ps.setString(4, c.getCorreo());
        ps.setString(5, c.getDireccion());
        ps.setInt(6, c.getComunaId());
        ps.setString(7, c.getTelefono());
        ps.setString(8, c.getCelular());
        ps.setDate(9, c.getFechaNacimiento());
        ps.setBoolean(10, c.getEstado());
        ps.setInt(11, c.getId());
    }
    
    @Override
    protected Cliente rowMapper(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt(1));
        cliente.setRut(rs.getString(2));
        cliente.setNombre(rs.getString(3));
        cliente.setApellido(rs.getString(4));
        cliente.setCorreo(rs.getString(5));
        cliente.setDireccion(rs.getString(6));
        cliente.setComunaId(rs.getInt(7));
        cliente.setTelefono(rs.getString(8));
        cliente.setCelular(rs.getString(9));
        cliente.setFechaNacimiento(rs.getDate(10));
        cliente.setEstado(rs.getBoolean(11));
        return cliente;
    }

    public static void main(String[] args) {
        ClienteDao dao = new ClienteDao();
        Cliente cliente = new Cliente();
        cliente.setComunaId(1);
        cliente.setEstado(Boolean.TRUE);
        cliente.setRut("1-1");
        cliente.setNombre("test");
        cliente.setApellido("test");
        cliente.setCorreo("1");
        cliente.setDireccion("");
        cliente.setCelular("");
        dao.save(cliente);
        System.out.println(dao.findById(1));
        System.out.println(dao.findByTermLike("1-1"));
        dao.updateStateByIds(Arrays.asList(1), true);
        System.out.println(dao.findAll());
        dao.findAll().forEach(c -> {
            dao.update(c);
        });
    }

    public Cliente findByRut(String rut) {
        Cliente result = null;
        String sql = String.format("SELECT %s FROM %s WHERE rut = ?", atributos, tableName);
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rut);
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
    
}
