package Liverpool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;


public class ClientePOP3 extends JFrame implements ActionListener, Runnable {

    //Panel donde se visualiza la lista de ficheros
    JPanel panel1 = new JPanel();
    //Panel donde se visualiza el boton actualizar
    JPanel panel2 = new JPanel(); 
    //Panel donde se visualiza el boton salir
    JPanel panel3 = new JPanel(); 

    //�?rea donde se visualizan los correos electrónicos.
    JTextArea area = new JTextArea(); 

    //Botón que actualiza los correos.
    JButton updateButton = new JButton("Update"); 
    //Botón que cierra la ventana.
    JButton exitButton = new JButton("Exit"); 

    //Pide el correo electrónico del usuario.
    String usuario = JOptionPane.showInputDialog(null, "Enter your email"); 

    // caja donde introduce el usuario la contraseña
    JPasswordField pf = new JPasswordField();
    //Recupera la contraseña del panel
    int pass = JOptionPane.showConfirmDialog(null, pf, "Enter your pasword", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    //Pide la contraseña del usuario introducido anteriormente.
    String password = "";

    /**
     * Constructor de la clase ClientePOP3
     */
    public ClientePOP3() {
        super("CLIENTE POP3 - LECTURA DE MAILS");
        setLayout(new GridLayout(1, 2));

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        area.setBorder(border);

        JScrollPane slide_bar = new JScrollPane(area);
        slide_bar.setPreferredSize(new Dimension(455, 55));

        this.add(slide_bar);
        this.add(panel1);

        panel1.setLayout(new GridLayout(2, 1));

        panel1.add(panel2);
        panel1.add(panel3);

        updateButton.setPreferredSize(new Dimension(380, 60));
        exitButton.setPreferredSize(new Dimension(380, 60));

        panel2.add(updateButton);
        panel3.add(exitButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1250, 750);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        updateButton.addActionListener(this);
        exitButton.addActionListener(this);

        if (pass == JOptionPane.OK_OPTION) {
            password = new String(pf.getPassword());
        }
        area.setEnabled(false);
        area.setDisabledTextColor(Color.black);
    }

    public void run() {
        reciboMails();

    }// run

    /**
     * Método que nos permite ver los correos electrónicos de forma ordenada de
     * un correo electrónico según el correo y la contraseña introducida.
     */
    public void reciboMails() {
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
            store.connect("pop.gmail.com", usuario, password);
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            Message[] mensajes = folder.getMessages();
            System.out.println(
                "<------------------------------------------------->NÚMERO DE CORREOS: " + mensajes.length);
            for (int i = 0; i < mensajes.length; i++) {
                //Si es correo tipo texto plano
                if (mensajes[i].isMimeType("text/*")) {
                    try {
                        System.out.println("<------------------------------------------------------------------->DE: "
                            + mensajes[i].getFrom()[0].toString());
                        area.setText(area.getText() + "DE: " + mensajes[i].getFrom()[0].toString() + "\n");
                        System.out
                            .println("<------------------------------------------------------------------->ASUNTO: "
                                + mensajes[i].getSubject());
                        area.setText(area.getText() + "ASUNTO: " + mensajes[i].getSubject() + "\n");
                        System.out.println(
                            "<------------------------------------------------------------------->MENSAJE: "
                            + mensajes[i].getContent());
                        area.setText(area.getText() + "MENSAJE: " + mensajes[i].getContent());
                        area.setText(area.getText()
                            + "------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //Si es correo multiparte
                if (mensajes[i].isMimeType("multipart/*")) {
                    try {
                        System.out.println("<------------------------------------------------------------------->DE: "
                            + mensajes[i].getFrom()[0].toString());
                        area.setText(area.getText() + "DE: " + mensajes[i].getFrom()[0].toString() + "\n");
                        System.out
                            .println("<------------------------------------------------------------------->ASUNTO: "
                                + mensajes[i].getSubject());
                        area.setText(area.getText() + "ASUNTO: " + mensajes[i].getSubject() + "\n");
                        System.out.println("<--------------------------------------------------->MENSAJE: "
                            + getTextFromMimeMultipart(mensajes[i]));
                        area.setText(area.getText() + "MENSAJE: " + getTextFromMimeMultipart(mensajes[i]));
                        area.setText(area.getText()
                            + "------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException me) {
            JOptionPane.showMessageDialog(null, "CORREO O CONTRASEÑA ERRÓNEA");
            this.dispose();
        }
    }

    /**
     *
     * @param mimeMultipart lee un correo
     * @return devuelve todas las partes del correo
     * @throws Exception Recoge el texto de un correo multiparte de forma
     * recursiva.
     */
    public static String getTextFromMimeMultipart(Message mimeMultipart) throws Exception {
        Multipart multi = (Multipart) mimeMultipart.getContent();
        String result = "";
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
        return result;
    }

    @Override
    /**
     * Método atribuido a cada botón en el que si pulsas el botón salir cierras
     * la ventana, y si pulsas el botón actualizar te actualiza los correos
     * recibidos.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            this.dispose();
        }

        if (e.getSource() == updateButton) {
            area.setText("");
            reciboMails();
        }
    }
}
