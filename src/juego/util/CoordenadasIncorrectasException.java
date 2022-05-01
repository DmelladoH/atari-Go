package juego.util;

/**
 * Excepcion.
 * <p>
 * Excepcion que salta al encontrarse con una celda que no se encuentra en el
 * tablero.
 * 
 * @author Daniel Mellado Hurtado
 * 
 * @since 2.0
 * @version 1.0
 *
 *
 */

public class CoordenadasIncorrectasException extends Exception {

	/**
	 * Constructor sin argumentos.
	 */
	public CoordenadasIncorrectasException() {
	}

	/**
	 * Constructor con texto descriptivo.
	 * 
	 * @param args0
	 *            texto descriptivo.
	 */
	public CoordenadasIncorrectasException(String args0) {
		super(args0);
	}

	/**
	 * Constructor con excepción encadenada.
	 * 
	 * @param args0
	 *            excepción encadenada.
	 */
	public CoordenadasIncorrectasException(Throwable args0) {
		super(args0);
	}

	/**
	 * Constructor con texto descriptivo y excepcion encadenada.
	 * 
	 * @param args0
	 *            texto descriptivo.
	 * @param args1
	 *            excepción encadenada.
	 */
	public CoordenadasIncorrectasException(String args0, Throwable args1) {
		super(args0, args1);
	}

	/**
	 * 
	 * Constructor con un texto descriptivo, una excepción encadenada y dos
	 * booleanos.
	 * 
	 * @param args0
	 *            texto descriptiovo.
	 * @param args1
	 *            excepcion encadenda.
	 * @param args2
	 *            boolean 1.
	 * @param args3
	 *            boolean 2.
	 */
	public CoordenadasIncorrectasException(String args0, Throwable args1, boolean args2, boolean args3) {
		super(args0, args1, args2, args3);
	}
}
