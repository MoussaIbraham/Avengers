package Liverpool;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.ObjectInputStream.GetField;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class VentanaClienteSMTP extends JFrame {

	private JPanel assunt;
	private JTextField addressee;
	private JTextField subject;
	private JTextField bodyMenssage;
	static Modelo modelo = new Modelo();
	ArrayList<String> filename = new ArrayList<String>();
	ArrayList<String> filepath = new ArrayList<String>();
	Transport t;
	DefaultListModel modelList = new DefaultListModel();
	/*
	 * Datos del usuario y conexión
	 */
	static String username = modelo.getAlmacenNombreUsuario();
	static String pasword =  modelo.getAlmacenContraseña();
	String server = modelo.getTextoServerSMTP();
	int puerto = 25;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaClienteSMTP frame = new VentanaClienteSMTP(username, pasword);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean enviarCorreo() {
		String [] destinatarios;		
		Properties p = System.getProperties();
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.setProperty("mail.smtp.starttls.enable", "true");
		p.setProperty("mail.smtp.port", "587");
		p.setProperty("mail.smtp.auth", "true");
		p.setProperty("mail.smtp.user", username);
		p.setProperty("mail.smtp.clave", pasword);
		
		Session session = Session.getInstance(p);
		session.setDebug(true);
		try {
			MimeMultipart m = new MimeMultipart();
			BodyPart text = new MimeBodyPart();
			MimeMessage message = new MimeMessage(session);
			text.setText(bodyMenssage.getText());
			m.addBodyPart(text);// Add text
			t = session.getTransport("smtp");
			if(addressee.getText().contains("/")) {
				destinatarios = addressee.getText().split("/");
				for(int i=0;i<destinatarios.length;i++) {
					message.setFrom(new InternetAddress(username));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatarios[i]));
					message.setSubject(subject.getText());	
				}
			}else {
			message.setFrom(new InternetAddress(username));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressee.getText()));
			message.setSubject(subject.getText());
			}
			/*
			 * Attached Files
			 */
			if (filepath.size() != 0) {
				for (int i = 0; i < filepath.size(); i++) {
					BodyPart adjuntfile = new MimeBodyPart();
					adjuntfile.setDataHandler(new DataHandler(new FileDataSource(filepath.get(i))));
					adjuntfile.setFileName(filename.get(i));
					m.addBodyPart(adjuntfile);
					System.out.println(filepath.get(i));
					System.out.println(filename.get(i));
					
				}
			}
			// Add Text and File
			message.setContent(m);
			/*
			 * Data Transport
			 */
			t.connect(modelo.getTextoServerSMTP(),username, pasword);
			t.sendMessage(message, message.getAllRecipients());
			return true;
		} catch (MessagingException me) {
			return false;
		} finally {
			try {
				t.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the frame.
	 * @param password 
	 * @param user 
	 */
	public VentanaClienteSMTP(String user, String password) {
		setTitle("Send Email");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 592, 377);
		assunt = new JPanel();
		assunt.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(assunt);
		assunt.setLayout(null);

		JButton buttonSend = new JButton("Send");
		buttonSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (addressee.getText().equals("") || subject.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "WRITE IN THE FIELDS");
				} else {
					Boolean correo = enviarCorreo();
					if (correo = true) {
						JOptionPane.showMessageDialog(null, "EMAIL SENT CORRECTLY");
					} else {
						JOptionPane.showMessageDialog(null, "FAIL TO SEND EMAIL");
					}
				}
			}
		});
		buttonSend.setBounds(424, 298, 85, 21);
		assunt.add(buttonSend);

		addressee = new JTextField();
		addressee.setBounds(81, 55, 236, 19);
		assunt.add(addressee);
		addressee.setColumns(10);

		JLabel lblNewLabel = new JLabel(modelo.getTextoSMTPLabelDestinatario());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 57, 61, 13);
		assunt.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(modelo.getTextoSMTPLabelAsunto());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 101, 46, 13);
		assunt.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(modelo.getTextoSMTPLabelCuerpo());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 145, 46, 13);
		assunt.add(lblNewLabel_2);

		subject = new JTextField();
		subject.setBounds(81, 99, 236, 19);
		assunt.add(subject);
		subject.setColumns(10);

		bodyMenssage = new JTextField();
		bodyMenssage.setBounds(81, 141, 241, 135);
		assunt.add(bodyMenssage);
		bodyMenssage.setColumns(10);

		JLabel advise = new JLabel(modelo.getTextoSMTPLabelAyudaDestinatario());
		advise.setBounds(368, 10, 200, 108);
		assunt.add(advise);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(346, 145, 210, 134);
		assunt.add(scrollPane);

		JList listAdjunt = new JList();
		listAdjunt.setModel(modelList);
		scrollPane.setViewportView(listAdjunt);
		
		JButton btnNewButton = new JButton(modelo.getTextoSMTPLabelAñadirArchivo());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f;
				File file;
				f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.FILES_ONLY);
				f.setDialogTitle("Select to file where you Up");

				int returnVal = f.showDialog(f, "SELECT");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = f.getSelectedFile();
					String archivo = file.getAbsolutePath();
					String nombreArchivo = file.getName();
					filename.add(nombreArchivo);
					filepath.add(archivo);
					modelList.addElement(nombreArchivo);
				}
			}
		});
		btnNewButton.setBounds(228, 298, 85, 21);
		assunt.add(btnNewButton);

		JButton cancel = new JButton(modelo.getTextoSMTPBotonCancelar());
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setBounds(59, 298, 85, 21);
		assunt.add(cancel);

	}
}
