package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.entidad.Venta;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void update(Venta t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        System.out.println(ps);
    }
    
    private String mapAttributes(String atributos, Function<String, String> mapper) {
        return Stream.of(atributos.split(", ")).map(mapper).collect(Collectors.joining(", "));
    }

}
