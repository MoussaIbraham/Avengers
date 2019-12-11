package Liverpool;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.*;

public class ClientFTP {

	public FTPClient Conexion(String User, String Pass) {
		// Creando nuestro objeto Client
		FTPClient client = new FTPClient();

		// Datos para conectar al servidor FTP
		String ftp = "192.168.1.135"; // También puede ir la IP

		try {
			// Conactando al servidor
			client.connect(ftp);

			// Logueado un usuario (true = pudo conectarse, false = no pudo
			// conectarse)
			boolean login = client.login(User, Pass);
			if (login) {
				System.out.println("se pudo conectar.");
				client.changeWorkingDirectory("/SIGE ODOO");
				client.setFileType(FTP.BINARY_FILE_TYPE);

				String filename = "miarchivo.txt";
				BufferedInputStream in = new BufferedInputStream(
						new FileInputStream("C:\\Users\\enano\\OneDrive\\Escritorio\\SIGE ODOO\\" + filename));
				System.out.println("Se ha guardado");
				// Guardando el archivo en el servidor
				client.storeFile(filename, in);
				in.close();

			} else {
				System.out.println("nada");
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return client;
	}

}
