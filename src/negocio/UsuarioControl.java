package negocio;

import datos.impl.UsuarioDaoImpl;
import dominio.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioControl {

    private final UsuarioDaoImpl DATOS;
    private Usuario obj;

    public UsuarioControl() {
        this.DATOS = new UsuarioDaoImpl();
        this.obj = new Usuario();
    }

    public String login(String email, String clave) {
        String resp = "0";
        Usuario usu = DATOS.login(email, encriptar(clave));
        if (usu != null) {
            if (usu.isActivo()) {
                Variables.usuarioId = usu.getId();
                Variables.rolNombre = usu.getRolNombre();
                Variables.usuarioNombre = usu.getEmail();
                resp = "1";
            } else {
                resp = "2";
            }
        }
        return resp;
    }

    private static String encriptar(String valor) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("sha-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        byte[] hash = md.digest(valor.getBytes());
        StringBuilder sb = new StringBuilder();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        UsuarioControl control=new UsuarioControl();
        System.out.println(control.login("admin@prueba.com","admin"));
        System.out.println(Variables.usuarioId);
        System.out.println(Variables.rolNombre);
        System.out.println(Variables.usuarioNombre);
    }
}
