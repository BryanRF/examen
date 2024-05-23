package dominio;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private boolean activo;
    private int rolId;
    private String rolNombre;

    public Usuario() {
    }

    public Usuario(int id, String email, boolean activo, String rolNombre) {
        this.id = id;
        this.email = email;
        this.activo = activo;
        this.rolNombre = rolNombre;
    }
    
    public Usuario(int id, String nombre, String email, String password, boolean activo, int rolId, String rolNombre) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.activo = activo;
        this.rolId = rolId;
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }   
}
