from suds.client import Client

dominio = "http://localhost:7000/ws/SoapServices?wsdl"
cliente = Client(dominio)
usuarioID = ''

def getAllByUsuario(cliente):
    nombreUsuario = input('Ingrese usuario en cuestion: ')
    for url in (cliente.service.getEnlacesByUser(nombreUsuario)):
        print (url)
    return

def login(cliente):
    aux = True
    global usuarioID
    while(aux):
        print('Inicio de Sesion\n')

        nombreUsuario = input('Ingrese nombre de usuario: ')
        clave = input('Ingrese contraseña: ')
        success = cliente.service.login(nombreUsuario, clave)
        print(success)

        if(success == True):
            print('Sesion iniciada\n')
            usuarioID = nombreUsuario
            return
        else:
            respuesta = input('error! desea intentar nuevamente? (S/N)')
            if (respuesta.lower == 'n'):
                aux = False
                break

def registrar_url(cliente):
    enlace = input('\n\nIngrese el URL que desea acortar: ')
    url = cliente.service.registrarEnlace(enlace)
    print(f'Direccion acortada: {url.direccionAcortada} \n url original: {url.origen}\n'
        f'Fecha de creacion: {str(url.fechaCreacion)}\n'
        f'imagen de preview (codificada en B64): {url.B64preview[0:10]}...(acortado porque el string es demasiado largo)')
    try:
        print(f'Cantidad de visitas: {url.clientes.length}\n obj: {url.clientes}\n')
    except (Exception, ArithmeticError) as e:
        print ('el Url aun no tiene visitas.')

        
    #print(url)

opcion = 1
while(opcion != 0):
    print('\nCliente de Acortador SOAP\n 1) listar Urls de un usuario\n 2) Iniciar sesion \n 3) Registrar Url(necesita haber iniciado sesion) \n 0) Salir')
    opcion = int(input('\nIngrese la opcion deseada: '))

    if opcion == 1:
        getAllByUsuario(cliente)

    if opcion == 2:
        login(cliente)

    if opcion == 3:
        if usuarioID != '':
            registrar_url(cliente)

    if opcion == 0:
        break

