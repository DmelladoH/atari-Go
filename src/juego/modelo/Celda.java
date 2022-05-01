package juego.modelo;

/**
 * Clase de las celdas de un tablero.
 * 
 * @author Daniel Mellado Hurtado.
 * 
 * @since 1.0
 * @version 1.0
 * 
 */
public class Celda {

	/** fila. */
	private int fila;
	/** columna. */
	private int columna;
	/** piedra. */
	private Piedra piedra;

	/**
	 * Constructor.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param fila
	 *            fila
	 * @param columna
	 *            columna
	 */
	public Celda(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;

	}

	/**
	 * Método que obtiene la piedra asignada a una celda.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return piedra piedra
	 */
	public Piedra obtenerPiedra() {
		return this.piedra;
	}

	/**
	 * Método que devuelve el color de la piedra.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return color de la piedra asignada a la celda
	 * 		   null si la piedra no esta viva
	 */
	public Color obtenerColorDePiedra() {
		if (!estaVacia()) {
			return piedra.obtenerColor();
		} else {
			return null;
		}
	}

	/**
	 * Método que establece en la celda una piedra.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param piedra
	 *            piedra
	 */
	public void establecerPiedra(Piedra piedra) {
		this.piedra = piedra;
	}

	/**
	 * Valida si una celda esta vida o no.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return true si esta vida, false si no lo esta
	 */
	public boolean estaVacia() {
		return obtenerPiedra() == null;
	}

	/**
	 * Método que devuelve el número de la fila de la celda dentro del tablero.
	 *
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return fila fila
	 */
	public int obtenerFila() {
		return fila;
	}

	/**
	 * Método que devuelce el número de la columna de la celda dentro del tablero.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return columna columna
	 */
	public int obtenerColumna() {
		return columna;
	}

	/**
	 * valida si la celda de la clase tiene las mismas coordenadas a la pasada por
	 * parámetro.
	 *
	 * @since 1.0
	 * @version 1.0
	 *
	 * @param otra
	 *            celda
	 * @return true, si las dos celdas son iguales, false si no
	 */
	public boolean tieneIgualesCoordenadas(Celda otra) {

		if (obtenerColumna() == otra.obtenerColumna() && obtenerFila() == otra.obtenerFila()) {
			return true;
		}

		return false;
	}

	/**
	 * Metododo que elminina la piedra asignada a la celda.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 */
	public void eliminarPiedra() {
		piedra = null;
	}

	/**
	 * Sobreescribe el metodo toString e imprime las coordenadas de la celda.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 */
	@Override
	public String toString() {
		return "(" + obtenerFila() + "/" + obtenerColumna() + ")";
	}

}
