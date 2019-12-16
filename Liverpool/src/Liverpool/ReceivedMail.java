package Liverpool;

public class ReceivedMail {
	
	public ReceivedMail() {}
	
	public ReceivedMail(String transmitter, String subject, String bodyMail, String receivedDate) {
		super();
		this.bodyMail = bodyMail;
		this.subject = subject;
		this.transmitter = transmitter;
		this.receivedDate = receivedDate;
	}

	private String bodyMail;
	private String subject;
	private String transmitter;
	private String receivedDate;
	
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
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	
	
	
	

}
