package negocio;

import datos.EntradaDao;
import datos.impl.EntradaDaoImpl;
import dominio.Entrada;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class EntradaControl {

    private final EntradaDao DATOS;
    private Entrada obj;

    public EntradaControl() {
        this.DATOS = new EntradaDaoImpl();
        this.obj = new Entrada();
    }
    private DefaultTableModel modeloTabla;

    public DefaultTableModel listar(String texto) {
        List<Entrada> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        // Establecemos las columnas del tableModel
        String[] titulos = {"ID", "Evento ID", "Pago", "DNI"};
        // Declaramos un vector que será el que agreguemos como registro al DefaultTableModel
        Object[] registro = new Object[4];
        // Agregamos los títulos al DefaultTableModel
        this.modeloTabla = new DefaultTableModel(null, titulos);

        // Recorremos toda la lista y la pasamos al DefaultTableModel
        for (Entrada item : lista) {
            registro[0] = item.getId();
            registro[1] = item.getEventoId();
            registro[2] = item.getPago();
            registro[3] = item.getDni();
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public String insertar(Entrada obj) {
        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el ingreso de datos.";
        }
    }

    public String actualizar(Entrada obj) {
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
