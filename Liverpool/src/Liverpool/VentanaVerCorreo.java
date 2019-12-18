package Liverpool;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaVerCorreo extends JFrame {

	private JPanel contentPane;
	private JTextField de;
	private JTextField asunto;
	static Modelo mimodelo;
	static ArrayList <ReceivedMail> textoscorreos = new ArrayList<ReceivedMail>();
	private int posicion;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerCorreo frame = new VentanaVerCorreo(mimodelo, -1, textoscorreos);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VentanaVerCorreo(Modelo mimodelo, int posicion, ArrayList<ReceivedMail> textoscorreos) {
		this.mimodelo=mimodelo;
		this.textoscorreos=textoscorreos;
		this.posicion=posicion;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 655, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblDe = new JLabel(mimodelo.getVerCorreoTextoLabelde());
		lblDe.setBounds(10, 11, 48, 14);
		contentPane.add(lblDe);
		
		JLabel lblAsunto = new JLabel(mimodelo.getVerCorreoTextoLabelAsunto());
		lblAsunto.setBounds(10, 57, 48, 14);
		contentPane.add(lblAsunto);
		
		de = new JTextField();
		de.setEditable(false);
		de.setBounds(68, 8, 561, 20);
		contentPane.add(de);
		de.setColumns(10);
		
		asunto = new JTextField();
		asunto.setEditable(false);
		asunto.setColumns(10);
		asunto.setBounds(68, 51, 561, 20);
		contentPane.add(asunto);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 619, 165);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		vertexto();
	}
	
	
	public void vertexto() {
		
		
		int iend = textoscorreos.get(posicion).getTransmitter().indexOf("<");
		
		de.setText(textoscorreos.get(posicion).getTransmitter().substring(iend));
		
		asunto.setText(textoscorreos.get(posicion).getSubject());
		
		textArea.setText(textoscorreos.get(posicion).getBodyMail());
	}
}
