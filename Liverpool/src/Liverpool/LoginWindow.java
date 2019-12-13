package Liverpool;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.spec.PSSParameterSpec;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import org.apache.commons.net.ftp.*;
public class LoginWindow {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField psword;
	private JButton btnSalir;
	private static Modelo mimodelo;
	static LoginWindow window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new LoginWindow(mimodelo);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow(Modelo mimodelo) {
		this.mimodelo=mimodelo;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel(mimodelo.getTextoLabelUsusario());
		lblUsuario.setBounds(44, 53, 46, 14);
		frame.getContentPane().add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(129, 50, 118, 17);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContraseña = new JLabel(mimodelo.getTextoLabelContraseña());
		lblContraseña.setBounds(44, 113, 71, 14);
		frame.getContentPane().add(lblContraseña);
		
		psword = new JPasswordField();
		psword.setBounds(129, 110, 118, 20);
		frame.getContentPane().add(psword);
		
		JButton btnEntrar = new JButton(mimodelo.getTextoBotonLoguearse());
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ConexionConBD bd = new ConexionConBD(mimodelo);

				char[] arrayC = psword.getPassword();
				String pass = new String(arrayC);
				System.out.println(pass);
				System.out.println(txtUsuario.getText());
				try {
					bd.setRs(bd.sentencia.executeQuery("Select NICKNAME, PASWORD from users where NICKNAME like '"
							+ txtUsuario.getText() + "' and pasword like '" + pass + "'"));
					if (bd.getRs().next()) {
						JOptionPane.showMessageDialog(null, "Correcto");
						
						mimodelo.setAlmacenNombreUsuario(txtUsuario.getText());
						mimodelo.setAlmacenContraseña(pass);
						
						DirigentesWindow dirigentes = new DirigentesWindow(txtUsuario.getText().toString(), pass, mimodelo);						
						dirigentes.setVisible(true);
						window.frame.dispose();
						bd.cerrarConexion();
					} else {
						JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaemergenteLoginError());
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaemergenteLoginError());
				}}
			}
		);
		btnEntrar.setBounds(129, 154, 89, 23);
		frame.getContentPane().add(btnEntrar);
		
		btnSalir = new JButton(mimodelo.getTextoBotonSalir());
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnSalir.setBounds(235, 154, 89, 23);
		frame.getContentPane().add(btnSalir);
	}
}
