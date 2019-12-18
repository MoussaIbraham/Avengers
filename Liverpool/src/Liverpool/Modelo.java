package Liverpool;

public class Modelo {

	
	
			//Ventana Login
	private String  
			TextoVentanaLogin="Login",	
			TextoLabelUsusario="User: ",
			TextoLabelContrase�a="Password: ",
			TextoBotonLoguearse="Enter",
			TextoBotonCancelar="Cancel",
			TextoVentanaemergenteLoginExito="Connected",
			TextoVentanaemergenteLoginError="Error, please Introduce a correct user or password.",
			AlmacenNombreUsuario="",
			AlmacenContrase�a="",
			
			//Errores de la base de datos
			TextoErrorBd1="Error al encontrar la Base de Datos",
			TextoErrorBd2="Error al encontrar el driver de la Base de Datos",
			
			//Ventana men�
			TextoVentanaMenu="Menu",
			TextoBotonGestion="Open Management",
			TextoBotonCorreo="Open Mail",
			TextoBotonSalir="Exit",
			
			
			//Ventana principal
			TextoVentanaPrincipal="Management",
			TextoLabelbotonesGestion="File management",
			
			//Crear carpeta gestion
			TextoBotonCrear="Create Folder",
			TextoGestionAyudaCrear="Set the name of the folder you want to create",
			TextoGestionAyudaCrear2="Folder",
			TextoVentanaEmergenteGestionCrearCarpetaExito=" was correctly created",
			
			
			TextoBotonRenombrar="Rename",
			TextoBotonBorrar="Delete Folder",
			TextoBotonBorrar2="Delete File",
			TextoBotonCerrar="Close",
			
			//Subidas y bajadas
			TextoLabelBotonesSubidaBajada="Uploads and Downloads",
			TextoBotonsubida="Upload",
			TextoBotonBajada="Download",
			//Gestion subida
			TextoVentanaEmergenteGestionSubidaExitosa="File saved successfuly",
			TextoVentanaEmergenteGestionSubidaFallo="Error, you haven't select anything",
			TextoGestionAyudaSubida="Select where you want to upload the file.",
			TextoGestionAyudaSubida2="Select the file you want to upload.",
			TextoGestionAyudaSubida3="Select.",
			//Gestion bajada
			TextoGestionAyudaBajada="Select the file you want to download.",
			TextoVentanaEmergenteGestionBajadaExitosa="File saved successfuly",
			TextoVentanaEmergenteGestionBajadaFallo="Error, the file couldn't be downloaded",
			
			
			
			//Ventana Crear Carpeta
			TextoVentanaCrearCarpeta="Create folder",
			TextoLabelNombrecarpeta="Folder name: ",
			TextoBotonCrearCarpeta="Create",
			//Boton cancel reutilizable aqui
			
			
			//Ventana Modificar nombre
			TextoVentanaRenombrar="Rename File",
			TextoLabelNuevoNombre="New name: ",
			TextoBotonRenombre="Rename",
			//Boton cancel reutilizable aqui
	
	
			//Otros textos
			TextoIpFtp="192.168.137.1",

			TextoServerSMTP = "smtp.gmail.com",

			//Ventana Logincorreo
			LoginCorreoTextoLabeluser="Email: ",
			LoginCorreoTextoLabelpassword="Password: ",
			LoginCorreoTextoBotonLogin="Login",
			LoginCorreoTextoBotonCancel="Cancel",
			LoginCorreoCajaCorreo="",
			LoginCorreoCajacontrase�a="",
			
			//Ventana LeerCorreo
			VerCorreoTextoLabelde="From:",
			VerCorreoTextoLabelAsunto="Subject:",
			
			
			//Ventana correo pop
			TextoPOPBotonAbrirCorreo="Open Mail",
			TextoPOPBotonEscribirCorreo="Send Mail",
			TextoPOPBotonCerrarCorreo="Close Mail",
			TextoPOPBotonActualizarCorreo="Refresh",
			TextoPOPBotonBorrarCorreo = "Delete Mail",
			TextoPOPtextoconfimarborrado="Are you sure you want to delete the email?",
	
			//Ventana correo SMTP
			TextoSMTPLabelDestinatario="To",
			TextoSMTPLabelAyudaDestinatario="<html>(Add '/' to separate mails if you<br>want to send it to more than one person)</html>",
			TextoSMTPLabelAsunto="Subject",
			TextoSMTPLabelCuerpo="Text",
			TextoSMTPButtonA�adirArchivo="Attach files",
			TextoSMTPBotonEnviar="Send Mail",
			TextoSMTPBotonCancelar="Cancel";
			
	
	
	
	
	public String getTextoPOPtextoconfimarborrado() {
		return TextoPOPtextoconfimarborrado;
	}

	public void setTextoPOPtextoconfimarborrado(String textoPOPtextoconfimarborrado) {
		TextoPOPtextoconfimarborrado = textoPOPtextoconfimarborrado;
	}

	public String getTextoPOPBotonActualizarCorreo() {
		return TextoPOPBotonActualizarCorreo;
	}

	public void setTextoPOPBotonActualizarCorreo(String textoPOPBotonActualizarCorreo) {
		TextoPOPBotonActualizarCorreo = textoPOPBotonActualizarCorreo;
	}

	public String getVerCorreoTextoLabelde() {
		return VerCorreoTextoLabelde;
	}

	public void setVerCorreoTextoLabelde(String verCorreoTextoLabelde) {
		VerCorreoTextoLabelde = verCorreoTextoLabelde;
	}

	public String getVerCorreoTextoLabelAsunto() {
		return VerCorreoTextoLabelAsunto;
	}

	public void setVerCorreoTextoLabelAsunto(String verCorreoTextoLabelAsunto) {
		VerCorreoTextoLabelAsunto = verCorreoTextoLabelAsunto;
	}

	public String getLoginCorreoTextoLabeluser() {
		return LoginCorreoTextoLabeluser;
	}

	public void setLoginCorreoTextoLabeluser(String loginCorreoTextoLabeluser) {
		LoginCorreoTextoLabeluser = loginCorreoTextoLabeluser;
	}

	public String getLoginCorreoTextoLabelpassword() {
		return LoginCorreoTextoLabelpassword;
	}

	public void setLoginCorreoTextoLabelpassword(String loginCorreoTextoLabelpassword) {
		LoginCorreoTextoLabelpassword = loginCorreoTextoLabelpassword;
	}

	public String getLoginCorreoTextoBotonLogin() {
		return LoginCorreoTextoBotonLogin;
	}

	public void setLoginCorreoTextoBotonLogin(String loginCorreoTextoBotonLogin) {
		LoginCorreoTextoBotonLogin = loginCorreoTextoBotonLogin;
	}

	public String getLoginCorreoTextoBotonCancel() {
		return LoginCorreoTextoBotonCancel;
	}

	public void setLoginCorreoTextoBotonCancel(String loginCorreoTextoBotonCancel) {
		LoginCorreoTextoBotonCancel = loginCorreoTextoBotonCancel;
	}

	public String getLoginCorreoCajaCorreo() {
		return LoginCorreoCajaCorreo;
	}

	public void setLoginCorreoCajaCorreo(String loginCorreoCajaCorreo) {
		LoginCorreoCajaCorreo = loginCorreoCajaCorreo;
	}

	public String getLoginCorreoCajacontrase�a() {
		return LoginCorreoCajacontrase�a;
	}

	public void setLoginCorreoCajacontrase�a(String loginCorreoCajacontrase�a) {
		LoginCorreoCajacontrase�a = loginCorreoCajacontrase�a;
	}

	public String getTextoSMTPLabelAyudaDestinatario() {
		return TextoSMTPLabelAyudaDestinatario;
	}

	public void setTextoSMTPLabelAyudaDestinatario(String textoSMTPLabelAyudaDestinatario) {
		TextoSMTPLabelAyudaDestinatario = textoSMTPLabelAyudaDestinatario;
	}

	public String getTextoSMTPBotonCancelar() {
		return TextoSMTPBotonCancelar;
	}

	public void setTextoSMTPBotonCancelar(String textoSMTPBotonCancelar) {
		TextoSMTPBotonCancelar = textoSMTPBotonCancelar;
	}

	public String getTextoPOPBotonAbrirCorreo() {
		return TextoPOPBotonAbrirCorreo;
	}

	public void setTextoPOPBotonAbrirCorreo(String textoPOPBotonAbrirCorreo) {
		TextoPOPBotonAbrirCorreo = textoPOPBotonAbrirCorreo;
	}

	public String getTextoPOPBotonEscribirCorreo() {
		return TextoPOPBotonEscribirCorreo;
	}

	public void setTextoPOPBotonEscribirCorreo(String textoPOPBotonEscribirCorreo) {
		TextoPOPBotonEscribirCorreo = textoPOPBotonEscribirCorreo;
	}

	public String getTextoPOPBotonCerrarCorreo() {
		return TextoPOPBotonCerrarCorreo;
	}

	public void setTextoPOPBotonCerrarCorreo(String textoPOPBotonCerrarCorreo) {
		TextoPOPBotonCerrarCorreo = textoPOPBotonCerrarCorreo;
	}

	public String getTextoSMTPLabelDestinatario() {
		return TextoSMTPLabelDestinatario;
	}

	public void setTextoSMTPLabelDestinatario(String textoSMTPLabelDestinatario) {
		TextoSMTPLabelDestinatario = textoSMTPLabelDestinatario;
	}

	public String getTextoSMTPLabelAsunto() {
		return TextoSMTPLabelAsunto;
	}

	public void setTextoSMTPLabelAsunto(String textoSMTPLabelAsunto) {
		TextoSMTPLabelAsunto = textoSMTPLabelAsunto;
	}

	public String getTextoSMTPLabelCuerpo() {
		return TextoSMTPLabelCuerpo;
	}

	public void setTextoSMTPLabelCuerpo(String textoSMTPLabelCuerpo) {
		TextoSMTPLabelCuerpo = textoSMTPLabelCuerpo;
	}

	public String getTextoSMTPLabelA�adirArchivo() {
		return TextoSMTPButtonA�adirArchivo;
	}

	public void setTextoSMTPLabelA�adirArchivo(String textoSMTPLabelA�adirArchivo) {
		TextoSMTPButtonA�adirArchivo = textoSMTPLabelA�adirArchivo;
	}

	public String getTextoSMTPBotonEnviar() {
		return TextoSMTPBotonEnviar;
	}

	public void setTextoSMTPBotonEnviar(String textoSMTPBotonEnviar) {
		TextoSMTPBotonEnviar = textoSMTPBotonEnviar;
	}

	public String getAlmacenNombreUsuario() {
		return AlmacenNombreUsuario;
	}

	public void setAlmacenNombreUsuario(String almacenNombreUsuario) {
		AlmacenNombreUsuario = almacenNombreUsuario;
	}

	public String getAlmacenContrase�a() {
		return AlmacenContrase�a;
	}

	public void setAlmacenContrase�a(String almacenContrase�a) {
		AlmacenContrase�a = almacenContrase�a;
	}

	public String getTextoErrorBd1() {
		return TextoErrorBd1;
	}

	public void setTextoErrorBd1(String textoErrorBd1) {
		TextoErrorBd1 = textoErrorBd1;
	}

	public String getTextoErrorBd2() {
		return TextoErrorBd2;
	}

	public void setTextoErrorBd2(String textoErrorBd2) {
		TextoErrorBd2 = textoErrorBd2;
	}

	public String getTextoBotonBorrar2() {
		return TextoBotonBorrar2;
	}

	public void setTextoBotonBorrar2(String textoBotonBorrar2) {
		TextoBotonBorrar2 = textoBotonBorrar2;
	}

	public String getTextoGestionAyudaCrear() {
		return TextoGestionAyudaCrear;
	}

	public void setTextoGestionAyudaCrear(String textoGestionAyudaCrear) {
		TextoGestionAyudaCrear = textoGestionAyudaCrear;
	}

	public String getTextoGestionAyudaCrear2() {
		return TextoGestionAyudaCrear2;
	}

	public void setTextoGestionAyudaCrear2(String textoGestionAyudaCrear2) {
		TextoGestionAyudaCrear2 = textoGestionAyudaCrear2;
	}

	public String getTextoVentanaEmergenteGestionCrearCarpetaExito() {
		return TextoVentanaEmergenteGestionCrearCarpetaExito;
	}

	public void setTextoVentanaEmergenteGestionCrearCarpetaExito(String textoVentanaEmergenteGestionCrearCarpetaExito) {
		TextoVentanaEmergenteGestionCrearCarpetaExito = textoVentanaEmergenteGestionCrearCarpetaExito;
	}

	public String getTextoGestionAyudaSubida() {
		return TextoGestionAyudaSubida;
	}

	public void setTextoGestionAyudaSubida(String textoGestionAyudaSubida) {
		TextoGestionAyudaSubida = textoGestionAyudaSubida;
	}

	public String getTextoGestionAyudaSubida2() {
		return TextoGestionAyudaSubida2;
	}

	public void setTextoGestionAyudaSubida2(String textoGestionAyudaSubida2) {
		TextoGestionAyudaSubida2 = textoGestionAyudaSubida2;
	}

	public String getTextoGestionAyudaSubida3() {
		return TextoGestionAyudaSubida3;
	}

	public void setTextoGestionAyudaSubida3(String textoGestionAyudaSubida3) {
		TextoGestionAyudaSubida3 = textoGestionAyudaSubida3;
	}

	public String getTextoGestionAyudaBajada() {
		return TextoGestionAyudaBajada;
	}

	public void setTextoGestionAyudaBajada(String textoGestionAyudaBajada) {
		TextoGestionAyudaBajada = textoGestionAyudaBajada;
	}

	public String getTextoVentanaEmergenteGestionBajadaExitosa() {
		return TextoVentanaEmergenteGestionBajadaExitosa;
	}

	public void setTextoVentanaEmergenteGestionBajadaExitosa(String textoVentanaEmergenteGestionBajadaExitosa) {
		TextoVentanaEmergenteGestionBajadaExitosa = textoVentanaEmergenteGestionBajadaExitosa;
	}

	public String getTextoVentanaEmergenteGestionBajadaFallo() {
		return TextoVentanaEmergenteGestionBajadaFallo;
	}

	public void setTextoVentanaEmergenteGestionBajadaFallo(String textoVentanaEmergenteGestionBajadaFallo) {
		TextoVentanaEmergenteGestionBajadaFallo = textoVentanaEmergenteGestionBajadaFallo;
	}

	public String getTextoVentanaEmergenteGestionSubidaFallo() {
		return TextoVentanaEmergenteGestionSubidaFallo;
	}

	public void setTextoVentanaEmergenteGestionSubidaFallo(String textoVentanaEmergenteGestionSubidaFallo) {
		TextoVentanaEmergenteGestionSubidaFallo = textoVentanaEmergenteGestionSubidaFallo;
	}

	public String getTextoVentanaLogin() {
		return TextoVentanaLogin;
	}

	public void setTextoVentanaLogin(String textoVentanaLogin) {
		TextoVentanaLogin = textoVentanaLogin;
	}

	public String getTextoLabelUsusario() {
		return TextoLabelUsusario;
	}

	public void setTextoLabelUsusario(String textoLabelUsusario) {
		TextoLabelUsusario = textoLabelUsusario;
	}

	public String getTextoLabelContrase�a() {
		return TextoLabelContrase�a;
	}

	public void setTextoLabelContrase�a(String textoLabelContrase�a) {
		TextoLabelContrase�a = textoLabelContrase�a;
	}

	public String getTextoBotonLoguearse() {
		return TextoBotonLoguearse;
	}

	public void setTextoBotonLoguearse(String textoBotonLoguearse) {
		TextoBotonLoguearse = textoBotonLoguearse;
	}

	public String getTextoBotonCancelar() {
		return TextoBotonCancelar;
	}

	public void setTextoBotonCancelar(String textoBotonCancelar) {
		TextoBotonCancelar = textoBotonCancelar;
	}

	public String getTextoVentanaemergenteLoginExito() {
		return TextoVentanaemergenteLoginExito;
	}

	public void setTextoVentanaemergenteLoginExito(String textoVentanaemergenteLoginExito) {
		TextoVentanaemergenteLoginExito = textoVentanaemergenteLoginExito;
	}

	public String getTextoVentanaemergenteLoginError() {
		return TextoVentanaemergenteLoginError;
	}

	public void setTextoVentanaemergenteLoginError(String textoVentanaemergenteLoginError) {
		TextoVentanaemergenteLoginError = textoVentanaemergenteLoginError;
	}

	public String getTextoVentanaMenu() {
		return TextoVentanaMenu;
	}

	public void setTextoVentanaMenu(String textoVentanaMenu) {
		TextoVentanaMenu = textoVentanaMenu;
	}

	public String getTextoBotonGestion() {
		return TextoBotonGestion;
	}

	public void setTextoBotonGestion(String textoBotonGestion) {
		TextoBotonGestion = textoBotonGestion;
	}

	public String getTextoBotonCorreo() {
		return TextoBotonCorreo;
	}

	public void setTextoBotonCorreo(String textoBotonCorreo) {
		TextoBotonCorreo = textoBotonCorreo;
	}

	public String getTextoBotonSalir() {
		return TextoBotonSalir;
	}

	public void setTextoBotonSalir(String textoBotonSalir) {
		TextoBotonSalir = textoBotonSalir;
	}

	public String getTextoVentanaPrincipal() {
		return TextoVentanaPrincipal;
	}

	public void setTextoVentanaPrincipal(String textoVentanaPrincipal) {
		TextoVentanaPrincipal = textoVentanaPrincipal;
	}

	public String getTextoLabelbotonesGestion() {
		return TextoLabelbotonesGestion;
	}

	public void setTextoLabelbotonesGestion(String textoLabelbotonesGestion) {
		TextoLabelbotonesGestion = textoLabelbotonesGestion;
	}

	public String getTextoBotonCrear() {
		return TextoBotonCrear;
	}

	public void setTextoBotonCrear(String textoBotonCrear) {
		TextoBotonCrear = textoBotonCrear;
	}

	public String getTextoBotonRenombrar() {
		return TextoBotonRenombrar;
	}

	public void setTextoBotonRenombrar(String textoBotonRenombrar) {
		TextoBotonRenombrar = textoBotonRenombrar;
	}

	public String getTextoBotonBorrar() {
		return TextoBotonBorrar;
	}

	public void setTextoBotonBorrar(String textoBotonBorrar) {
		TextoBotonBorrar = textoBotonBorrar;
	}

	public String getTextoBotonCerrar() {
		return TextoBotonCerrar;
	}

	public void setTextoBotonCerrar(String textoBotonCerrar) {
		TextoBotonCerrar = textoBotonCerrar;
	}

	public String getTextoLabelBotonesSubidaBajada() {
		return TextoLabelBotonesSubidaBajada;
	}

	public void setTextoLabelBotonesSubidaBajada(String textoLabelBotonesSubidaBajada) {
		TextoLabelBotonesSubidaBajada = textoLabelBotonesSubidaBajada;
	}

	public String getTextoBotonsubida() {
		return TextoBotonsubida;
	}

	public void setTextoBotonsubida(String textoBotonsubida) {
		TextoBotonsubida = textoBotonsubida;
	}

	public String getTextoBotonBajada() {
		return TextoBotonBajada;
	}

	public void setTextoBotonBajada(String textoBotonBajada) {
		TextoBotonBajada = textoBotonBajada;
	}

	public String getTextoVentanaEmergenteGestionSubidaExitosa() {
		return TextoVentanaEmergenteGestionSubidaExitosa;
	}

	public void setTextoVentanaEmergenteGestionSubidaExitosa(String textoVentanaEmergenteGestionSubidaExitosa) {
		TextoVentanaEmergenteGestionSubidaExitosa = textoVentanaEmergenteGestionSubidaExitosa;
	}

	public String getTextoVentanaCrearCarpeta() {
		return TextoVentanaCrearCarpeta;
	}

	public void setTextoVentanaCrearCarpeta(String textoVentanaCrearCarpeta) {
		TextoVentanaCrearCarpeta = textoVentanaCrearCarpeta;
	}

	public String getTextoLabelNombrecarpeta() {
		return TextoLabelNombrecarpeta;
	}

	public void setTextoLabelNombrecarpeta(String textoLabelNombrecarpeta) {
		TextoLabelNombrecarpeta = textoLabelNombrecarpeta;
	}

	public String getTextoBotonCrearCarpeta() {
		return TextoBotonCrearCarpeta;
	}

	public void setTextoBotonCrearCarpeta(String textoBotonCrearCarpeta) {
		TextoBotonCrearCarpeta = textoBotonCrearCarpeta;
	}

	public String getTextoVentanaRenombrar() {
		return TextoVentanaRenombrar;
	}

	public void setTextoVentanaRenombrar(String textoVentanaRenombrar) {
		TextoVentanaRenombrar = textoVentanaRenombrar;
	}

	public String getTextoLabelNuevoNombre() {
		return TextoLabelNuevoNombre;
	}

	public void setTextoLabelNuevoNombre(String textoLabelNuevoNombre) {
		TextoLabelNuevoNombre = textoLabelNuevoNombre;
	}

	public String getTextoBotonRenombre() {
		return TextoBotonRenombre;
	}

	public void setTextoBotonRenombre(String textoBotonRenombre) {
		TextoBotonRenombre = textoBotonRenombre;
	}

	public String getTextoIpFtp() {
		return TextoIpFtp;
	}

	public void setTextoIpFtp(String textoIpFtp) {
		TextoIpFtp = textoIpFtp;
	}

	public String getTextoServerSMTP() {
		return TextoServerSMTP;
	}

	public void setTextoServerSMTP(String textoServerSMTP) {
		TextoServerSMTP = textoServerSMTP;
	}

	public String getTextoSMTPButtonA�adirArchivo() {
		return TextoSMTPButtonA�adirArchivo;
	}

	public void setTextoSMTPButtonA�adirArchivo(String textoSMTPButtonA�adirArchivo) {
		TextoSMTPButtonA�adirArchivo = textoSMTPButtonA�adirArchivo;
	}

	public String getTextoPOPBotonBorrarCorreo() {
		return TextoPOPBotonBorrarCorreo;
	}

	public void setTextoPOPBotonBorrarCorreo(String textoPOPBotonBorrarCorreo) {
		TextoPOPBotonBorrarCorreo = textoPOPBotonBorrarCorreo;
	}			
	
	
	
}