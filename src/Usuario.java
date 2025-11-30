public class Usuario {
    private int idUsuario;
    private String nombre;
    private String password;
    private String rol;

    public Usuario(int idUsuario, String nombre, String password, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    public boolean login(String user, String pass){
        return this.nombre.equals(user) && this.password.equals(pass);
    }
}
