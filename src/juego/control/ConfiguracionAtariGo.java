package juego.control;

/**
 * Configuración de la Atari Go. 
 * 
 * @author Daniel Mellado Hurtado.
 * 
 * @since 2.0
 * @version 1.0
 * 
 */
public class ConfiguracionAtariGo {

	/** TAMAÑO_POR_DEFECTO.*/
	public static final int TAMAÑO_POR_DEFECTO = 9;
	
	/** TAMAÑOS. */
	private static final int[] TAMAÑOS = {9,13,19};
	
	/** LETRA_NO_UTILIZADA. */
	public static final char LETRA_NO_UTILIZADA = 'I';
	
	/** MINIMO_CAPTURAS. */
	public static final int MINIMO_CAPTURAS = 2;
	
	/** MAXIMO_CAPTURAS. */
	public static final int MAXIMO_CAPTURAS = 10;
	
	/**
	 * Constructor.
	 *
	 * @since 2.0
	 * @version 1.0
	 */
	private ConfiguracionAtariGo() {
	}

	/**
	 * Método que devuelve el tamaño máximo del tablero. 
	 * 
	 * @since 2.0
	 * @version 1.0
	 * 
	 * @return TAMAÑO_POR_DEFECTO.
	 */
	public static int obtenerTamañoMaximo() {
		return TAMAÑOS[TAMAÑOS.length - 1];
	}
	
	/**
	 * Método que valida si el tamaño introducido por parámetro esta entre los tamaños permitidos.
	 * 
	 * @since 2.0
	 * @version 1.0
	 *
	 * @param tamañoSugerido
	 * 						tamañoSugerido.
	 * @return true si la el tamaño es válido, false si no.
	 */
	public static boolean esTamañoValido(int tamañoSugerido) {
		
		boolean valido = false;
		
		for(int i = 0; i < TAMAÑOS.length; i++) {
			
			if(TAMAÑOS[i] == tamañoSugerido) {
				valido = true;
			}
		}
		
		return valido;
	}
	
	/**
	 * Método que devuelve la información de ayuda con los valores la clase.
	 *
	 * @since 2.0
	 * @version 1.0
	 * 
	 * @return ayuda.
	 */
	public static String generarAyuda() {
		
		String ret = "Tamaño máximo: " + obtenerTamañoMaximo() + "\nTamaños permitidos: [";
	
		for(int i = 0; i < TAMAÑOS.length; i++) {
			ret = ret.concat(" " + TAMAÑOS[i]);
		}
		ret = ret.concat(" ] "+ "\nLetra no utilizada: "+ LETRA_NO_UTILIZADA);
		
		return ret;	
	}
	
}
