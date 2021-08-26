import java.util.Set;

public class Usuario {

    private String nombreUsuario;
    private String password;
    private String nombre;
    private RolesApp rol;

    private Set<URL> urls;

    public Usuario(){}

    public Usuario(String usuario, String password, String nombre, RolesApp rol){
        this.nombreUsuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<URL> getUrls() {
        return urls;
    }

    public void setUrls(Set<URL> urls) {
        this.urls = urls;
    }

    public RolesApp getRol() {
        return rol;
    }
    public void setRol(RolesApp rol) {
        this.rol = rol;
    }
}
