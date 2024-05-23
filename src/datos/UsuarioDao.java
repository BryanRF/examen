package datos;

import java.util.List;


public interface UsuarioDao<T> {
   public List<T> listar(String texto,int totalPorPagina,int numPagina);
    public boolean insertar(T obj);
    public boolean actualizar(T obj);
    public boolean desactivar(int id);
    public boolean activar(int id);
    public int total();
    public int existe(String texto); 
    public T login(String email, String password);
}
