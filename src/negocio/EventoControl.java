package negocio;

import datos.EventoDao;
import datos.impl.EventoDaoImpl;
import dominio.Evento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class EventoControl {

    private final EventoDao DATOS;
    private Evento obj;

    public EventoControl() {
        this.DATOS = new EventoDaoImpl();
        this.obj = new Evento();
    }
    private DefaultTableModel modeloTabla;

    public DefaultTableModel listar(String texto) {
        List<Evento> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        // Establecemos las columnas del tableModel
        String[] titulos = {"ID", "Nombre", "Descripción", "Fecha Inicio", "Fecha Fin", "Costo"};
        // Declaramos un vector que será el que agreguemos como registro al DefaultTableModel
        Object[] registro = new Object[6];
        // Agregamos los títulos al DefaultTableModel
        this.modeloTabla = new DefaultTableModel(null, titulos);

        // Recorremos toda la lista y la pasamos al DefaultTableModel
        for (Evento item : lista) {
            registro[0] = item.getId();
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();
            registro[3] = item.getFechaInicio();
            registro[4] = item.getFechaFin();
            registro[5] = item.getCosto();
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public String insertar(Evento obj) {
        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el ingreso de datos.";
        }
    }

    public String actualizar(Evento obj) {
        if (DATOS.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la actualización de datos.";
        }
    }

    public String eliminar(int id) {
        if (DATOS.eliminar(id)) {
            return "OK";
        } else {
            return "Error en la eliminación de datos.";
        }
    }
}
