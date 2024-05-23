package datos;

import java.util.List;

public interface EventoDao<T> {
    List<T> listar(String texto);
    boolean insertar(T obj);
    boolean actualizar(T obj);
    boolean eliminar(int id);
}
