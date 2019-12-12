package Liverpool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.Rectangle;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class DirigentesWindow extends JFrame {

	static JList list = null;
	private JPanel contentPane;
	private static String User = "";
	private static String pass = "";
	static FTPClient client = null;
	private static Modelo mimodelo;

	/**
	 * Create the frame.
	 */
	public DirigentesWindow(String string, String pass, Modelo mimodelo) {
		this.mimodelo = mimodelo;
		ClientFTP cliente = new ClientFTP(string, pass, mimodelo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		User = string;
		this.pass = pass;

		list = new JList();
		list.setBounds(41, 31, 310, 332);
		
		JScrollPane barraDesplazamiento = new JScrollPane(list);
		barraDesplazamiento.setPreferredSize(new Dimension(335, 620));
		barraDesplazamiento.setBounds(new Rectangle(5, 65, 335, 620));
		contentPane.add(list);
		try {
			LlenarLista(cliente.getFiles(), "/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton btnSalir = new JButton(mimodelo.getTextoBotonSalir());
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginWindow login = new LoginWindow(mimodelo);
				String[] args = null;
				setVisible(false);
				LoginWindow.main(args);
			}
		});
		btnSalir.setBounds(461, 317, 89, 23);
		contentPane.add(btnSalir);
		
		JButton btnSubirArchivo = new JButton("Subir Archivo");
		btnSubirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cliente.getClient().setFileType(FTP.BINARY_FILE_TYPE);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String strDirectorio = "";
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				try {
					cliente.getClient().changeWorkingDirectory(list.getSelectedValue().toString());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println(list.getSelectedValue().toString());
				chooser.setDialogTitle("SELECCIONE EL ARCHIVO A SUBIR.");
				int returnVal = chooser.showDialog(chooser, "Seleccionar");
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					strDirectorio = file.getAbsolutePath();
					BufferedInputStream in = null;
					try {
						in = new BufferedInputStream(new FileInputStream(strDirectorio));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						System.out.println(file.getName());
						cliente.getClient().storeFile(file.getName(), in);
						System.out.println("Se ha guardado el archivo correctamente");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} 
				if(strDirectorio.equals("")) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun archivo.");
				}
				
				
			}
		});
		btnSubirArchivo.setBounds(376, 45, 111, 23);
		contentPane.add(btnSubirArchivo);
		
		JButton btnDescargarArchivo = new JButton("Descargar Archivo");
		btnDescargarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cliente.getClient().changeWorkingDirectory(list.getSelectedValue().toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String file = list.getSelectedValue().toString();
				BufferedOutputStream descarga = null;
				System.out.println(file);
				try {
					descarga = new BufferedOutputStream(new FileOutputStream(file));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if(cliente.getClient().retrieveFile(file, descarga)) {
						System.out.println("Recuperado correctamente.");
					} else {
						System.out.println("No se ha podido descargar.");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDescargarArchivo.setBounds(509, 45, 134, 23);
		contentPane.add(btnDescargarArchivo);
		
		JButton btnCrearCarpeta = new JButton("Crear Carpeta");
		btnCrearCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearCarpeta.setBounds(376, 92, 111, 23);
		contentPane.add(btnCrearCarpeta);
		
		JButton btnBorrarCarpeta = new JButton("Eliminar Carpeta");
		btnBorrarCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorrarCarpeta.setBounds(509, 92, 134, 23);
		contentPane.add(btnBorrarCarpeta);
		
		JButton btnRenombrar = new JButton("Renombrar");
		btnRenombrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRenombrar.setBounds(461, 239, 89, 23);
		contentPane.add(btnRenombrar);
		
		JButton btnEliminarArchivo = new JButton("Eliminar Archivo");
		btnEliminarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarArchivo.setBounds(441, 155, 124, 23);
		contentPane.add(btnEliminarArchivo);
	}

	public static void LlenarLista(FTPFile[] files, String direc2) throws IOException {
		if (files == null)
			return;

		DefaultListModel lista = new DefaultListModel<>();
		lista = new DefaultListModel<>();

		list.setForeground(Color.blue);
		list.setFont(new Font("Courier", Font.PLAIN, 12));

		list.removeAll();

		lista.addElement(direc2);

		for (int i = 0; i < files.length; i++) {
			if (!(files[i].getName()).equals(".") && !(files[i].getName()).equals("..")) {
				// Se obtiene el nombre del fichero o directorio
				String f = files[i].getName();
				// Si es directorio se a�ade al nombre (DIR)
				if (files[i].isDirectory()) {
					f = "/" + f;
				}
				lista.addElement(f);

			}
		}
		list.setModel(lista);
	}
}