package Liverpool;


public class Main {

	static Modelo mimodelo = new Modelo();
	
	public static void main(String[] args) {
		LoginWindow window = new LoginWindow(mimodelo);
		window.main(args);
		System.out.println("hello 2");
	}
	
}
