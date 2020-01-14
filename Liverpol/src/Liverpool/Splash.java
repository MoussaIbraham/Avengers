package Liverpool;

import javax.swing.*;
import java.awt.*;
public class Splash extends JWindow{

	private int duracion = 0;
	public Splash(int duracion) {
		this.duracion = duracion;
		this.setBackground(new Color(0, 0, 0, 128));
		JPanel panel = (JPanel) getContentPane();
		ImageIcon img = new ImageIcon("img/escudo.png");
		this.setBackground(null);
		panel.add(new JLabel(img), BorderLayout.CENTER);
		panel.setOpaque(false);
		panel.setBackground(new Color(0, 0, 0, 128));
		setSize(img.getIconWidth(), img.getIconHeight());
		setLocationRelativeTo(null);
		
		setVisible(true);

		
		try {
			Thread.sleep(duracion);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		setVisible(false);
	}
	

}
