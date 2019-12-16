package Liverpool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.List;
import java.awt.Rectangle;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DirigentesWindow extends JFrame {

	static JList list = null;
	static String direcInicial = "/";
	static String direcSelec = direcInicial;
	static String ficheroSelec = "";
	private JPanel contentPane;
	private static String User = "";
	private static String pass = "";
	static FTPClient client = null;
	private static Modelo mimodelo;
	static DefaultListModel lista;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public DirigentesWindow(String Usuario, String pass, Modelo mimodelo) throws SQLException {
		this.mimodelo = mimodelo;
		ClientFTP cliente = new ClientFTP(Usuario, pass, mimodelo);
		
		JButton btnSalir = new JButton(mimodelo.getTextoBotonSalir());
		btnSalir.setForeground(new Color(255, 0, 0));
		JButton btnSubirArchivo = new JButton(mimodelo.getTextoBotonsubida());
		btnSubirArchivo.setBackground(new Color(102, 205, 170));
		JButton btnDescargarArchivo = new JButton(mimodelo.getTextoBotonBajada());
		btnDescargarArchivo.setForeground(new Color(255, 255, 255));
		btnDescargarArchivo.setBackground(new Color(46, 139, 87));
		JButton btnCrearCarpeta = new JButton(mimodelo.getTextoBotonCrear());
		btnCrearCarpeta.setBackground(new Color(30, 144, 255));
		JButton btnBorrarCarpeta = new JButton(mimodelo.getTextoBotonBorrar());
		btnBorrarCarpeta.setBackground(new Color(100, 149, 237));
		JButton btnRenombrar = new JButton(mimodelo.getTextoBotonRenombrar());
		btnRenombrar.setBackground(new Color(230, 230, 250));

		ConexionConBD bd2 = new ConexionConBD(mimodelo);

		try {
			bd2.setRs(bd2.sentencia.executeQuery(
					"SELECT permissions.UP, permissions.DOWN, permissions.CREATEP, permissions.REMOVEP, permissions.RENAMEP FROM permissions LEFT JOIN users ON users.TYPE = permissions.TYPE where users.NICKNAME = '"
							+ Usuario + "' and users.PASWORD = '" + pass + "'"));
			if (bd2.getRs().next()) {
				System.out.println(bd2.getRs().getInt(1));
				System.out.println(bd2.getRs().getInt(4));
				if (bd2.getRs().getInt(1) == 0) {
					btnSubirArchivo.setEnabled(false);
				}
				if (bd2.getRs().getInt(2) == 0) {
					btnDescargarArchivo.setEnabled(false);
				}
				if (bd2.getRs().getInt(3) == 0) {
					btnCrearCarpeta.setEnabled(false);
				}
				if (bd2.getRs().getInt(4) == 0) {
					btnBorrarCarpeta.setEnabled(false);
				}
				if (bd2.getRs().getInt(5) == 0) {
					btnRenombrar.setEnabled(false);
				}
			}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel();
		lblUsuario.setBounds(516, 30, 139, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblServidorFtp = new JLabel("Servidor FTP:");
		lblServidorFtp.setBounds(27, 11, 220, 14);
		contentPane.add(lblServidorFtp);
		lblUsuario.setText("Usuario: " + Usuario);
		lblServidorFtp.setText("Servidor FTP: " + mimodelo.getTextoIpFtp());
		
		User = Usuario;
		this.pass = pass;

		list = new JList();
		list.setBounds(41, 31, 310, 332);

		JScrollPane barraDesplazamiento = new JScrollPane(list);
		barraDesplazamiento.setPreferredSize(new Dimension(335, 300));
		barraDesplazamiento.setBounds(new Rectangle(27, 81, 335, 300));
		barraDesplazamiento.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		contentPane.add(barraDesplazamiento);
		try {
			LlenarLista(cliente.getFiles(), "/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginWindow login = new LoginWindow(mimodelo);
				String[] args = null;
				setVisible(false);
				LoginWindow.main(args);
			}
		});
		btnSalir.setBounds(464, 337, 129, 41);
		contentPane.add(btnSalir);

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					ficheroSelec = "";

					String fic = list.getSelectedValue().toString();
					if (list.getSelectedIndex() == 0) {

						if (!fic.equals(direcInicial)) {
							try {
								cliente.client.changeToParentDirectory();
								direcSelec = cliente.client.printWorkingDirectory();
								FTPFile[] ff2 = null;
								ff2 = cliente.client.listFiles();

								LlenarLista(ff2, direcSelec);
							} catch (Exception e2) {
								// TODO: handle exception
								e2.printStackTrace();
							}
						}
					} else {
						if (fic.substring(0, 1).equals("/")) {
							fic = fic.substring(1);
							System.out.println(fic);
							String direcSelec2 = "";
							if (direcSelec.equals("/"))
								direcSelec2 = direcSelec + fic;
							else
								direcSelec2 = direcSelec + "/" + fic;
							FTPFile[] ff2 = null;
							try {
								cliente.client.changeWorkingDirectory(direcSelec2);
								ff2 = cliente.client.listFiles();
								direcSelec = direcSelec2;

								LlenarLista(ff2, direcSelec);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} else {
							ficheroSelec = direcSelec;
							if (direcSelec.equals("/"))
								ficheroSelec += fic;
							else
								ficheroSelec += "/" + fic;

						}
					}
				}
			}
		});

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
					cliente.client.changeWorkingDirectory(direcSelec);
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				chooser.setDialogTitle(mimodelo.getTextoGestionAyudaSubida2());
				int returnVal = chooser.showDialog(chooser, mimodelo.getTextoGestionAyudaSubida3());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
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
						FTPFile[] ff2 = null;
						ff2 = cliente.client.listFiles();

						lista.removeAllElements();

						LlenarLista(ff2, direcSelec);
						JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaEmergenteGestionSubidaExitosa());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (strDirectorio.equals("")) {
					JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaEmergenteGestionSubidaFallo());
				}

			}
		});
		btnSubirArchivo.setBounds(386, 81, 129, 41);
		contentPane.add(btnSubirArchivo);

		btnDescargarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					cliente.client.changeWorkingDirectory(direcSelec);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String file = list.getSelectedValue().toString();
				BufferedOutputStream descarga = null;
				System.out.println(file);
				try {
					descarga = new BufferedOutputStream(new FileOutputStream(file));
				} catch (FileNotFoundException e1) {

				}
				try {
					if (cliente.client.retrieveFile(file, descarga)) {
						JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaEmergenteGestionBajadaExitosa());
					} else {
						JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaEmergenteGestionBajadaFallo());
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnDescargarArchivo.setBounds(525, 81, 130, 41);
		contentPane.add(btnDescargarArchivo);

		btnCrearCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreCarpeta = JOptionPane.showInputDialog(null, mimodelo.getTextoGestionAyudaCrear(),
						mimodelo.getTextoGestionAyudaCrear2());


				if (!(nombreCarpeta == null)) {
					String direcCarpeta = direcSelec;
					if (!direcSelec.equals("/"))
						direcCarpeta = direcCarpeta + "/";
					direcCarpeta += nombreCarpeta.trim();

					try {
						if (cliente.client.makeDirectory(direcCarpeta)) {
							cliente.client.changeWorkingDirectory(direcSelec);
							String m = nombreCarpeta.trim()
									+ mimodelo.getTextoVentanaEmergenteGestionCrearCarpetaExito();
							JOptionPane.showMessageDialog(null, m);

							FTPFile[] ff2 = null;
							ff2 = cliente.client.listFiles();

							LlenarLista(ff2, direcSelec);
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btnCrearCarpeta.setBounds(386, 148, 129, 41);
		contentPane.add(btnCrearCarpeta);

		btnBorrarCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione la carpeta que quiera borrar.");
				} else {
					int index = list.getSelectedIndex();
					String fichero = list.getSelectedValue().toString();
					if(!(fichero == null)){
						String directorio = direcSelec;
						if(!direcSelec.equals("/")) directorio = directorio + "/";
						directorio += fichero.trim();
						if (fichero.substring(0, 1).equals("/")) {
							int Seleccion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la carpeta seleccionada?");
							if (Seleccion == JOptionPane.OK_OPTION) {
								try {
									if (index > -1)
										if (cliente.client.removeDirectory(fichero.substring(1))) {
											cliente.client.changeWorkingDirectory(direcSelec);
											JOptionPane.showMessageDialog(null, "La carpeta se ha borrado correctamente.");
											lista.removeElementAt(index);
										} else {
											JOptionPane.showMessageDialog(null,
													"Para poder borrar la carpeta, primero tiene que estar vacia.");
										}

								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Seleccione una carpeta.");
						}
					}				
				}

			}
		});
		btnBorrarCarpeta.setBounds(526, 148, 129, 41);
		contentPane.add(btnBorrarCarpeta);

		btnRenombrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione lo que quiera renombrar.");
				} else {
					String fichero = list.getSelectedValue().toString();
					if (fichero.equals("")) {
						JOptionPane.showMessageDialog(null, "Seleccione algun archivo al cual cambiar el nombre.");
					} else {
						String nuevoNombre = JOptionPane.showInputDialog(null, "Elija el nuevo nombre para el archivo.",
								fichero);
						try {
							cliente.client.rename(fichero, nuevoNombre);
							FTPFile[] ff2 = null;
							ff2 = cliente.client.listFiles();

							lista.removeAllElements();

							LlenarLista(ff2, direcSelec);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			}
		});
		btnRenombrar.setBounds(464, 264, 129, 41);
		contentPane.add(btnRenombrar);

		JButton btnEliminarArchivo = new JButton(mimodelo.getTextoBotonBorrar2());
		btnEliminarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione el archivo que quiera eliminar.");
				} else {
					int index = list.getSelectedIndex();
					String fichero = list.getSelectedValue().toString();
					if (fichero.substring(0, 1).equals("/")) {
						JOptionPane.showMessageDialog(null, "Seleccione un archivo.");
					} else {
						int Seleccion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el archivo seleccionado?");
						if (Seleccion == JOptionPane.OK_OPTION) {
							try {
								if (index > -1)
									lista.removeElementAt(index);
								cliente.client.deleteFile(fichero);
								JOptionPane.showMessageDialog(null, "El archivo se ha borrado correctamente.");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}

			}
		});
		btnEliminarArchivo.setBounds(464, 200, 129, 41);
		contentPane.add(btnEliminarArchivo);
		
	
	}

	public static void LlenarLista(FTPFile[] files, String direc2) throws IOException {
		if (files == null)
			return;
		System.out.println("Llenando...");
		lista = new DefaultListModel<>();

		list.setForeground(Color.blue);
		list.setFont(new Font("Courier", Font.PLAIN, 12));

		list.removeAll();

		try {
			client.changeWorkingDirectory(direc2);
		} catch (Exception e) {

		}
		direcSelec = direc2;

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
		try {
			list.setModel(lista);
		} catch (NullPointerException e) {

		}

	}
}