package Liverpool;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.*;

public class ClientFTP {
	
	private static Modelo mimodelo;

	public FTPClient Conexion(String User, String Pass, Modelo mimodelo) {
		
		this.mimodelo=mimodelo;
		
		// Creando nuestro objeto Client
		FTPClient client = new FTPClient();

		// Datos para conectar al servidor FTP
		String ftp = mimodelo.getTextoIpFtp(); // También puede ir la IP

		try {
			// Conactando al servidor
			client.connect(ftp);

			// Logueado un usuario (true = pudo conectarse, false = no pudo
			// conectarse)
			boolean login = client.login(User, Pass);
			if (login) {
				System.out.println(mimodelo.getTextoVentanaemergenteLoginExito()); //Cambiar esto por ventana emergente
				client.changeWorkingDirectory("/SIGE ODOO");
				client.setFileType(FTP.BINARY_FILE_TYPE);

				String filename = "miarchivo.txt";
				BufferedInputStream in = new BufferedInputStream(
						new FileInputStream("C:\\Users\\enano\\OneDrive\\Escritorio\\SIGE ODOO\\" + filename));
				System.out.println(mimodelo.getTextoVentanaEmergenteGestionSubidaExitosa()); //Cambiar esto por ventana emergente
				// Guardando el archivo en el servidor
				client.storeFile(filename, in);
				in.close();

			} else {
				System.out.println(mimodelo.getTextoVentanaemergenteLoginError()); //Cambiar esto por ventana emergente
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return client;
	}
}