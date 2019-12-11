package Liverpool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class DirigentesWindow extends JFrame {

	static JList list = null;
	private JPanel contentPane;
	static FTPClient client = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DirigentesWindow frame = new DirigentesWindow(client);
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
	public DirigentesWindow(FTPClient cliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		client = cliente;
		
		list = new JList();
		list.setBounds(41, 31, 310, 332);
		contentPane.add(list);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 // Cerrando sesión
	            try {
					client.logout();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 
	            // Desconectandose con el servidor
	            try {
					client.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            LoginWindow login = new LoginWindow();
	            String[] args = null;
				LoginWindow.main(args);
			}
		});
		btnSalir.setBounds(429, 317, 89, 23);
		contentPane.add(btnSalir);
	}
	
	public static void LlenarLista(FTPFile[] files, String direc2) {
		if(files == null) return;
		
		DefaultListModel lista = new DefaultListModel<>();		
		lista = new DefaultListModel<>();
		
		list.setForeground(Color.blue);
		list.setFont(new Font("Courier", Font.PLAIN, 12));
		
		list.removeAll();
		
		
	}
}
