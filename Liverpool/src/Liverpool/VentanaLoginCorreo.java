package Liverpool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLoginCorreo extends JFrame {

	private JPanel contentPane;
	private JTextField correo;
	private JTextField contraseņa;
	static Modelo mimodelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mimodelo = new Modelo();
					VentanaLoginCorreo frame = new VentanaLoginCorreo(mimodelo);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaLoginCorreo(Modelo mimodelo) {
		this.mimodelo=mimodelo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 256, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(227,27,35));
		
		JLabel lblMail = new JLabel(mimodelo.getLoginCorreoTextoLabeluser());
		lblMail.setBounds(10, 41, 58, 14);
		contentPane.add(lblMail);
		
		JLabel lblPassword = new JLabel(mimodelo.getLoginCorreoTextoLabelpassword());
		lblPassword.setBounds(10, 96, 58, 14);
		contentPane.add(lblPassword);
		
		correo = new JTextField();
		correo.setBounds(68, 38, 162, 20);
		contentPane.add(correo);
		correo.setColumns(10);
		
		contraseņa = new JTextField();
		contraseņa.setColumns(10);
		contraseņa.setBounds(68, 93, 162, 20);
		contentPane.add(contraseņa);
		
		JButton btnLogIn = new JButton(mimodelo.getLoginCorreoTextoBotonLogin());
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread hiloventa = new Thread( new POP3(mimodelo,correo.getText(),contraseņa.getText()));
				hiloventa.start();
				//POP3 correos = new POP3(mimodelo,correo.getText(),contraseņa.getText());
				//correos.setVisible(true);
				
//				HiloCorreo hilo = new HiloCorreo(correos);
//				hilo.start();
				//VentanaClientePOP3 correos = new VentanaClientePOP3(mimodelo,correo.getText(),contraseņa.getText());
				//correos.setVisible(true);	
			}
		});
		btnLogIn.setBounds(141, 182, 89, 23);
		btnLogIn.setBorder(new LineBorder(Color.YELLOW));
		contentPane.add(btnLogIn);
		
		JButton btnCancel = new JButton(mimodelo.getLoginCorreoTextoBotonCancel());
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				dispose();
			}
		});
		btnCancel.setBounds(10, 182, 89, 23);
		btnCancel.setBorder(new LineBorder(Color.YELLOW));
		contentPane.add(btnCancel);
	}
}
