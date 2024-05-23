package datos.impl;

import datos.EventoDao;
import dominio.Evento;
import database.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventoDaoImpl implements EventoDao<Evento> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public EventoDaoImpl() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Evento> listar(String texto) {
        List<Evento> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("SELECT * FROM eventos WHERE nombre LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Evento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getDouble("costo"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at")
                ));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Evento obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO eventos (nombre, descripcion, fecha_inicio, fecha_fin, costo) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setDate(3, new java.sql.Date(obj.getFechaInicio().getTime()));
            ps.setDate(4, new java.sql.Date(obj.getFechaFin().getTime()));
            ps.setDouble(5, obj.getCosto());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean actualizar(Evento obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE eventos SET nombre=?, descripcion=?, fecha_inicio=?, fecha_fin=?, costo=?, updated_at=NOW() WHERE id=?");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setDate(3, new java.sql.Date(obj.getFechaInicio().getTime()));
            ps.setDate(4, new java.sql.Date(obj.getFechaFin().getTime()));
            ps.setDouble(5, obj.getCosto());
            ps.setInt(6, obj.getId());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean eliminar(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("DELETE FROM eventos WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }
}
