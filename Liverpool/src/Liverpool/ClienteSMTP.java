package Liverpool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.apache.commons.net.smtp.*;


public class ClienteSMTP extends JFrame implements ActionListener {

    // se crea cliente SMTP seguro
    AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();
    // datos del usuario y del servidor
    String server = "smtp.gmail.com";
    String username = JOptionPane.showInputDialog(null, "Introduce email");

    JPasswordField pf = new JPasswordField();
    int pass = JOptionPane.showConfirmDialog(null, pf, "Introduce contraseña", JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE);
    String password = "";

    int puerto = 25;

    //Paneles dentro del SMTP
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();

    //Etiquetas para el envio de los mensajes
    JLabel labelPersona = new JLabel("Para:");
    JLabel labelAsunto = new JLabel("Asunto:");
    JLabel labelMensaje = new JLabel("Mensaje:");

    //Cajas para el envio de la informacion recogida en las cajas
    JTextField cajaPersona = new JTextField(20);
    JTextField cajaAsunto = new JTextField(20);
    JTextArea cajaMensaje = new JTextArea(4, 30);

    //Botones para enviar el mensaje y salir de dicha ventana
    JButton botonEnviar = new JButton("Enviar Mensaje");
    JButton botonSalir = new JButton("Salir");

    /**
     * Constructor de la clase ClienteSMTP
     */
    public ClienteSMTP() {
        super("CLIENTE SMTP - ENV�?O DE MAILS");

        setLayout(new GridLayout(4, 1));
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);

        panel1.add(labelPersona);
        panel1.add(cajaPersona);

        panel2.add(labelAsunto);
        panel2.add(cajaAsunto);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        cajaMensaje.setBorder(border);

        JScrollPane barraDesplazamiento = new JScrollPane(cajaMensaje);
        barraDesplazamiento.setPreferredSize(new Dimension(455, 55));

        panel3.add(labelMensaje);
        panel3.add(barraDesplazamiento);

        panel4.add(botonEnviar);
        panel4.add(botonSalir);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(625, 345);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        botonEnviar.addActionListener(this);
        botonSalir.addActionListener(this);

        if (pass == JOptionPane.OK_OPTION) {
            password = new String(pf.getPassword());
            System.out.println(password);
        }

        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "CORREO O CONTRASEÑA ERRÓNEA");
            this.dispose();
        }

    }

    @Override
    /**
     * Métodos atribuidos a cada botón, el la que si pulsamos el botón enviar
     * con los campos vacíos nos pide que los rellenemos, y si están rellenos
     * envía el correo.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEnviar) {
            if (cajaPersona.getText().equals("") || cajaAsunto.getText().equals("")
                || cajaMensaje.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "RELLENE LOS CAMPOS");
            } else {
                try {
                    int respuesta;
                    // Creación de la clave para establecer un canal seguro
                    KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                    kmf.init(null, null);
                    KeyManager km = kmf.getKeyManagers()[0];
                    // nos conectamos al servidor SMTP
                    client.connect(server, puerto);
                    System.out.println("1 - " + client.getReplyString());
                    // se establece la clave para la comunicación segura
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
                            String destinol = cajaPersona.getText();
                            String asunto = cajaAsunto.getText();
                            String mensaje = cajaMensaje.getText();

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
                cajaAsunto.setText("");
                cajaMensaje.setText("");
                cajaPersona.setText("");
            }

        }

        if (e.getSource() == botonSalir) {
            this.dispose();
        }
    }
}//ClienteSMTP
