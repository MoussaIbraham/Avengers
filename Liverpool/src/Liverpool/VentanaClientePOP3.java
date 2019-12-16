package Liverpool;

import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.mail.pop3.POP3Store;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class VentanaClientePOP3 extends JFrame {

	private JPanel contentPane;
	static Modelo mimodelo;
	static ArrayList <ReceivedMail> correosrecibidos = new ArrayList<ReceivedMail>();
	static ArrayList <String> recibidosarray = new ArrayList<String>(); 
	static String user;
	static String password;
	static JList bandeja;
	static DefaultListModel modelocorreos = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mimodelo = new Modelo();
					VentanaClientePOP3 frame = new VentanaClientePOP3(mimodelo, user, password);
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
	public VentanaClientePOP3(Modelo mimodelo,String user, String password) {
		this.mimodelo=mimodelo;
		this.user=user;
		this.password=password;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 702, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnAbrirCorreo = new JButton(mimodelo.getTextoPOPBotonAbrirCorreo());
		btnAbrirCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
			}
		});
		btnAbrirCorreo.setBounds(568, 21, 108, 35);
		contentPane.add(btnAbrirCorreo);
		
		
		JButton btnEscribirCorreo = new JButton(mimodelo.getTextoPOPBotonEscribirCorreo());
		btnEscribirCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaClienteSMTP escribircorreo = new VentanaClienteSMTP();
				escribircorreo.setVisible(true);
				
			}
		});
		btnEscribirCorreo.setBounds(568, 67, 108, 35);
		contentPane.add(btnEscribirCorreo);
		
		
		JButton btnCerrarCorreo = new JButton(mimodelo.getTextoPOPBotonCerrarCorreo());
		btnCerrarCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnCerrarCorreo.setBounds(568, 113, 108, 35);
		contentPane.add(btnCerrarCorreo);
		
		JPanel lista = new JPanel();
		lista.setBackground(Color.WHITE);
		lista.setBounds(10, 21, 548, 358);
		contentPane.add(lista);
		
		try {
			receiveEmail();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		bandeja = new JList();
		bandeja.setModel(modelocorreos);
		lista.add(bandeja);
		
	}
	

	public static void receiveEmail() throws IOException {  
			  
		 Properties prop = new Properties();
	        // Deshabilitamos TLS
	        prop.setProperty("mail.pop3.starttls.enable", "false");
	        // Hay que usar SSL
	        prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        prop.setProperty("mail.pop3.socketFactory.fallback", "false");
	        // Puerto 995 para conectarse.
	        prop.setProperty("mail.pop3.port", "995");
	        prop.setProperty("mail.pop3.socketFactory.port", "995");
	        
	        Session sesion = Session.getInstance(prop);
	        sesion.setDebug(true);

	        Store store;
	        try {
	            store = sesion.getStore("pop3");
	            store.connect("pop.gmail.com", user, password);
	            Folder folder = store.getFolder("INBOX");
	            folder.open(Folder.READ_ONLY);
	            Message[] mensajes = folder.getMessages();	

	  
	            
	            for(int i=0;i<mensajes.length;i++) {
	            //ReceivedMail correo = new ReceivedMail(mensajes[i].getFrom().toString(),mensajes[i].getSubject().toString(),mensajes[i].getContent().toString());
	            //correosrecibidos.add(correo);
	            	recibidosarray.add("Recibido de: "+mensajes[i].getFrom().toString() + " Asunto: "+mensajes[i].getSubject().toString());
	            }
	            modelocorreos.addAll(recibidosarray);		
	            //modelocorreos.addAll(correosrecibidos);
	            
	        }catch(javax.mail.NoSuchProviderException e) {	
	        } catch (MessagingException e) {
				e.printStackTrace();
			}
	        catch(NullPointerException e) {
	        	
	        }
	}
}