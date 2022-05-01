package juego.control;

import juego.modelo.Tablero;

/**
 * Arbitro AtariGo básico.
 * <p>
 * La partida finaliza a una sola captura.
 *
 * @author Daniel Mellado Hurtado
 * 
 * @since 2.0
 * @version 1.0
 *
 */
public class ArbitroAtariGoBasico extends ArbitroAtariGo {

	/**
	 * Constructor.
	 * 
	 * @since 2.0
	 * @version 1.0
	 * 
	 * @param tablero
	 *            tablero
	 */
	public ArbitroAtariGoBasico(Tablero tablero) {
		super(tablero);
	}

	/**
	 * Metodo que estableze si la partida se ha acabado.
	 * <p>
	 * La partida se acaba al alcanzar una captura.
	 * 
	 * @since 2.0
	 * @version 1.0
	 * 
	 * @return true si la partida esta acabada, false si no.
	 */
	@Override
	public boolean estaAcabado() {

		if (tablero.obtenerNumeroPiedrasCapturadas(obtenerJugadorConTurno().obtenerColor()) != 0) {
			return true;
		} else
			return false;
	}
}
