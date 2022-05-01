package juego.control;

import juego.modelo.Tablero;

/**
 * Arbitro AtariGo avanzado.
 * <p>
 * La opción avanzada permite que haya que pueda haber más de una captura.
 *
 * @author Daniel Mellado Hurtado
 * 
 * @since 2.0
 * @version 1.0
 * 
 */

public class ArbitroAtariGoAvanzado extends ArbitroAtariGo {

	/** cotaNumeroCapturas. */
	private int cotaNumeroCapturas;

	/**
	 * Constructor.
	 *
	 * @since 2.0
	 * @version 1.0
	 * 
	 * @param tablero
	 *            tablero
	 * @param cotaNumeroCapturas
	 *            cotaNumeroCapturas
	 */
	public ArbitroAtariGoAvanzado(Tablero tablero, int cotaNumeroCapturas) {
		super(tablero);
		this.cotaNumeroCapturas = cotaNumeroCapturas;
	}

	/**
	 * Metodo que estableze si la partida se ha acabado.
	 * <p>
	 * La partida se acaba al alcanzar un número de capturas.
	 * 
	 * @since 2.0
	 * @version 1.0
	 * 
	 * @return true si la partida esta acabada, false si no.
	 */
	@Override
	public boolean estaAcabado() {

		if (tablero.obtenerNumeroPiedrasCapturadas(obtenerJugadorConTurno().obtenerColor()) >= cotaNumeroCapturas) {
			return true;
		}
		return false;
	}
}
