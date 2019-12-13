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

public class VentanaClienteSMTP extends JFrame {

	private JPanel assunt;
	private JTextField addressee;
	private JTextField subject;
	private JTextField bodyMenssage;
	Modelo modelo = new Modelo();
	ArrayList<String> filename = new ArrayList<String>();
	ArrayList<String> filepath = new ArrayList<String>();
	/*
	 * Datos del usuario y conexión
	 */
	String username = "dmatasalazar.sanjose@alumnado.fundacionloyola.net";//modelo.getAlmacenNombreUsuario();
	String pasword = "21485902"; //modelo.getAlmacenContraseña();
	String server = modelo.getTextoServerSMTP();
	int puerto = 25;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaClienteSMTP frame = new VentanaClienteSMTP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean enviarCorreo() {
		try { 
		Properties p = System.getProperties();
		p.put("mail.smtp.host", "smtp.gmail.com");
	    p.setProperty("mail.smtp.starttls.enable", "true"); 
	    p.setProperty("mail.smtp.port", "587"); 
	    p.setProperty("mail.smtp.user", username);
	    p.setProperty("mail.smtp.auth", "true");
	    

	    Session session = Session.getInstance(p);
	    session.setDebug(true);
	    /*
	     * Texto
	     */
	    BodyPart text = new MimeBodyPart();
	    text.setText(bodyMenssage.getText());
	    MimeMultipart m = new MimeMultipart();
	    m.addBodyPart(text);// Add text  
	    MimeMessage message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(username));
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressee.getText()));
	    message.setSubject(subject.getText());  
	    /*
	     * Adjuntos
	     */
	    if(filepath.size()!=0) {
	    	for(int i=0;i<filepath.size();i++) {
	    	BodyPart adjuntfile = new MimeBodyPart();
		    adjuntfile.setDataHandler(new DataHandler(new FileDataSource(filepath.get(i))));
		    adjuntfile.setFileName(filename.get(i));
		    m.addBodyPart(adjuntfile);
	    	}
	    }
	    // Add Text and File
	    message.setContent(m);
	    /*
	     * 
	     */
	    Transport t = session.getTransport("smtp");
	    t.connect(username, pasword);
	    t.send(message,message.getRecipients(Message.RecipientType.TO));
	    t.close();
	        return true;
	    }
	    catch (MessagingException me) {
	        return false;
	    }
	}


	/**
	 * Create the frame.
	 */
	public VentanaClienteSMTP() {
		setTitle("Send Email");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		assunt = new JPanel();
		assunt.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(assunt);
		assunt.setLayout(null);

		JButton buttonSend = new JButton("Send\r\n");
		buttonSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (addressee.getText().equals("") || subject.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "RELLENE LOS CAMPOS");
				} else {
					Boolean correo = enviarCorreo();
					if(correo = true) {
						JOptionPane.showMessageDialog(null, "EMAIL CORRECT");
					}else {
						JOptionPane.showMessageDialog(null, "FAIL TO SEND EMAIL");
					}
				}
			}
		});
		buttonSend.setBounds(315, 232, 85, 21);
		assunt.add(buttonSend);

		addressee = new JTextField();
		addressee.setBounds(81, 29, 236, 19);
		assunt.add(addressee);
		addressee.setColumns(10);

		JLabel lblNewLabel = new JLabel(modelo.getTextoSMTPLabelDestinatario());
		lblNewLabel.setBounds(10, 32, 61, 13);
		assunt.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(modelo.getTextoSMTPLabelAsunto());
		lblNewLabel_1.setBounds(10, 61, 46, 13);
		assunt.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(modelo.getTextoSMTPLabelCuerpo());
		lblNewLabel_2.setBounds(10, 102, 46, 13);
		assunt.add(lblNewLabel_2);

		subject = new JTextField();
		subject.setBounds(81, 58, 236, 19);
		assunt.add(subject);
		subject.setColumns(10);

		bodyMenssage = new JTextField();
		bodyMenssage.setBounds(81, 99, 236, 108);
		assunt.add(bodyMenssage);
		bodyMenssage.setColumns(10);

		JLabel advise = new JLabel(modelo.getTextoSMTPLabelAyudaDestinatario());
		advise.setBounds(354, 32, 46, 13);
		assunt.add(advise);

		JButton btnNewButton = new JButton(modelo.getTextoSMTPLabelAñadirArchivo());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f;
				File file;
				f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.FILES_ONLY);
				f.setDialogTitle("Select to file where you Up");
	
				int returnVal = f.showDialog(f, "UP");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = f.getSelectedFile();
					String archivo = file.getAbsolutePath();
					String nombreArchivo = file.getName();
					filename.add(nombreArchivo);
					filepath.add(nombreArchivo);
			}
			}
		});
		btnNewButton.setBounds(157, 232, 85, 21);
		assunt.add(btnNewButton);

		JButton cancel = new JButton(modelo.getTextoSMTPBotonCancelar());
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancel.setBounds(10, 232, 85, 21);
		assunt.add(cancel);
	}
}
