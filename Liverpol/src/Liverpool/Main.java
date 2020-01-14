package Liverpool;


/**
 * @author Avengers
 * @version 1.0
 * Clase donde se ejecutara el programa.
 */
public class Main {

	static Modelo mimodelo = new Modelo();

	/**
	 * @param args
	 * Lanza la app tras la visualización del Splash
	 */
	public static void main(String[] args) {
		Splash splash = new Splash(2000);
		VentanaMenu menu = new VentanaMenu(mimodelo, args);
		menu.main(args);
	}

}
