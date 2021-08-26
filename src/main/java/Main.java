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
        String token = null;
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
                    token = iniciarSesion(nombreUsuario,clave);
                    break;

                case 3:
                    System.out.println("Ingrese la URL: ");
                    url = sc.next();
                    registrarUrl(url,token);
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

    public static String iniciarSesion(String nombreUsuario, String clave) {
        HttpResponse<String> loginPOST = Unirest.post("http://localhost:7000/api-rest/login")
                .header("accept", "application/json")
                .queryString("nombreUsuario", nombreUsuario)
                .queryString("password", clave)
                .asString();

        System.out.println(loginPOST.getBody());
        return loginPOST.getBody();
    }

    public static void registrarUrl(String url, String token){
        URL urlResponse = Unirest.post("http://localhost:7000/api-rest/url")
                .header("accept", "application/json")
                .queryString("url", url)
                .header("Authorization", "Bearer " + token)
                .asObject(URL.class)
                .getBody();
        System.out.println("\nURL registrada correctamente");
        System.out.println("URL acortado: "+urlResponse.getDireccionAcortada()
        +"\n URL original: "+ urlResponse.getOrigen()+ "\n cantidad de visitas: "+ urlResponse.getClientes() + "\n Preview (imagen codificada B64): "
        + urlResponse.B64preview.substring(0,30)+"...\n\n");
    }

}