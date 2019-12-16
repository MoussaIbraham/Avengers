package Liverpool;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaVerCorreo extends JFrame {

	private JPanel contentPane;
	private JTextField de;
	private JTextField asunto;
	static Modelo mimodelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerCorreo frame = new VentanaVerCorreo(mimodelo);
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
	public VentanaVerCorreo(Modelo mimodelo) {
		this.mimodelo=mimodelo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 82, 414, 168);
		contentPane.add(textArea);
		
		JLabel lblDe = new JLabel(mimodelo.getVerCorreoTextoLabelde());
		lblDe.setBounds(10, 11, 48, 14);
		contentPane.add(lblDe);
		
		JLabel lblAsunto = new JLabel(mimodelo.getVerCorreoTextoLabelAsunto());
		lblAsunto.setBounds(10, 57, 48, 14);
		contentPane.add(lblAsunto);
		
		de = new JTextField();
		de.setBounds(68, 8, 356, 20);
		contentPane.add(de);
		de.setColumns(10);
		
		asunto = new JTextField();
		asunto.setColumns(10);
		asunto.setBounds(68, 51, 356, 20);
		contentPane.add(asunto);
		
		vertexto();
	}
	
	
	public void vertexto() {
		
		
		
		
	}
	
	
}
