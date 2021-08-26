import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String nombreUsuario;
        String clave;
        String url;
        int opcion;
        do{
            System.out.println("Menu:\n" +
                    "1.Listar URL's de un usuario\n" +
                    "2.Iniciar Sesion\n" +
                    "3.Registrar un URL(necesita haber iniciado sesion)\n" +
                    "0. Salir");
            opcion = sc.nextInt();
            switch(opcion)
            {
                case 1:
                    System.out.println("Ingrese el usuario deseado: ");
                    nombreUsuario = sc.next();
                    consultarUrls(nombreUsuario);
                    break;

                case 2:
                    System.out.println("Ingrese el usuario deseado: ");
                    nombreUsuario = sc.next();
                    System.out.println("Ingrese la contraseña del usuario: ");
                    clave = sc.next();
                    iniciarSesion(nombreUsuario,clave);
                    break;

                case 3:
                    System.out.println("Ingrese la URL: ");
                    url = sc.next();
                    registrarUrl(url);
                    break;

                default :
                    System.out.println("Opcion incorrecta");
                    break;
            }
        }while(opcion != 0);

    }
    public static void consultarUrls(String nombreUsuario){
        String urlGet = Unirest.get("http://localhost:7000/api-rest/url/"+nombreUsuario)
                .asJson()
                .getBody()
                .toString();
        System.out.println(urlGet);
    }

    public static void iniciarSesion(String nombreUsuario, String clave) {
        HttpResponse<JsonNode> loginPOST = Unirest.post("http://localhost:7000/api-rest/login")
                .header("accept", "application/json")
                .queryString("nombreUsuario", nombreUsuario)
                .queryString("password", clave)
                .asJson();
        System.out.println("STATUS: " + loginPOST.getStatus());
    }

    public static void registrarUrl(String url){
        HttpResponse<JsonNode> registrarPOST = Unirest.post("http://localhost:7000/api-rest/url")
                .header("accept", "application/json")
                .queryString("url", url)
                .asJson();
        System.out.println("STATUS: " + registrarPOST.getStatus());
        System.out.println(registrarPOST.getBody().toString());
    }

}