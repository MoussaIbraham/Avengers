package Liverpool;

import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.mail.pop3.POP3Store;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
	static ArrayList <ReceivedMail> textoscorreos = new ArrayList<ReceivedMail>();
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
				
				
				abrircorreo();
			}
		});
		btnAbrirCorreo.setBounds(568, 21, 108, 35);
		contentPane.add(btnAbrirCorreo);
		
		
		JButton btnEscribirCorreo = new JButton(mimodelo.getTextoPOPBotonEscribirCorreo());
		btnEscribirCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaClienteSMTP escribircorreo = new VentanaClienteSMTP(user, password);
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
		btnCerrarCorreo.setBounds(568, 156, 108, 35);
		contentPane.add(btnCerrarCorreo);
		
		JButton btnActualizar = new JButton(mimodelo.getTextoPOPBotonActualizarCorreo());
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				try {
					receiveEmail();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnActualizar.setBounds(568, 110, 108, 35);
		contentPane.add(btnActualizar);
		
		JPanel lista = new JPanel();
		lista.setBackground(Color.WHITE);
		lista.setBounds(10, 21, 548, 358);
		contentPane.add(lista);
		
		try {
			receiveEmail();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
	           
	            	if(mensajes[i].isMimeType("text/*")) {
	            	ReceivedMail correo = new ReceivedMail(mensajes[i].getFrom()[0].toString(),mensajes[i].getSubject().toString(),mensajes[i].getContent().toString());
		            textoscorreos.add(correo);
	            	recibidosarray.add("Recibido de: "+mensajes[i].getFrom()[0] + " Asunto: "+mensajes[i].getSubject().toString());
	            	}
	            	else if(mensajes[i].isMimeType("multipart/*")) {
	            		ReceivedMail correo = new ReceivedMail(mensajes[i].getFrom()[0].toString(),mensajes[i].getSubject().toString(),getTextFromMimeMultipart(mensajes[i]));
			            textoscorreos.add(correo);
		            	recibidosarray.add("Recibido de: "+mensajes[i].getFrom()[0] + " Asunto: "+mensajes[i].getSubject().toString());
	            	}
	            }
	            modelocorreos.addAll(recibidosarray);	
	    		bandeja = new JList();
	    		bandeja.setModel(modelocorreos);
	            
	        }catch(javax.mail.NoSuchProviderException e) {	
	        } catch (MessagingException e) {
				e.printStackTrace();
			}
	        catch(NullPointerException e) {
	        	
	        }
	}
	
	public static void abrircorreo() {
		
		int selecccionado = bandeja.getSelectedIndex();;
		
		
		VentanaVerCorreo verventana = new VentanaVerCorreo(mimodelo, selecccionado, textoscorreos);
		verventana.setVisible(true);
		
		
		
	}
	
	public static String getTextFromMimeMultipart(Message mimeMultipart) {
		
		String result = "";
		
		try {
        Multipart multi = (Multipart) mimeMultipart.getContent();
        int partCount = multi.getCount();
        
        for (int i = 0; i < partCount; i++) {
            BodyPart bodyPart = multi.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + bodyPart.getContent();
                break;
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = html;
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMimeMultipart((Message) bodyPart.getContent());
            }
        }
    }
	catch(IOException e) {
		
	} catch (MessagingException e) {
		e.printStackTrace();
	}
		return result;
}
}