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
	private static String User = "";
	private static String pass = "";
	static FTPClient client = null;
	private static Modelo mimodelo;

	/**
	 * Create the frame.
	 */
	public DirigentesWindow(String string, String pass, Modelo mimodelo) {
		this.mimodelo = mimodelo;
		ClientFTP cliente = new ClientFTP(string, pass, mimodelo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		User = string;
		this.pass = pass;

		list = new JList();
		list.setBounds(41, 31, 310, 332);
		contentPane.add(list);
		try {
			LlenarLista(cliente.getFiles(), "/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton btnSalir = new JButton(mimodelo.getTextoBotonSalir());
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginWindow login = new LoginWindow(mimodelo);
				String[] args = null;
				setVisible(false);
				LoginWindow.main(args);
			}
		});
		btnSalir.setBounds(429, 317, 89, 23);
		contentPane.add(btnSalir);
	}

	public static void LlenarLista(FTPFile[] files, String direc2) throws IOException {
		if (files == null)
			return;

		DefaultListModel lista = new DefaultListModel<>();
		lista = new DefaultListModel<>();

		list.setForeground(Color.blue);
		list.setFont(new Font("Courier", Font.PLAIN, 12));

		list.removeAll();

		lista.addElement(direc2);

		for (int i = 0; i < files.length; i++) {
			if (!(files[i].getName()).equals(".") && !(files[i].getName()).equals("..")) {
				// Se obtiene el nombre del fichero o directorio
				String f = files[i].getName();
				// Si es directorio se a�ade al nombre (DIR)
				if (files[i].isDirectory()) {
					f = "(Carpeta) " + f;
				}
				lista.addElement(f);

			}
		}
		list.setModel(lista);
	}
}