package com.ipsoflatus.dreamgifts.modelo.dao;

import com.ipsoflatus.dreamgifts.modelo.conexion.MySQLConection;
import com.ipsoflatus.dreamgifts.modelo.error.DreamGiftsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractDao<T> implements DAO<T> {

    protected final String tableName;
    protected String atributos;
    private String atributosInsert;
    private String atributosBusqueda;
    private String insertValues;
    private String setValues;
    private String searchValues;
    
    public AbstractDao(String tableName) {
        this.tableName = tableName;
    }

    public void setAtributos(String atributos) {
        this.atributos = atributos;
        this.atributosInsert = atributos.replaceFirst("id,\\ ", "");
        insertValues = attributes2values(atributosInsert, s -> "?");
        setValues = attributes2values(atributosInsert, s -> s + " = ?");
    }

    public void setAtributosBusqueda(String atributosBusqueda) {
        this.atributosBusqueda = atributosBusqueda;
         searchValues = Stream.of(atributosBusqueda.split(", ")).map(s -> String.format("UPPER(%s) LIKE UPPER(?)", s)).collect(Collectors.joining(" OR "));
    }
    
    @Override
    public List<T> findAll() {
        List<T> result = new ArrayList<>();
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
    public List<T> findByTermLike(String term) {
        List<T> result = new ArrayList<>();
        String sql = String.format("SELECT %s FROM %s WHERE %s", atributos, tableName, searchValues);
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String[] tmp = atributosBusqueda.split(", ");
            for (int i = 0; i < tmp.length; i++) {
                ps.setString(i+1, "%" + term + "%");
            }
            System.out.println(ps);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(rowMapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
        return result;
    }
   
    @Override
    public T findById(int id) {
        T result = null;
        String sql = String.format("SELECT %s FROM %s WHERE id = ?", atributos, tableName);
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
    public void save(T t) {
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, atributosInsert, insertValues);
        try (Connection conn = MySQLConection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            setInsertPS(ps, t);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void update(T t) {
        String sql = String.format("UPDATE %s SET %s WHERE id = ?", tableName, setValues);
        try (Connection conn = MySQLConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setUpdatePS(ps, t);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
    }

    @Override
    public void updateStateByIds(List<Integer> ids, boolean estado) {
        String in = ids.stream().map(id -> id.toString()).collect(Collectors.joining(", "));
        String sql = String.format("UPDATE %s SET estado = %s WHERE id IN (%s)", tableName, estado, in);
        System.out.println(sql);
        try (Connection conn = MySQLConection.getConnection();
             Statement s = conn.createStatement()) {
            s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DreamGiftsException(e.getMessage());
        }
    }
    
    protected abstract void setInsertPS(PreparedStatement ps, T t) throws SQLException;
    protected abstract void setUpdatePS(PreparedStatement ps, T t) throws SQLException;
    protected abstract T rowMapper(ResultSet rs) throws SQLException;
    
    private String attributes2values(String atributos, Function<String, String> mapper) {
        return Stream.of(atributos.split(", ")).map(mapper).collect(Collectors.joining(", "));
    }

}
