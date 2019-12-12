package Liverpool;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenu extends JFrame {

	private JPanel contentPane;
	static Modelo mimodelo;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu frame = new VentanaMenu(mimodelo);
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
	public VentanaMenu(Modelo mimodelo) {
		this.mimodelo=mimodelo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenido = new JLabel(mimodelo.getTextoVentanaMenu());
		lblBienvenido.setBounds(5, 5, 567, 25);
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblBienvenido);
		
		//Accion boton gestion
		JButton btnGestinDeDatos = new JButton(mimodelo.getTextoBotonGestion());
		btnGestinDeDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DirigentesWindow Gestion = new DirigentesWindow("usuario", "Contraseña", mimodelo);
			}
		});
		btnGestinDeDatos.setBounds(224, 56, 128, 39);
		contentPane.add(btnGestinDeDatos);
		
		//Accion boton correo
		JButton btnCorreo = new JButton(mimodelo.getTextoBotonCorreo());
		btnCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCorreo correo = new VentanaCorreo(mimodelo);
			}
		});
		btnCorreo.setBounds(224, 118, 128, 39);
		contentPane.add(btnCorreo);
		
		//Accion boton salir
		JButton btnSalir = new JButton(mimodelo.getTextoBotonSalir());
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false); //Solucion cutre
			}
		});
		btnSalir.setBounds(224, 178, 128, 39);
		contentPane.add(btnSalir);
	}
}
