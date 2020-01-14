package Liverpool;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
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
import java.awt.Color;
import java.awt.Toolkit;

public class POP3 extends JFrame implements Runnable{

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
	
	static JButton closemail;
	static JButton refresh;
	static JButton sendmail;
	static JButton openmail;
	
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
		setTitle("BANDEJA ENTRADA");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/app.png"));
		this.mimodelo = mimodelo;
		this.user = user;
		this.password = password;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(227,27,35));
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
		openmail = new JButton(m.getTextoPOPBotonAbrirCorreo());
		openmail.setBounds(550, 42, 126, 55);
		openmail.setBorder(new LineBorder(Color.YELLOW));
		contentPane.add(openmail);
		openmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrircorreo();
			}
		});
		
		sendmail = new JButton(m.getTextoSMTPBotonEnviar());
		sendmail.setBounds(550, 122, 126, 53);
		sendmail.setBorder(new LineBorder(Color.YELLOW));
		contentPane.add(sendmail);
		sendmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				VentanaClienteSMTP escribircorreo = new VentanaClienteSMTP(user, password, mimodelo);
				escribircorreo.setVisible(true);	
			}
		});
		
		refresh = new JButton(m.getTextoPOPBotonActualizarCorreo());
		refresh.setBounds(550, 204, 126, 53);
		refresh.setBorder(new LineBorder(Color.YELLOW));
		contentPane.add(refresh);
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ControlButtons(false);
					receiveEmail();
					ControlButtons(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		closemail = new JButton(m.getTextoBotonCerrar());
		closemail.setForeground(new Color(255, 0, 0));
		closemail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closemail.setBounds(535, 320, 160, 53);
		closemail.setBorder(new LineBorder(Color.YELLOW));
		contentPane.add(closemail);
		
		setVisible(true);
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
			
			}
				modelocorreos.removeAllElements();
				
				Collections.reverse(recibidosarray);
				Collections.reverse(textoscorreos);
				
				modelocorreos.addAll(recibidosarray);
				list.setModel(modelocorreos);
			
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	public static void ControlButtons(boolean active) {
		closemail.setEnabled(active);
		refresh.setEnabled(active);
		sendmail.setEnabled(active);
		openmail.setEnabled(active);
	}
	
	

	@Override
	public void run() {
		try {
			while(true) {
			Thread.sleep(20000);
			ControlButtons(false);
			receiveEmail();
			ControlButtons(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
