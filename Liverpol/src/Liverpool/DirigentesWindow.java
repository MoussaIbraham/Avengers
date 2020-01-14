package Liverpool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.List;
import java.awt.Rectangle;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AbstractDocument.Content;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


import javax.swing.*;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DirigentesWindow extends JFrame {

	static JList list = null;
	static String direcInicial = "/";
	static String direcSelec = direcInicial;
	static String ficheroSelec = "";
	private JPanel contentPane;
	private static String horaFinal;
	private static String fechaFinal;
	private static String User = "";
	private static String pass = "";
	static FTPClient client = null;
	private static Modelo mimodelo;
	static DefaultListModel lista;
	private ClientFTP cliente;

	/**
	 * Create the frame.
	 * @param args 
	 * 
	 * @throws SQLException
	 */
	public DirigentesWindow(String Usuario, String pass, Modelo mimodelo, String[] args) throws SQLException {
		super("VENTANA DE USUARIO");
		this.mimodelo = mimodelo;
		User = Usuario;
		this.pass = pass;
		this.setResizable(false);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e5) {
			e5.printStackTrace();
		} catch (InstantiationException e5) {
			e5.printStackTrace();
		} catch (IllegalAccessException e5) {
			e5.printStackTrace();
		} catch (UnsupportedLookAndFeelException e5) {
			e5.printStackTrace();
		}
		
		ConexionConBD bd = new ConexionConBD(mimodelo);
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date fecha = null, date = null;
		String fechaTexto = null, hora = null;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 485);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(227, 27, 35));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("img/app.png"));
		try {
			cliente = new ClientFTP(Usuario, pass, mimodelo);
		} catch (Exception e) {
			this.getDefaultCloseOperation();
		}

		Border thickBorder = new LineBorder(Color.YELLOW, 2);

		JButton btnEliminarArchivo = new JButton(mimodelo.getTextoBotonBorrar2());
		PropiedadesBotones(btnEliminarArchivo, new Color(0, 0, 0), new Color(255, 255, 255), thickBorder,
				new Rectangle(386, 284, 129, 41));

		JButton btnSalir = new JButton(mimodelo.getTextoBotonSalir());
		PropiedadesBotones(btnSalir, new Color(204, 51, 0), new Color(255, 255, 255), thickBorder,
				new Rectangle(462, 377, 129, 41));

		JButton btnSubirArchivo = new JButton(mimodelo.getTextoBotonsubida());
		PropiedadesBotones(btnSubirArchivo, new Color(0, 0, 0), new Color(255, 255, 255), thickBorder,
				new Rectangle(386, 81, 129, 41));

		JButton btnDescargarArchivo = new JButton(mimodelo.getTextoBotonBajada());
		PropiedadesBotones(btnDescargarArchivo, new Color(0, 0, 0), new Color(255, 255, 255), thickBorder,
				new Rectangle(525, 81, 130, 41));

		JButton btnCrearCarpeta = new JButton(mimodelo.getTextoBotonCrear());
		PropiedadesBotones(btnCrearCarpeta, new Color(0, 0, 0), new Color(255, 255, 255), thickBorder,
				new Rectangle(462, 154, 129, 41));

		JButton btnBorrarCarpeta = new JButton(mimodelo.getTextoBotonBorrar());
		PropiedadesBotones(btnBorrarCarpeta, new Color(0, 0, 0), new Color(255, 255, 255), thickBorder,
				new Rectangle(526, 284, 129, 41));

		JButton btnRenombrar = new JButton(mimodelo.getTextoBotonRenombrar());
		PropiedadesBotones(btnRenombrar, new Color(0, 0, 0), new Color(255, 255, 255), thickBorder,
				new Rectangle(462, 219, 129, 41));

		JLabel lblRuta = new JLabel();
		PropiedadesLabel(lblRuta, new Color(255, 255, 255), new Font("Tahoma", Font.BOLD, 14),
				new Rectangle(27, 43, 628, 27), direcSelec);

		JLabel lblUsuario = new JLabel();
		PropiedadesLabel(lblUsuario, new Color(255, 255, 255), new Font("Tahoma", Font.PLAIN, 13),
				new Rectangle(489, 11, 139, 21), "Usuario: " + Usuario);

		JLabel lblServidorFtp = new JLabel();
		PropiedadesLabel(lblServidorFtp, new Color(255, 255, 255), new Font("Tahoma", Font.PLAIN, 13),
				new Rectangle(27, 11, 220, 21), "Servidor FTP: " + mimodelo.getTextoIpFtp());

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
			e3.printStackTrace();
		}

		list = new JList();
		list.setBounds(41, 31, 310, 332);

		JScrollPane barraDesplazamiento = new JScrollPane(list);
		barraDesplazamiento.setPreferredSize(new Dimension(335, 300));
		barraDesplazamiento.setBounds(new Rectangle(27, 81, 335, 337));
		barraDesplazamiento.setViewportView(list);
		barraDesplazamiento.setBorder(thickBorder);
		list.setLayoutOrientation(JList.VERTICAL);
		contentPane.add(barraDesplazamiento);
		try {
			LlenarLista(cliente.getFiles(), "/", lblRuta);
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginWindow login = new LoginWindow(mimodelo, args);
				String[] args = null;
				setVisible(false);
				LoginWindow.main(args);
			}
		});

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
								Rellenado(cliente, lblRuta);
								FTPFile[] ff2 = null;
								ff2 = cliente.client.listFiles();
								LlenarLista(ff2, direcSelec, lblRuta);
							} catch (Exception e2) {
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
								LlenarLista(ff2, direcSelec, lblRuta);
							} catch (IOException e1) {
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
				int ficheros = 0;
				String strDirectorio = "";
				try {
					cliente.getClient().setFileType(FTP.BINARY_FILE_TYPE);
					strDirectorio = "";
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					chooser.setMultiSelectionEnabled(true);

					cliente.client.changeWorkingDirectory(direcSelec);

					chooser.setDialogTitle(mimodelo.getTextoGestionAyudaSubida2());
					int returnVal = chooser.showDialog(chooser, mimodelo.getTextoGestionAyudaSubida3());

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File[] filess = chooser.getSelectedFiles();
						for (int i = 0; i < filess.length; i++) {
							strDirectorio = filess[i].getAbsolutePath();
							BufferedInputStream in = null;
							try {
								in = new BufferedInputStream(new FileInputStream(strDirectorio));
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							System.out.println(filess[i].getName());
							cliente.getClient().storeFile(filess[i].getName(), in);
							Movimientos(formateador, dateFormat, fecha, date, fechaTexto, hora, bd, Usuario,
									"Ha subido un archivo");

							Rellenado(cliente, lblRuta);
						}
						JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaEmergenteGestionSubidaExitosa());
					}
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				if (strDirectorio.equals("")) {
					JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaEmergenteGestionSubidaFallo());
				}
			}
		});

		btnDescargarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					cliente.client.changeWorkingDirectory(direcSelec);
					String file = list.getSelectedValue().toString();
					BufferedOutputStream descarga = null;
					System.out.println(file);
					try {
						descarga = new BufferedOutputStream(new FileOutputStream(file));
					} catch (FileNotFoundException e1) {
					}
					try {
						if (cliente.client.retrieveFile(file, descarga)) {
							JOptionPane.showMessageDialog(null,
									mimodelo.getTextoVentanaEmergenteGestionBajadaExitosa());
							Movimientos(formateador, dateFormat, fecha, date, fechaTexto, hora, bd, Usuario,
									"Ha descargado un archivo");

						} else {
							JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaEmergenteGestionBajadaFallo());
						}
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				} catch (Exception e4) {
					JOptionPane.showMessageDialog(null, "Seleccione un archivo.");
				}
			}
		});

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
							Movimientos(formateador, dateFormat, fecha, date, fechaTexto, hora, bd, Usuario,
									"Ha creado una carpeta");

							Rellenado(cliente, lblRuta);
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		btnBorrarCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione las carpetas que quiera borrar.");
				} else {
					int[] index = list.getSelectedIndices();

					int Seleccion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la carpeta seleccionada?");
					if (Seleccion == JOptionPane.OK_OPTION) {
						for (int i = 0; i < index.length; i++) {
							String fichero = lista.getElementAt(index[i]).toString();
							if (!(fichero == null)) {
								String directorio = direcSelec;
								if (!direcSelec.equals("/"))
									directorio = directorio + "/";
								directorio += fichero.trim();
								try {
									if (fichero.substring(0, 1).equals("/")) {
										if (index[i] > -1)
											if (cliente.client.removeDirectory(fichero.substring(1))) {
												cliente.client.changeWorkingDirectory(direcSelec);
												Movimientos(formateador, dateFormat, fecha, date, fechaTexto, hora, bd,
														Usuario, "Ha borrado una carpeta");

											} else {
												JOptionPane.showMessageDialog(null,
														"Para poder borrar la carpeta, primero tiene que estar vacia.");
											}
									} else {
										JOptionPane.showMessageDialog(null, "No se puede eliminar este archivo.");
									}

								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione una carpeta.");
					}
					JOptionPane.showMessageDialog(null, "Las carpetas se han borrado correctamente.");
					Rellenado(cliente, lblRuta);
				}
			}
		});

		btnRenombrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] arreglo = null;
				String nuevoNombre = null;
				if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione lo que quiera renombrar.");
				} else {
					String fichero = list.getSelectedValue().toString();
					if (fichero.equals("")) {
						JOptionPane.showMessageDialog(null, "Seleccione algun archivo al cual cambiar el nombre.");
					} else {
						if (!fichero.substring(0, 1).equals("/")) {
							arreglo = fichero.split("\\.");
							nuevoNombre = JOptionPane.showInputDialog(null, "Elija el nuevo nombre para el archivo.",
									arreglo[0]);
						} else {
							nuevoNombre = JOptionPane.showInputDialog(null, "Elija el nuevo nombre para el archivo.",
									fichero);
						}
						try {
							if (fichero.substring(0, 1).equals("/")) {
								cliente.client.rename(fichero, nuevoNombre);
								Rellenado(cliente, lblRuta);
								Movimientos(formateador, dateFormat, fecha, date, fechaTexto, hora, bd, Usuario,
										"Ha renombrado una carpeta");

							} else {
								cliente.client.rename(fichero, nuevoNombre + "." + arreglo[1]);
								Rellenado(cliente, lblRuta);
								Movimientos(formateador, dateFormat, fecha, date, fechaTexto, hora, bd, Usuario,
										"Ha renombrado una archivo");

							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});

		btnEliminarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione los archivos que desee eliminar.");
				} else {
					int[] index = list.getSelectedIndices();
					int Seleccion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar los archivos seleccionados?");
					if (Seleccion == JOptionPane.OK_OPTION) {
						for (int i = 0; i < index.length; i++) {
							String fichero = lista.getElementAt(index[i]).toString();
							if (fichero.substring(0, 1).equals("/")) {
								JOptionPane.showMessageDialog(null, "No se puede eliminar esta carpeta.");
							} else {
								try {
									if (index[i] > -1)
										cliente.client.deleteFile(fichero);
									Movimientos(formateador, dateFormat, fecha, date, fechaTexto, hora, bd, Usuario,
											"Ha eliminado un archivo");
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
					JOptionPane.showMessageDialog(null, "Los archivos se han eliminado correctamente.");
					Rellenado(cliente, lblRuta);
				}
			}
		});

	}

	public void Rellenado(ClientFTP cliente, JLabel lblRuta) {
		FTPFile[] ff2 = null;
		try {
			ff2 = cliente.client.listFiles();
			LlenarLista(ff2, direcSelec, lblRuta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void LlenarLista(FTPFile[] files, String direc2, JLabel lblRuta) throws IOException {
		if (files == null)
			return;
		System.out.println("Llenando...");
		lista = new DefaultListModel<>();

		list.setForeground(Color.BLACK);
		list.setFont(new Font("Courier", Font.PLAIN, 12));

		list.removeAll();
		lblRuta.setText("RUTA: " + direcSelec);
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
				// Si es directorio se aï¿½ade al nombre (DIR)
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

	public void PropiedadesBotones(JButton nombre, Color color, Color color2, Border thickBorder, Rectangle bound) {
		nombre.setForeground(color);
		nombre.setBackground(color2);
		nombre.setBorder(thickBorder);
		nombre.setBounds(bound);
		contentPane.add(nombre);
	}

	public void Movimientos(SimpleDateFormat formateador, DateFormat dateFormat, Date fecha, Date date,
			String fechaTexto, String hora, ConexionConBD bd, String Usuario, String string) {
		try {
			horaFinal = ActualizarHora(formateador, dateFormat, fecha, date, fechaTexto, hora);
			fechaFinal = ActualizarFecha(formateador, dateFormat, fecha, date, fechaTexto, hora);
			bd.sentencia.executeUpdate("INSERT INTO `movements`(`NICK`, `ACTION`, `DATE`, `HOUR`) VALUES ('" + Usuario
					+ "',' " + string + " ','" + fechaFinal + "',' " + horaFinal + " ')");
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	}

	public String ActualizarHora(SimpleDateFormat formateador, DateFormat dateFormat, Date fecha, Date date,
			String fechaTexto, String hora) {
		date = new Date();
		hora = dateFormat.format(date);
		return hora;
	}

	public String ActualizarFecha(SimpleDateFormat formateador, DateFormat dateFormat, Date fecha, Date date,
			String fechaTexto, String hora) {
		fecha = new Date(Calendar.getInstance().getTimeInMillis());
		fechaTexto = formateador.format(fecha);
		return fechaTexto;

	}

	public void PropiedadesLabel(JLabel label, Color color, Font fuente, Rectangle bound, String texto) {
		label.setFont(fuente);
		label.setForeground(color);
		label.setBounds(bound);
		label.setText(texto);
		contentPane.add(label);
	}

}