package Liverpool;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

/**
 * 
 * @author Grupo Avengers
 *
 */
public class VentanaLoginCorreo extends JFrame {

	private JPanel contentPane;
	private JTextField correo;
	private JTextField contraseña;
	static Modelo mimodelo;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mimodelo = new Modelo();
					VentanaLoginCorreo frame = new VentanaLoginCorreo(mimodelo, args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param args 
	 */
	public VentanaLoginCorreo(Modelo mimodelo, String[] args) {
		setTitle("LOGIN CORREO");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/app.png"));
		this.mimodelo=mimodelo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 256, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(227,27,35));
		
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
		
		JLabel lblMail = new JLabel(mimodelo.getLoginCorreoTextoLabeluser());
		lblMail.setForeground(new Color(255, 255, 255));
		lblMail.setBounds(10, 41, 58, 14);
		contentPane.add(lblMail);
		
		JLabel lblPassword = new JLabel(mimodelo.getLoginCorreoTextoLabelpassword());
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(10, 96, 58, 14);
		contentPane.add(lblPassword);
		
		correo = new JTextField();
		correo.setBounds(68, 38, 162, 20);
		contentPane.add(correo);
		correo.setColumns(10);
		
		contraseña = new JPasswordField();
		contraseña.setColumns(10);
		contraseña.setBounds(68, 93, 162, 20);
		contentPane.add(contraseña);
		
		JButton btnLogIn = new JButton(mimodelo.getLoginCorreoTextoBotonLogin());
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread hiloventa = new Thread(new POP3(mimodelo,correo.getText(),contraseña.getText()));
				hiloventa.start();
			}
		});
		
		btnLogIn.setBounds(141, 182, 89, 23);
		btnLogIn.setBorder(new LineBorder(Color.YELLOW));
		contentPane.add(btnLogIn);
		
		JButton btnCancel = new JButton(mimodelo.getLoginCorreoTextoBotonCancel());
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				VentanaMenu menu = new VentanaMenu(mimodelo, args);
				menu.main(args);
				dispose();
			}
		});
		btnCancel.setBounds(10, 182, 89, 23);
		btnCancel.setBorder(new LineBorder(Color.YELLOW));
		contentPane.add(btnCancel);
	}
}
