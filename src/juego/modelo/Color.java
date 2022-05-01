package juego.modelo;

/**
 * Color de una pidra.
 * 
 * @author Daniel Mellado Hurtado.
 * 
 * @since 1.0
 * @version 1.0
 *
 */
public enum Color {
	/** Blanco.*/
	BLANCO('O'),
	/** Negro.*/
	NEGRO('X');

	/** caracter.*/
	private char caracter;
	
	/**
	 * Constructor.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param caracter
	 * 				 caracter
	 */
	private Color(char caracter) {
		this.caracter = caracter;
	}
	
	/**
	 * Método que obtiene el carácter del color.
	 * 
	 * @since 1.0
	 * @version 1.0
	 *
	 * @return a char
	 */
	public char toChar() {
		return caracter;
	}
}
