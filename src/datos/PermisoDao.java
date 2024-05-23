package datos;

import java.util.List;

/**
 *
 * @author incanatoapps
 */
public interface PermisoDao<T> {
    public List<T> listar(String texto);
    public T findByNombre(String texto);
    public boolean insertar(T obj);
    public boolean actualizar(T obj);
    public boolean eliminar(int id);
}

