package Liverpool;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class POP3 extends JFrame {

	private JPanel contentPane;
	static ArrayList<ReceivedMail> textoscorreos = new ArrayList<ReceivedMail>();
	static ArrayList<String> recibidosarray = new ArrayList<String>();
	static String user;
	static String password;
	static Modelo mimodelo;
	static DefaultListModel modelocorreos = new DefaultListModel();
	static JList list;
	Modelo m = new Modelo();
	
	
	static Properties prop = new Properties();
	static Message[] mensajes;
	static Session sesion = Session.getInstance(prop);
	static Store store;
	static Folder folder;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POP3 frame = new POP3(mimodelo, user, password);
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
	public POP3(Modelo mimodelo, String user,String password) {
		this.mimodelo = mimodelo;
		this.user = user;
		this.password = password;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 39, 450, 366);
		contentPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		try {
			receiveEmail();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JButton openmail = new JButton(m.getTextoPOPBotonAbrirCorreo());
		openmail.setBounds(577, 50, 99, 34);
		contentPane.add(openmail);
		openmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrircorreo();
			}
		});
		
		JButton sendmail = new JButton(m.getTextoSMTPBotonEnviar());
		sendmail.setBounds(577, 125, 99, 39);
		contentPane.add(sendmail);
		sendmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				VentanaClienteSMTP escribircorreo = new VentanaClienteSMTP(user, password);
				escribircorreo.setVisible(true);	
			}
		});
		
		JButton refresh = new JButton(m.getTextoPOPBotonActualizarCorreo());
		refresh.setBounds(577, 199, 99, 39);
		contentPane.add(refresh);
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					receiveEmail();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton closemail = new JButton(m.getTextoBotonCerrar());
		closemail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closemail.setBounds(577, 271, 99, 39);
		contentPane.add(closemail);
	}

	public static void abrircorreo() {

		int selecccionado = list.getSelectedIndex();
		VentanaVerCorreo verventana = new VentanaVerCorreo(mimodelo, selecccionado, textoscorreos);
		verventana.setVisible(true);
	}

	public static String transformartexto(Message mimeMultipart) {

		String textoArreglado = "";

		try {
			Multipart multi = (Multipart) mimeMultipart.getContent();
			int partCount = multi.getCount();

			for (int i = 0; i < partCount; i++) {
				BodyPart bodyPart = multi.getBodyPart(i);
				if (bodyPart.isMimeType("text/plain")) {
					textoArreglado = textoArreglado + bodyPart.getContent();
					break;
				} else if (bodyPart.isMimeType("text/html")) {
					String html = (String) bodyPart.getContent();
					textoArreglado = html;
				} else if (bodyPart.getContent() instanceof MimeMultipart) {
					textoArreglado = textoArreglado + transformartexto((Message) bodyPart.getContent());
				}
			}
		} catch (IOException e) {

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return textoArreglado;
	}

	public static void receiveEmail() throws IOException {

		recibidosarray.removeAll(recibidosarray);
		textoscorreos.removeAll(textoscorreos);

		prop.setProperty("mail.pop3.starttls.enable", "false");
		prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.pop3.socketFactory.fallback", "false");
		prop.setProperty("mail.pop3.port", "995");
		prop.setProperty("mail.pop3.socketFactory.port", "995");
		
		
		try {
			store = sesion.getStore("pop3");
			store.connect("pop.gmail.com", user, password);
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			mensajes = folder.getMessages();
			for (int i = 0; i < mensajes.length; i++) {
				if (mensajes[i].isMimeType("text/*")) {
					ReceivedMail correo = new ReceivedMail(mensajes[i].getFrom()[0].toString(),
							mensajes[i].getSubject().toString(), mensajes[i].getContent().toString());
					textoscorreos.add(correo);
					recibidosarray.add("Recibido de: " + mensajes[i].getFrom()[0] + " Asunto: " + mensajes[i].getSubject().toString());
				} 
				else if (mensajes[i].isMimeType("multipart/*")) {
					ReceivedMail correo = new ReceivedMail(mensajes[i].getFrom()[0].toString(),
							mensajes[i].getSubject().toString(), transformartexto(mensajes[i]));
					textoscorreos.add(correo);
					recibidosarray.add("Recibido de: " + mensajes[i].getFrom()[0] + " Asunto: " + mensajes[i].getSubject().toString());
				}
				modelocorreos.removeAllElements();
				
				Collections.reverse(recibidosarray);
				Collections.reverse(textoscorreos);
				
				modelocorreos.addAll(recibidosarray);
				list.setModel(modelocorreos);
			}
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
