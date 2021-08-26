
import java.util.HashSet;
import java.util.Set;

public class URL{

    private String direccionAcortada; //direccion acortada
    private String origen; //Origen que se va a acortar
    private Set<Cliente> clientes;
    public String B64preview;


    public URL() {
        if(clientes == null) {clientes = new HashSet<>();}
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDireccionAcortada() {
        return direccionAcortada;
    }

    public void setDireccionAcortada(String direccion) {
        this.direccionAcortada = direccion;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public long getCantidadClientes(){
        return this.clientes.size();
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }
}
