package juego.control;

import juego.modelo.Celda;
import juego.modelo.Jugador;
import juego.modelo.Tablero;
import juego.util.CoordenadasIncorrectasException;

/**
 * Interface de arbitro.
 * 
 * @author Daniel Mellado Hurtado
 * @since 2.0
 * @version 1.0
 * 
 * @see ArbitroAtariGo
 */
public interface Arbitro {

	/**
	 * registrarJugadoresEnOrden.
	 * 
	 * @param nombre
	 *            nombre del jugador
	 */
	public void registrarJugadoresEnOrden(String nombre);

	/**
	 * obtenerJugadorConTurno.
	 * 
	 * @return Jugador
	 * 
	 */
	public Jugador obtenerJugadorConTurno();

	/**
	 * obtenerJugadorSinTurno.
	 * 
	 * @return Jugador
	 * 
	 */
	public Jugador obtenerJugadorSinTurno();

	/** cambiarTurno. */
	public void cambiarTurno();

	/**
	 * obtenerTablero.
	 * 
	 * @return tablero
	 * 
	 */
	public Tablero obtenerTablero();

	/**
	 * estaAcabado.
	 *
	 * @return true si esta acabado o false si no
	 */
	public boolean estaAcabado();

	/**
	 * obtenerGanador.
	 *
	 * @return jugador
	 *           
	 */
	public Jugador obtenerGanador();

	/**
	 * jugar.
	 * 
	 * @param celda
	 *            celda a colocar
	 * @throws CoordenadasIncorrectasException
	 */
	public void jugar(Celda celda) throws CoordenadasIncorrectasException;

	/**
	 * esMovimientoLegal.
	 * 
	 * @param celda
	 *            celda a colocar
	 * @return true si es movimiento legal false si no
	 */
	public boolean esMovimientoLegal(Celda celda);

}
