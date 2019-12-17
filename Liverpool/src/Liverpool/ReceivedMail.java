package Liverpool;

import javax.mail.Address;

public class ReceivedMail {
	
	


	public ReceivedMail(String transmitter, String subject, String bodyMail) {
		
		this.transmitter=transmitter;
		this.subject=subject;
		this.bodyMail=bodyMail;
	}

	private String bodyMail;
	private String subject;
	private String transmitter;
	
	
	public String getBodyMail() {
		return bodyMail;
	}
	public void setBodyMail(String bodyMail) {
		this.bodyMail = bodyMail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTransmitter() {
		return transmitter;
	}
	public void setTransmitter(String transmitter) {
		this.transmitter = transmitter;
	}
}