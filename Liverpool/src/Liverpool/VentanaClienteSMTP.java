package Liverpool;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import java.awt.event.ActionEvent;

public class VentanaClienteSMTP extends JFrame {

	private JPanel assunt;
	private JTextField addressee;
	private JTextField subject;
	private JTextField bodyMenssage;
	Modelo modelo = new Modelo();
	// se crea cliente SMTP seguro
	AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();
	/*
	 * Datos del usuario y conexi�n
	 */
	String username = "dmatasalazar.sanjose@alumnado.fundacionloyola.net";//modelo.getAlmacenNombreUsuario();
	String password = "21485902"; //modelo.getAlmacenContrase�a();
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

	public void ClienteSMTP() {
		 try {
             int respuesta;
             // Creación de la clave para establecer un canal seguro
             KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
             kmf.init(null, null);
             KeyManager km = kmf.getKeyManagers()[0];
             /*
              *  nos conectamos al servidor SMTP
              */
             client.connect(server, puerto);
             System.out.println("1 - " + client.getReplyString());
             /*
              * the key to secure communication is established
              */
             client.setKeyManager(km);
             respuesta = client.getReplyCode();
             if (!SMTPReply.isPositiveCompletion(respuesta)) {
                 client.disconnect();
                 System.err.println("CONEXIÓN RECHAZADA.");
                 System.exit(1);
             }
             // se envia el commando EHLO
             client.ehlo(server);// necesario
             System.out.println("2 - " + client.getReplyString());
             // Se ejecuta el comando STARTTLS y se comprueba si es true
             if (client.execTLS()) {
                 System.out.println("3 - " + client.getReplyString());
                 // se realiza la autenticación con el servidor
                 if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {

                     System.out.println("4 - " + client.getReplyString());
                     String destinol = addressee.getText();
                     String asunto = subject.getText();
                     String mensaje = bodyMenssage.getText();

                     // se crea la cabecera
                     SimpleSMTPHeader cabecera = new SimpleSMTPHeader(username, destinol, asunto);

                     // el nombre de usuario y el email de origen coinciden
                     client.setSender(username);
                     client.addRecipient(destinol);
                     System.out.println("5 - " + client.getReplyString());

                     // se envia DATA
                     Writer writer = client.sendMessageData();
                     if (writer == null) { // fallo
                         System.out.println("FALLO AL ENVIAR DATA.");
                         System.exit(1);
                     }
                     writer.write(cabecera.toString()); // cabecera
                     writer.write(mensaje);// luego mensaje
                     writer.close();
                     System.out.println("6 - " + client.getReplyString());
                     boolean exito = client.completePendingCommand();
                     System.out.println("7 - " + client.getReplyString());
                     if (!exito) { // fallo
                         System.out.println("FALLO AL FINALIZAR TRANSACCIÓN.");
                         System.exit(1);
                     } else {
                         JOptionPane.showMessageDialog(null, "MENSAJE ENVIADO");
                     }
                 } else {
                     System.out.println("USUARIO NO AUTENTICADO.");
                     JOptionPane.showMessageDialog(null, "CORREO O CONTRASEÑA ERRÓNEA");
                     this.dispose();
                 }
             } else {
                 System.out.println("FALLO AL EJECUTAR STARTTLS.");
             }
         } catch (IOException ioe) {
             System.err.println("Could not connect to server.");
             ioe.printStackTrace();
             System.exit(1);
             try {
                 client.disconnect();
             } catch (IOException f) {
                 f.printStackTrace();
             }
             System.out.println("Fin de envio.");
             System.exit(0);
         } catch (NoSuchAlgorithmException nsa) {
             nsa.printStackTrace();
         } catch (UnrecoverableKeyException uke) {
             uke.printStackTrace();
         } catch (KeyStoreException kse) {
             kse.printStackTrace();
         } catch (InvalidKeyException ike) {
             ike.printStackTrace();
         } catch (InvalidKeySpecException ikse) {
             ikse.printStackTrace();
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
					ClienteSMTP();
				}
			}
		});
		buttonSend.setBounds(315, 232, 85, 21);
		assunt.add(buttonSend);

		addressee = new JTextField();
		addressee.setBounds(81, 29, 236, 19);
		assunt.add(addressee);
		addressee.setColumns(10);

		JLabel lblNewLabel = new JLabel(modelo.getTextoSMTPLabelAyudaDestinatario());
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

		JButton btnNewButton = new JButton(modelo.getTextoSMTPLabelA�adirArchivo());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
