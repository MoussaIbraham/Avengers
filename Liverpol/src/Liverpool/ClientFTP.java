package Liverpool;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.*;

public class ClientFTP {

	private boolean login;
	private static Modelo mimodelo;
	FTPFile[] files;
	FTPClient client;
	/**
	 * @param User String usuario introducido en el login, se comprueba si existe en el servidor de filezilla
	 * @param Pass String contraseña introducida en el login, se comprueba si existe en el servidor de filezilla
	 * @param mimodelo modelo donde se recogen todos los String por si se desea cambiar de idioma.
	 * @return devuelve el propio cliente del FTP
	 * Constructor donde se comprueba si el usuario y contraseña son correctos.
	 */
	public ClientFTP(String User, String Pass, Modelo mimodelo) {
		this.mimodelo = mimodelo;

		// Creando nuestro objeto Client
		client = new FTPClient();

		// Datos para conectar al servidor FTP
		String ftp = mimodelo.getTextoIpFtp(); // También puede ir la IP

		try {
			// Conactando al servidor
			try {
				client.connect(ftp);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se pudo encontrar una conexión con el servidor FTP.");
			}
	
			// Logueado un usuario (true = pudo conectarse, false = no pudo
			// conectarse)
			try {
				login = client.login(User, Pass);
			} catch (Exception e) {
				System.exit(0);
			}
			
			if (login) {
				System.out.println(mimodelo.getTextoVentanaemergenteLoginExito()); // Cambiar esto por ventana emergente
				client.changeWorkingDirectory("/SIGE ODOO");
				// Obtenemos ficheros y directorios.
				files = client.listFiles();
				client.setFileType(FTP.BINARY_FILE_TYPE);

			} else {
				System.out.println(mimodelo.getTextoVentanaemergenteLoginError()); // Cambiar esto por ventana emergente
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public FTPFile[] getFiles() {
		return files;
	}

	public void setFiles(FTPFile[] files) {
		this.files = files;
	}

	public FTPClient getClient() {
		return client;
	}

	public void setClient(FTPClient client) {
		this.client = client;
	}

}