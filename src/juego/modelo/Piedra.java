package juego.modelo;

/**
 * Clase piedra.
 * 
 * @author Daniel Mellado Hurtado.
 * @version 1.0
 *
 */
public class Piedra {

	/** color. */
	private Color color;
	/** celda. */
	private Celda celda;

	/**
	 * Constructor.
	 * 
	 * @param color
	 *            color
	 */
	public Piedra(Color color) {
		this.color = color;
	}

	/**
	 * Método que devuelve el color de la piedra.
	 * 
	 * @return color
	 */
	public Color obtenerColor() {
		return color;
	}

	/**
	 * Método que coloca una celda y la vincula con la piedra.
	 * 
	 * @param celda
	 *            celda
	 */
	public void colocarEn(Celda celda) {
		this.celda = celda;
	}
	/**
	 * Devuelve la celda correspondiente a una piedra.
	 * 
	 * @return celda 
	 * 				celda
	 */
	public Celda obtenerCelda() {
		return celda;
	}

	/**
	 * Sobreescribe el metodo toString e imprime la celda y el color de la piedra.
	 */
	@Override
	public String toString() {
		return celda + "/" + color;
	}
}
