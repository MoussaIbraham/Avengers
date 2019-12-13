package Liverpool;

public class Modelo {

	
	
			//Ventana Login
	private String  
			TextoVentanaLogin="Login",	
			TextoLabelUsusario="User: ",
			TextoLabelContraseña="Password: ",
			TextoBotonLoguearse="Enter",
			TextoBotonCancelar="Cancel",
			TextoVentanaemergenteLoginExito="Connected",
			TextoVentanaemergenteLoginError="Error, please Introduce a correct user or password.",
			AlmacenNombreUsuario="",
			AlmacenContraseña="",
			
			//Errores de la base de datos
			TextoErrorBd1="Error al encontrar la Base de Datos",
			TextoErrorBd2="Error al encontrar el driver de la Base de Datos",
			
			//Ventana menú
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
			TextoIpFtp="192.168.1.135";

	
	
	
	
	
	public String getAlmacenNombreUsuario() {
		return AlmacenNombreUsuario;
	}

	public void setAlmacenNombreUsuario(String almacenNombreUsuario) {
		AlmacenNombreUsuario = almacenNombreUsuario;
	}

	public String getAlmacenContraseña() {
		return AlmacenContraseña;
	}

	public void setAlmacenContraseña(String almacenContraseña) {
		AlmacenContraseña = almacenContraseña;
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

	public String getTextoLabelContraseña() {
		return TextoLabelContraseña;
	}

	public void setTextoLabelContraseña(String textoLabelContraseña) {
		TextoLabelContraseña = textoLabelContraseña;
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
}