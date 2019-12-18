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

public class LoginWindow {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField psword;
	private JButton btnSalir;
	private static Modelo mimodelo;
	static LoginWindow window;
	private JLabel lblNewLabel;

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
		this.mimodelo = mimodelo;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 51, 0));
		frame.getContentPane().setForeground(Color.RED);
		frame.setBounds(100, 100, 568, 253);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Border thickBorder = new LineBorder(Color.YELLOW, 2);

		JLabel lblUsuario = new JLabel(mimodelo.getTextoLabelUsusario());
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setBackground(new Color(255, 255, 51));
		lblUsuario.setBounds(44, 40, 105, 14);
		frame.getContentPane().add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(175, 38, 118, 17);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblContraseña = new JLabel(mimodelo.getTextoLabelContraseña());
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContraseña.setForeground(new Color(255, 255, 255));
		lblContraseña.setBackground(Color.MAGENTA);
		lblContraseña.setBounds(44, 94, 105, 14);
		frame.getContentPane().add(lblContraseña);

		psword = new JPasswordField();
		psword.setBounds(175, 92, 118, 20);
		frame.getContentPane().add(psword);

		JButton btnEntrar = new JButton(mimodelo.getTextoBotonLoguearse());
		btnEntrar.setBackground(new Color(255, 255, 255));
		btnEntrar.setForeground(new Color(0, 0, 0));
		btnEntrar.setBorder(thickBorder);
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

						DirigentesWindow dirigentes = new DirigentesWindow(txtUsuario.getText().toString(), pass,
								mimodelo);
						dirigentes.setVisible(true);
						window.frame.dispose();
						bd.cerrarConexion();
					} else {
						JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaemergenteLoginError());
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, mimodelo.getTextoVentanaemergenteLoginError());
				}
			}
		});
		btnEntrar.setBounds(44, 145, 105, 38);
		frame.getContentPane().add(btnEntrar);

		btnSalir = new JButton(mimodelo.getTextoBotonSalir());
		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.setBorder(thickBorder);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnSalir.setBounds(188, 145, 105, 38);
		frame.getContentPane().add(btnSalir);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\enano\\git\\Avengers\\Avengers\\Liverpool\\img\\escudo1.png"));
		lblNewLabel.setBounds(326, 11, 202, 187);
		frame.getContentPane().add(lblNewLabel);
	}
}
