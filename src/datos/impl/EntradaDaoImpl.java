package datos.impl;

import datos.EntradaDao;
import dominio.Entrada;
import database.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntradaDaoImpl implements EntradaDao<Entrada> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public EntradaDaoImpl() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Entrada> listar(String texto) {
        List<Entrada> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("SELECT * FROM entradas WHERE dni LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Entrada(
                        rs.getInt("id"),
                        rs.getInt("evento_id"),
                        rs.getDouble("pago"),
                        rs.getString("dni"),
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
    public boolean insertar(Entrada obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO entradas (evento_id, pago, dni) VALUES (?, ?, ?)");
            ps.setInt(1, obj.getEventoId());
            ps.setDouble(2, obj.getPago());
            ps.setString(3, obj.getDni());
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
    public boolean actualizar(Entrada obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE entradas SET evento_id=?, pago=?, dni=?, updated_at=NOW() WHERE id=?");
            ps.setInt(1, obj.getEventoId());
            ps.setDouble(2, obj.getPago());
            ps.setString(3, obj.getDni());
            ps.setInt(4, obj.getId());
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
            ps = CON.conectar().prepareStatement("DELETE FROM entradas WHERE id=?");
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
