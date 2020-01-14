package Liverpool;

import java.awt.*;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Avengers
 * @version 1.0
 *
 */
public class LoginWindow {

	private JFrame frame;
	private static Modelo mimodelo;
	static LoginWindow window;
	private DirigentesWindow dirigentes;

	/**
	 * @param args
	 * Método principal que lanza la ventana de login.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new LoginWindow(mimodelo, args);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * @param mimodelo modelo donde van todos los String por si se desea cambiar de idioma la app.
	 * Constructor
	 * @param args 
	 */
	public LoginWindow(Modelo mimodelo, String[] args) {
		this.mimodelo = mimodelo;
		initialize(args);
	}

	
	/**
	 * Método que da los parametros a la ventana y la crea.
	 * @param args 
	 */
	private void initialize(String[] args) {
		frame = new JFrame();
		frame.setTitle("LOGIN");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("img/app.png"));
		frame.getContentPane().setBackground(new Color(227, 27, 35));
		frame.setBounds(100, 100, 568, 253);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
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
		
		Border thickBorder = new LineBorder(Color.YELLOW, 2);

		JLabel lblContraseña = new JLabel(mimodelo.getTextoLabelContraseña());
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContraseña.setForeground(new Color(255, 255, 255));
		lblContraseña.setBackground(Color.MAGENTA);
		lblContraseña.setBounds(44, 94, 105, 14);
		frame.getContentPane().add(lblContraseña);
		
		JLabel lblUsuario = new JLabel(mimodelo.getTextoLabelUsusario());
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setBackground(new Color(255, 255, 51));
		lblUsuario.setBounds(44, 40, 105, 14);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setIcon(new ImageIcon("img/app.png"));
		lblImagen.setBounds(326, 11, 202, 202);
		frame.getContentPane().add(lblImagen);

		JTextField txtUsuario = new JTextField();
		txtUsuario.setBounds(175, 38, 118, 17);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JPasswordField psword = new JPasswordField();
		psword.setBounds(175, 92, 118, 20);
		frame.getContentPane().add(psword);

		JButton btnEntrar = new JButton(mimodelo.getTextoBotonLoguearse());
		btnEntrar.setBackground(new Color(255, 255, 255));
		btnEntrar.setForeground(new Color(0, 0, 0));
		btnEntrar.setBorder(thickBorder);
		btnEntrar.setBounds(44, 145, 105, 38);
		frame.getContentPane().add(btnEntrar);
		
		JButton btnSalir = new JButton(mimodelo.getTextoBotonSalir());
		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.setBorder(thickBorder);
		btnSalir.setBounds(188, 145, 105, 38);
		frame.getContentPane().add(btnSalir);
		//El botón entrar al pulsar sobre el, comprueba si los datos introducidos son válidos.
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ConexionConBD bd = new ConexionConBD(mimodelo);
				//dividimos el label del password en caracteres para poder cogerlo
				char[] arrayC = psword.getPassword();
				String pass = new String(arrayC);
				try {
					//aqui comprobamos en la BD que el usuario y la contraseña son correctos.
					bd.setRs(bd.sentencia.executeQuery("Select NICKNAME, PASWORD from users where NICKNAME like '"
							+ txtUsuario.getText() + "' and pasword like '" + pass + "'"));
					if (bd.getRs().next()) {
						JOptionPane.showMessageDialog(null, "Correcto");

						mimodelo.setAlmacenNombreUsuario(txtUsuario.getText());
						mimodelo.setAlmacenContraseña(pass);
						try {
							//si el usuario es correcto abrimos la nueva ventana.
							dirigentes = new DirigentesWindow(txtUsuario.getText().toString(), pass,
									mimodelo, args);
							dirigentes.setVisible(true);
							window.frame.dispose();
						} catch (Exception e) {
							dirigentes.getDefaultCloseOperation();
						}
					
						bd.cerrarConexion();
					} else {
						JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaemergenteLoginError());
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaemergenteLoginError());
				}
			}
		});
		//Al pulsar el boton salir, se cerrara la aplicación.
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMenu menu = new VentanaMenu(mimodelo, args);
				menu.main(args);
				frame.dispose();
			}
		});
		
	}
}
