package Liverpool;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.apache.commons.net.ftp.*;
public class LoginWindow {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField psword;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
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
	public LoginWindow() {
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
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(44, 53, 46, 14);
		frame.getContentPane().add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(129, 50, 118, 17);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setBounds(44, 113, 71, 14);
		frame.getContentPane().add(lblContraseña);
		
		psword = new JPasswordField();
		psword.setBounds(129, 110, 118, 20);
		frame.getContentPane().add(psword);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
	
				
				if(txtUsuario.getText().equalsIgnoreCase("Mini")) {
					ClientFTP conexion = new ClientFTP();
					char[] arrayC = psword.getPassword();
					String pass = new String(arrayC);
					FTPClient cliente = conexion.Conexion(txtUsuario.getText().toString(), pass);
					JOptionPane.showMessageDialog(null,"Correcto");
					DirigentesWindow dirigentes = new DirigentesWindow(cliente);
					dirigentes.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,"Introduce mini");
				}
			}
		});
		btnEntrar.setBounds(129, 154, 89, 23);
		frame.getContentPane().add(btnEntrar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnSalir.setBounds(235, 154, 89, 23);
		frame.getContentPane().add(btnSalir);
	}
}
