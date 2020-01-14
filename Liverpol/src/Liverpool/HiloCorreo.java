package Liverpool;

import java.io.IOException;

import javax.swing.JButton;

public class HiloCorreo extends Thread{

	POP3 frame;
	public HiloCorreo(POP3 correos) {
		this.frame = correos;
	}

	public void run() {
		try {while(true) {
			sleep(15000);
			frame.receiveEmail();
			sleep(15000);
			
		}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
