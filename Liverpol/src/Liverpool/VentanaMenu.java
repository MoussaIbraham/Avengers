package Liverpool;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaMenu extends JFrame {

	private JPanel contentPane;
	static Modelo mimodelo;
	private static VentanaMenu frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mimodelo = new  Modelo();					
					frame = new VentanaMenu(mimodelo, args);
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
	public VentanaMenu(Modelo mimodelo, String[] args) {
		setTitle("MENU");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/app.png"));
		this.mimodelo=mimodelo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 272, 266);
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
		
		JButton btnAccederAArchivos = new JButton(mimodelo.getTextoBotonGestion());
		btnAccederAArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginWindow login = new LoginWindow(mimodelo, args);
				login.main(args);
				frame.dispose();
				
			}
		});
		btnAccederAArchivos.setBounds(61, 38, 142, 39);
		btnAccederAArchivos.setBorder(new LineBorder(Color.YELLOW));
		contentPane.add(btnAccederAArchivos);
		
		JButton btnAccederAlCorreo = new JButton(mimodelo.getTextoBotonCorreo());
		btnAccederAlCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaLoginCorreo correo = new VentanaLoginCorreo(mimodelo, args);
				correo.setVisible(true);
				frame.dispose();
				
				
			}
		});
		btnAccederAlCorreo.setBounds(61, 88, 142, 39);
		btnAccederAlCorreo.setBorder(new LineBorder(Color.YELLOW));
		contentPane.add(btnAccederAlCorreo);
		
		JButton btnSalir = new JButton(mimodelo.getTextoBotonSalir());
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(61, 138, 142, 39);
		btnSalir.setBorder(new LineBorder(Color.YELLOW));
		contentPane.add(btnSalir);
	}

}
