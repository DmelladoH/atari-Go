package juego.control;

import java.util.ArrayList;

import juego.modelo.Celda;
import juego.modelo.Color;
import juego.modelo.Grupo;
import juego.modelo.Jugador;
import juego.modelo.Tablero;
import juego.util.CoordenadasIncorrectasException;

/**
 * Clase abstracta que controla el curso de la partida.
 * <P>
 * Esta clase se encarga de que las jugadas realizadas por los jugadores sean
 * válidas.
 * 
 * @author Daniel Mellado Hurtado.
 * @since 1.0
 * @version 2.0
 * 
 * @see ArbitroAtariGoAvanzado ArbitroAtariGoBasico
 *
 */
public abstract class ArbitroAtariGo implements Arbitro {

	/** tablero. */
	protected Tablero tablero;

	/** jugadorId. */
	private static int jugadorId = 0;

	/** turno. */
	private Jugador turno;

	/** J1. */
	private Jugador J1;

	/** J2. */
	private Jugador J2;

	/**
	 * Constructor.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * @param tablero
	 *            tablero
	 */
	public ArbitroAtariGo(Tablero tablero) {
		this.tablero = tablero;
	}

	/**
	 * Método que registra en primer lugar al jugador las piezas negras y en segundo
	 * lugar al de las blancas.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param nombre
	 *            nombre
	 * 
	 */
	public void registrarJugadoresEnOrden(String nombre) {

		switch (jugadorId) {

		case 0:
			J1 = new Jugador(nombre, Color.NEGRO);
			turno = J1;
			break;
		case 1:
			J2 = new Jugador(nombre, Color.BLANCO);
			break;
		default:
			break;
		}

		jugadorId++;
	}

	/**
	 * Método que devuelve el jugador que tiene el turno actual.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return jugador
	 */
	public Jugador obtenerJugadorConTurno() {
		return turno;
	}

	/**
	 * Método que devuelve el juegador que no tiene el turno actual.
	 *
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return jugador
	 */
	public Jugador obtenerJugadorSinTurno() {

		if (turno == J1) {
			return J2;
		} else if (turno == J2) {
			return J1;
		}
		return null;

	}

	/**
	 * Método que avanza el turno según qué jugador ha realizado el último
	 * movimiento.
	 *
	 * @since 1.0
	 * @version 1.0
	 */
	public void cambiarTurno() {

		if (turno == J1) {
			turno = J2;
		} else if (turno == J2) {
			turno = J1;
		}
	}

	/**
	 * Método que devuelve el tablero.
	 *
	 * @since 1.0
	 * @version 1.0
	 *
	 * @return tablero
	 */
	public Tablero obtenerTablero() {
		return tablero;
	}

	/**
	 * Método que permite consultar si el juego está acabado. El juego finaliza
	 * cuando un jugador ha realizado una captura sobre el contrario.
	 *
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @return true si el juego se ha acabado, false si no
	 */

	public abstract boolean estaAcabado();

	/**
	 * Método que informa del jugador ganador.
	 *
	 * @since 1.0
	 * @version 1.0
	 *
	 * @return jugador o null si aún no ha habido un ganador
	 */

	public Jugador obtenerGanador() {

		if (estaAcabado()) {
			return obtenerJugadorSinTurno();
		}
		return null;
	}

	/**
	 * Método que coloca una pieza ya validada, actualiza los grupos en el método
	 * colocar y cambia de turno.
	 *
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param celda
	 *            celda
	 * @throws CoordenadasIncorrectasException
	 */
	public void jugar(Celda celda) throws CoordenadasIncorrectasException {

		if (!tablero.estaEnTablero(celda.obtenerFila(), celda.obtenerColumna())) {
			throw new CoordenadasIncorrectasException("Las coordenadas de la celda no pertenecen a tablero");
		} else {

			tablero.colocar(obtenerJugadorConTurno().generarPiedra(), tablero.obtenerCeldasConMismasCoordenadas(celda));

			cambiarTurno();
		}

	}

	/**
	 * Método que valida si un movimiento es correcto o no.
	 * <p>
	 * Comprueba si la celda esta en el tablero, si la posición de la celda ya esta
	 * ocupada y si ha habido un suicidio.
	 *
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @param celda
	 *            celda
	 * @return true si el movimiento es correcto, false si no
	 */
	public boolean esMovimientoLegal(Celda celda) {

		try {
			Tablero tableroCopia = tablero.generaCopia();
			ArrayList<Grupo> gruposJugador = tableroCopia.obtenerGruposDelJugador(obtenerJugadorConTurno());

			Celda celdaCopia = tableroCopia.obtenerCeldasConMismasCoordenadas(celda);

			// Comprueba si la celda esta en el tablero y si la celda no este ocupada ya
			if (!(tableroCopia.estaEnTablero(celdaCopia.obtenerFila(), celdaCopia.obtenerColumna()))
					|| !celdaCopia.estaVacia()) {
				return false;

			} else {

				tableroCopia.colocar(obtenerJugadorConTurno().generarPiedra(), celdaCopia);

				// comprueba si al suicidarse consigue matar

				if (suicidioPermitido(tableroCopia)) {
					return true;
				} else {
					for (int i = 0; i < gruposJugador.size(); i++) {

						if (!(gruposJugador.get(i)).estaVivo()) {
							return false;
						}

					}
				}
			}
			return true;

		} catch (CoordenadasIncorrectasException e) {
			throw new RuntimeException("Error al validar si un movimiento es legal", e);
		}
	}

	/**
	 * Método que valida si un suicidio esta permitido o no.
	 * <p>
	 * Un suicidio está permitido si al colocar la ficha consigue matar a algún
	 * grupo del contrario.
	 *
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param tableroCopia
	 *            tableroCopia
	 * @return true si el suicidio esta permitido, false si no lo esta
	 */
	private boolean suicidioPermitido(Tablero tableroCopia) {

		ArrayList<Grupo> gruposContrario = tableroCopia.obtenerGruposDelJugador(obtenerJugadorSinTurno());

		for (int i = 0; i < gruposContrario.size(); i++) {
			if (!(gruposContrario.get(i)).estaVivo()) {
				return true;
			}
		}
		return false;
	}
}