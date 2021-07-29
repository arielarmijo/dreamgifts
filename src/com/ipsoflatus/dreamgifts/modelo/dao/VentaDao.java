package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
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

public class VentaDao implements DAO<Venta> {

    private final String tableName;
    private final String atributos;
    
    public VentaDao() {
        this.tableName = "ventas";
        this.atributos = "id, cliente_id, total, fecha_venta, fecha_transferencia, codigo_transferencia, banco_id, nombre_destinatario, apellido_destinatario, direccion_destinatario, comuna_id, telefono_destinatario, fecha_entrega, hora_entrega_inicial, hora_entrega_final, saludo, rrss_id, estado_venta_id, pack_id";
    }
    
    @Override
    public List<Venta> findAll() {
        List<Venta> results = new ArrayList<>();
        String sql = String.format("SELECT %s FROM %s", atributos, tableName);
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            conn.setReadOnly(true);
            System.out.println(ps);
            while (rs.next()) {
                results.add(rowMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
        return results;
    }

    @Override
    public List<Venta> findByTermLike(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venta findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Venta c) {
        String insertAttributes = atributos.replaceFirst("id,\\ ", "");
        String insertValues = mapAttributes(insertAttributes, s -> "?");
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, insertAttributes, insertValues);
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setInsertPS(ps, c);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void update(Venta v) {
        String setValues = mapAttributes(atributos.replaceFirst("id,\\ ", ""), s -> s + " = ?");
        String sql = String.format("UPDATE %s SET %s WHERE id = ?", tableName, setValues);
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setInsertPS(ps, v);
            ps.setInt(19, v.getId());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Venta rowMapper(ResultSet rs) throws SQLException {
        Venta v = new Venta();
        v.setId(rs.getInt(1));
        v.setClienteId(rs.getInt(2));
        v.setTotal(rs.getInt(3));
        v.setFechaVenta(rs.getDate(4));
        v.setFechaTransferencia(rs.getDate(5));
        v.setCodigoTransferencia(rs.getInt(6));
        v.setBancoId(rs.getInt(7));
        v.setNombreDestinatario(rs.getString(8));
        v.setApellidoDestinatario(rs.getString(9));
        v.setDireccionDestinatario(rs.getString(10));
        v.setComunaId(rs.getInt(11));
        v.setTelefonoDestinatario(rs.getString(12));
        v.setFechaEntrega(rs.getDate(13));
        v.setHoraEntregaInicial(rs.getTime(14));
        v.setHoraEntregaFinal(rs.getTime(15));
        v.setSaludo(rs.getString(16));
        v.setRrssId(rs.getInt(17));
        v.setEstadoVentaId(rs.getInt(18));
        v.setPackId(rs.getInt(19));
        return v;
    }

    private void setInsertPS(PreparedStatement ps, Venta v) throws SQLException {
        ps.setInt(1, v.getClienteId());
        ps.setInt(2, v.getTotal());
        ps.setDate(3, v.getFechaVenta());
        ps.setDate(4, v.getFechaTransferencia());
        ps.setObject(5, v.getCodigoTransferencia());
        ps.setObject(6, v.getBancoId());
        ps.setString(7, v.getNombreDestinatario());
        ps.setString(8, v.getApellidoDestinatario());
        ps.setString(9, v.getDireccionDestinatario());
        ps.setInt(10, v.getComunaId());
        ps.setString(11, v.getTelefonoDestinatario());
        ps.setDate(12, v.getFechaEntrega());
        ps.setTime(13, v.getHoraEntregaInicial());
        ps.setTime(14, v.getHoraEntregaFinal());
        ps.setString(15, v.getSaludo());
        ps.setObject(16, v.getRrssId());
        ps.setObject(17, v.getEstadoVentaId());
        ps.setInt(18, v.getPackId());
    }
    
    private String mapAttributes(String atributos, Function<String, String> mapper) {
        return Stream.of(atributos.split(", ")).map(mapper).collect(Collectors.joining(", "));
    }

}
