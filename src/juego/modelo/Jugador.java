package juego.modelo;

/**
 * Clase jugador.
 * 
 * @author Daniel Mellado Hurtado.
 * 
 * @since 1.0
 * @version 1.0
 *
 */
public class Jugador {

	/** nombre. */
	private String nombre;
	/** color. */
	private Color color;

	/**
	 * Constructor.
	 *
	 * @since 1.0
	 * @version 1.0
	 *
	 * @param nombre
	 *            nombre
	 * @param color
	 *            color
	 */
	public Jugador(String nombre, Color color) {
		this.nombre = nombre;
		this.color = color;
	}

	/**
	 * Método que devuelve el color del jugador.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return color
	 */
	public Color obtenerColor() {
		return color;
	}

	/**
	 * Método que devuelve el nombre del jugador.
	 *
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return nombre
	 */
	public String obtenerNombre() {
		return nombre;
	}

	/**
	 * Método que genera una pidra acorde con el color del jugador.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * @return piedra
	 */
	public Piedra generarPiedra() {
		return new Piedra(obtenerColor());
	}

	/**
	 * Sobreescribe el metodo toString e imprime el nombre y el color.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 */
	@Override
	public String toString() {
		return obtenerNombre() + "/" + obtenerColor();
	}

}
