package Liverpool;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCrear extends JFrame {

	private JPanel contentPane;
	private JTextField caja;
	private JButton btnNewButton;
	static Modelo mimodelo;
	String Nombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCrear frame = new VentanaCrear(mimodelo);
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
	public VentanaCrear(Modelo mimodelo) {
		this.mimodelo=mimodelo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		caja = new JTextField();
		caja.setBounds(39, 39, 235, 20);
		contentPane.add(caja);
		caja.setColumns(10);
		
		JLabel lblIntroduzcaElNombre = new JLabel(mimodelo.getTextoVentanaCrearCarpeta());
		lblIntroduzcaElNombre.setBounds(61, 11, 189, 14);
		contentPane.add(lblIntroduzcaElNombre);
		
		btnNewButton = new JButton(mimodelo.getTextoBotonCrear());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Nombre = caja.getText();
				
			}
		});
		btnNewButton.setBounds(99, 83, 107, 48);
		contentPane.add(btnNewButton);
	}
}
