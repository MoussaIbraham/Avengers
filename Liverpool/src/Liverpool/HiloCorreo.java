package Liverpool;

import java.io.IOException;

public class HiloCorreo extends Thread{

	VentanaClientePOP3 frame;
	public HiloCorreo(VentanaClientePOP3 frame) {
		this.frame = frame;
	}
	
	public void run() {
		try {while(true) {
			sleep(30000);
			frame.receiveEmail();
		}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
