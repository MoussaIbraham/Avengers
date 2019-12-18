package Liverpool;


public class Main {

	static Modelo mimodelo = new Modelo();
	
	public static void main(String[] args) {
		Splash splash = new Splash(500);
		LoginWindow window = new LoginWindow(mimodelo);
		window.main(args);
	}
	
}
