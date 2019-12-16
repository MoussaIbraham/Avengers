package Liverpool;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class VentanaPruebaClientePOP3 extends JFrame {
	
	private JPanel contentPane;
	static Modelo mimodelo;
	static ArrayList <ReceivedMail> correosrecibidos = new ArrayList<ReceivedMail>();
	private static String user = "dmatasalazar.sanjose@alumnado.fundacionloyola.net";
	private static String password = "21485902";
	private static JList bandeja;
	private static DefaultListModel modelocorreos = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPruebaClientePOP3 frame = new VentanaPruebaClientePOP3();
					try {
						receiveEmail();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					bandeja = new JList();
					bandeja.setModel(modelocorreos);
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
	public VentanaPruebaClientePOP3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 258, 198);
		contentPane.add(scrollPane);
		
		JList bandeja = new JList();
		scrollPane.setRowHeaderView(bandeja);
		
		JButton btnAbrirCorreo = new JButton("New button");
		btnAbrirCorreo.setBounds(318, 40, 85, 21);
		contentPane.add(btnAbrirCorreo);
		btnAbrirCorreo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton btnEscribirCorreo = new JButton("New button");
		btnEscribirCorreo.setBounds(318, 122, 85, 21);
		contentPane.add(btnEscribirCorreo);
		btnEscribirCorreo.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaClienteSMTP();	
			}
		});
		
		
		JButton btnCerrarCorreo = new JButton("New button");
		btnCerrarCorreo.setBounds(318, 208, 85, 21);
		contentPane.add(btnCerrarCorreo);
		btnCerrarCorreo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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

	            System.out.println("EStos son todos los correos= "+(mensajes[1].getSubject().toString()));
	            
	            for(int i=0;i<mensajes.length-1;i++) {
	            	System.out.println(""+mensajes[1].getFrom().toString());
	            ReceivedMail correo = new ReceivedMail(mensajes[1].getFrom().toString(),mensajes[1].getSubject().toString(),mensajes[1].getContent().toString(),mensajes[1].getReceivedDate().toString());
	            correosrecibidos.add(correo);
	            }
	            		
	            modelocorreos.addAll(correosrecibidos);
	        }catch(javax.mail.NoSuchProviderException e) {	
	        } catch (MessagingException e) {
				e.printStackTrace();
			}catch (NullPointerException e) {
			}
	}
}
