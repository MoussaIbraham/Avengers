package Liverpool;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
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
		setBounds(100, 100, 272, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAccederAArchivos = new JButton(mimodelo.getTextoBotonGestion());
		btnAccederAArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//DirigentesWindow Gestion = new DirigentesWindow(string, pass, mimodelo);
				//Gestion.setVisible(True);

				
			}
		});
		btnAccederAArchivos.setBounds(61, 38, 142, 39);
		contentPane.add(btnAccederAArchivos);
		
		JButton btnAccederAlCorreo = new JButton(mimodelo.getTextoBotonCorreo());
		btnAccederAlCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//VentanaClientePOP3 correo = new VentanaClientePOP3(mimodelo);
				//correo.SetVisible(True);
				
			}
		});
		btnAccederAlCorreo.setBounds(61, 88, 142, 39);
		contentPane.add(btnAccederAlCorreo);
		
		JButton btnSalir = new JButton(mimodelo.getTextoBotonSalir());
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
			}
		});
		btnSalir.setBounds(61, 138, 142, 39);
		contentPane.add(btnSalir);
	}

}
