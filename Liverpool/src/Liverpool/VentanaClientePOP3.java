package Liverpool;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaClientePOP3 extends JFrame {

	private JPanel contentPane;
	static Modelo mimodelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mimodelo = new Modelo();
					VentanaClientePOP3 frame = new VentanaClientePOP3(mimodelo);
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
	public VentanaClientePOP3(Modelo mimodelo) {
		this.mimodelo=mimodelo;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 592, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnAbrirCorreo = new JButton(mimodelo.getTextoPOPBotonAbrirCorreo());
		btnAbrirCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
			}
		});
		btnAbrirCorreo.setBounds(458, 21, 108, 35);
		contentPane.add(btnAbrirCorreo);
		
		
		JButton btnEscribirCorreo = new JButton(mimodelo.getTextoPOPBotonEscribirCorreo());
		btnEscribirCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//VentanaClienteSMTP escribircorreo = new VentanaClienteSMTP(mimodelo);
				
			}
		});
		btnEscribirCorreo.setBounds(458, 77, 108, 35);
		contentPane.add(btnEscribirCorreo);
		
		
		JButton btnCerrarCorreo = new JButton(mimodelo.getTextoPOPBotonCerrarCorreo());
		btnCerrarCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
			}
		});
		btnCerrarCorreo.setBounds(458, 134, 108, 35);
		contentPane.add(btnCerrarCorreo);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 21, 438, 275);
		contentPane.add(panel);
		
		JList list = new JList();
		panel.add(list);
	}
}
