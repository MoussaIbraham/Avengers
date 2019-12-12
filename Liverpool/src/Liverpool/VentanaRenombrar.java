package Liverpool;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaRenombrar extends JFrame {

	private JPanel contentPane;
	static Modelo mimodelo;
	private JTextField caja;
	private JButton btnNewButton;
	String nuevoNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRenombrar frame = new VentanaRenombrar(mimodelo);
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
	public VentanaRenombrar(Modelo mimodelo) {
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
		
		JLabel lblIntroduzcaElNombre = new JLabel(mimodelo.getTextoVentanaRenombrar());
		lblIntroduzcaElNombre.setBounds(61, 11, 189, 14);
		contentPane.add(lblIntroduzcaElNombre);
		
		btnNewButton = new JButton(mimodelo.getTextoBotonRenombrar());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoNombre = caja.getText();
				
			}
		});
		btnNewButton.setBounds(99, 83, 107, 48);
		contentPane.add(btnNewButton);
	}

}
