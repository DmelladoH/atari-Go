package juego.modelo;

import java.util.ArrayList;
import java.util.List;
import juego.util.CoordenadasIncorrectasException;
import juego.util.Sentido;

/**
 * Clase Tablero.
 * 
 * @author Daniel Mellado Hurtado.
 * 
 * @since 1.0
 * @version 2.0
 *
 */
public class Tablero {

	/** matriz. */
	private List<List<Celda>> matriz;

	/** gruposJ1. */
	private ArrayList<Grupo> gruposJ1;

	/** gruposJ2. */
	private ArrayList<Grupo> gruposJ2;

	/** piedrasCapturadasJ1. */
	private ArrayList<Grupo> piedrasCapturadasJ1;

	/** piedrasCapturadasJ2. */
	private ArrayList<Grupo> piedrasCapturadasJ2;

	/** g. */
	private Grupo g;

	/**
	 * Constructor.
	 * 
	 * @param filas
	 *            filas
	 * @param columnas
	 *            columnas
	 */
	public Tablero(int filas, int columnas) {

		matriz = new ArrayList<>();

		gruposJ1 = new ArrayList<>();
		gruposJ2 = new ArrayList<>();

		piedrasCapturadasJ1 = new ArrayList<>();
		piedrasCapturadasJ2 = new ArrayList<>();

		for (int i = 0; i < filas; i++) {
			ArrayList<Celda> f = new ArrayList<>();
			for (int j = 0; j < columnas; j++) {
				f.add(new Celda(i, j));
			}
			matriz.add(f);
		}
	}

	/**
	 * Método que coloca una piedra en una celda.
	 * 
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @param piedra
	 *            piedra
	 * @param celda
	 *            celda
	 * @throws CoordenadasIncorrectasException
	 */
	public void colocar(Piedra piedra, Celda celda) throws CoordenadasIncorrectasException {

		if (!estaEnTablero(celda.obtenerFila(), celda.obtenerColumna())) {
			throw new CoordenadasIncorrectasException("La celda no pertenece al tablero");
		} else {

			Celda c = obtenerCeldasConMismasCoordenadas(celda);
			c.establecerPiedra(piedra);
			piedra.colocarEn(c);

			g = new Grupo(c, this);

			unirAGrupo(c, g);

			if (piedra.obtenerColor() == Color.NEGRO) {
				capturarGrupo(Color.BLANCO);

			} else {
				capturarGrupo(Color.NEGRO);

			}
		}
	}

	/**
	 * Método que une un grupo a sus adyacentes.
	 * 
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @param celda
	 *            celda
	 * @param g
	 *            grupo
	 */

	private void unirAGrupo(Celda celda, Grupo g) {

		try {

			Grupo gJugada = new Grupo(celda, this);

			ArrayList<Grupo> gruposJugador = obtenerGruposDelJugador(celda.obtenerColorDePiedra());
			ArrayList<Celda> celdasAdyacentes = obtenerCeldasAdyacentes(celda);

			Celda celdaAdy;
			Grupo gru;

			for (int i = 0; i < celdasAdyacentes.size(); i++) {
				celdaAdy = obtenerCeldasConMismasCoordenadas(celdasAdyacentes.get(i));

				if (!celdaAdy.estaVacia()) {

					if (celdaAdy.obtenerColorDePiedra() == celda.obtenerColorDePiedra()) {

						for (int j = 0; j < gruposJugador.size(); j++) {
							gru = gruposJugador.get(j);

							if (gru.contiene(celdaAdy)) {

								gJugada.añadirCeldas(gru);
								borrarGrupo(j, celda.obtenerPiedra().obtenerColor());

							}
						}
					}
				}
			}
			colocarEnGrupo(celda.obtenerPiedra(), gJugada);
		} catch (CoordenadasIncorrectasException e) {
			throw new RuntimeException("Error al unir un grupo", e);
		}
	}

	/**
	 * Método que borra el grupo de una determinado posición.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param posicion
	 *            posicion
	 * @param piedra
	 *            piedra
	 */
	private void borrarGrupo(int posicion, Color color) {

		if (color == Color.NEGRO) {
			gruposJ1.remove(posicion);
		} else {
			gruposJ2.remove(posicion);
		}
	}

	/**
	 * Método que captura los grupos.
	 * 
	 * @since 2.0
	 * @version 1.0
	 * 
	 * @param color
	 */
	private void capturarGrupo(Color color) {

		ArrayList<Grupo> grupos = obtenerGruposDelJugador(color);
		ArrayList<Grupo> piedrasCap = obtenerPiedrasCapturadaJugador(color);

		for (int i = 0; i < grupos.size(); i++) {
			if (!grupos.get(i).estaVivo()) {
				piedrasCap.add(grupos.get(i));
			}
		}

		for (int j = 0; j < grupos.size(); j++) {
			for (int k = 0; k < piedrasCap.size(); k++) {
				if (grupos.get(j).equals(piedrasCap.get(k))) {
					grupos.get(j).eliminarPiedras();
					borrarGrupo(j, color);
				}
			}
		}
	}

	/**
	 * Método que coloca un grupo en los grupos de los jugadores.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param piedra
	 *            piedra
	 * @param grupo
	 *            grupo
	 */

	private void colocarEnGrupo(Piedra piedra, Grupo grupo) {

		if (piedra.obtenerColor() == Color.NEGRO) {
			gruposJ1.add(grupo);
		} else if (piedra.obtenerColor() == Color.BLANCO) {
			gruposJ2.add(grupo);
		}
	}

	/**
	 * Método que devuelve una celda dentro del tablero.
	 * 
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @param fila
	 *            fila
	 * @param columna
	 *            columna
	 * 
	 * @return celda o null si la celda no esta en el tablero
	 * @throws CoordenadasIncorrectasException
	 */
	public Celda obtenerCelda(int fila, int columna) throws CoordenadasIncorrectasException {

		if (!estaEnTablero(fila, columna)) {
			throw new CoordenadasIncorrectasException("Las coordenadas de la celda no pertenecen a tablero");
		} else {
			return matriz.get(fila).get(columna);

		}
	}

	/**
	 * Método que valida si unas coordenadas pasadas por parámetro se encuentran en
	 * el tablero.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param fila
	 *            fila
	 * @param columna
	 *            columna
	 * @return true si las coordenadas están en el tablero, false si no
	 */

	public boolean estaEnTablero(int fila, int columna) {
		if (fila >= 0 && fila <= obtenerNumeroFilas() - 1 && columna >= 0 && columna <= obtenerNumeroColumnas() - 1) {
			return true;
		} else
			return false;
	}

	/**
	 * Método que devuelve una celda del tablero con las mismas coordenadas a una de
	 * pasada por parámetro.
	 *
	 * @since 1.0
	 * @version 2.0
	 *
	 * @param celda
	 *            celda
	 * @return celda
	 * @throws CoordenadasIncorrectasException
	 */
	public Celda obtenerCeldasConMismasCoordenadas(Celda celda) throws CoordenadasIncorrectasException {

		if (!estaEnTablero(celda.obtenerFila(), celda.obtenerColumna())) {
			throw new CoordenadasIncorrectasException("Las coordenadas de la celda no pertenecen a tablero");
		} else {
			return obtenerCelda(celda.obtenerFila(), celda.obtenerColumna());
		}
	}

	/**
	 * Métpdo que devuelve el número de piedras que se encuentran en el tablero.
	 * 
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @param color
	 *            color
	 * @return numeroPiedra
	 */
	public int obtenerNumeroPiedras(Color color) {

		try {
			int cont = 0;
			for (int i = 0; i < obtenerNumeroFilas(); i++) {
				for (int j = 0; j < obtenerNumeroColumnas(); j++) {
					if (!obtenerCelda(i, j).estaVacia() && obtenerCelda(i, j).obtenerPiedra().obtenerColor() == color) {
						cont++;
					}
				}
			}
			return cont;
		} catch (CoordenadasIncorrectasException e) {
			throw new RuntimeException("Error al obtener el numero de piedras", e);
		}
	}

	/**
	 * Método que devuelve el número de filas del tablero.
	 * 
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @return filas filas
	 */
	public int obtenerNumeroFilas() {
		return matriz.size();
	}

	/**
	 * Método que devuelve el número de columnas del tablero.
	 * 
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @return columnas
	 */
	public int obtenerNumeroColumnas() {
		return matriz.get(0).size();
	}

	/**
	 * Método que valida si el tablero esta lleno de piedras.
	 * 
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @return true si esta completo, false si no lo esta
	 */
	public boolean estaCompleto() {
		try {

			for (int i = 0; i < obtenerNumeroFilas(); i++) {
				for (int j = 0; j < obtenerNumeroColumnas(); j++) {
					if (obtenerCelda(i, j).estaVacia()) {
						return false;
					}
				}
			}
			return true;
		} catch (CoordenadasIncorrectasException e) {
			throw new RuntimeException("Error al validar si un tablero esta completo", e);
		}
	}

	/**
	 * Método que devuelve un tablero igual e independiente al tablero de la clase.
	 * 
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @return tablero
	 */
	public Tablero generaCopia() {

		try {

			Tablero tableroCopia = new Tablero(this.obtenerNumeroFilas(), this.obtenerNumeroColumnas());

			for (int i = 0; i < obtenerNumeroFilas(); i++) {
				for (int j = 0; j < obtenerNumeroColumnas(); j++) {

					if (!this.obtenerCelda(i, j).estaVacia()) {

						tableroCopia.colocar(this.obtenerCelda(i, j).obtenerPiedra(), tableroCopia.obtenerCelda(i, j));
					}
				}

			}
			return tableroCopia;

		} catch (CoordenadasIncorrectasException e) {
			throw new RuntimeException("Error al generar una copia del tablero", e);
		}
	}

	/**
	 * Método que devuelve la lista de grupos de un mismo jugador.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param jugador
	 *            jugador
	 * 
	 * @return grupo grupos del jugador
	 */
	public ArrayList<Grupo> obtenerGruposDelJugador(Jugador jugador) {

		if (jugador.obtenerColor() == Color.NEGRO) {
			return gruposJ1;
		} else {
			return gruposJ2;
		}
	}

	/**
	 * Método que devuelve la lista de grupos de un mismo jugador.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param color
	 *            color del jugador
	 * @return grupo grupos del jugador
	 */
	private ArrayList<Grupo> obtenerGruposDelJugador(Color color) {

		if (color == Color.NEGRO) {
			return gruposJ1;
		} else {
			return gruposJ2;
		}
	}

	/**
	 * Método que halla el número de grados de libertad de una celda en el tablero.
	 * 
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @param celda
	 *            celda
	 * @return gradosDeLibertad
	 * @throws CoordenadasIncorrectasException
	 */
	public int obtenerGradosDeLibertad(Celda celda) throws CoordenadasIncorrectasException {

		if (!estaEnTablero(celda.obtenerFila(), celda.obtenerColumna())) {
			throw new CoordenadasIncorrectasException("Las coordenadas de la celda no pertenecen a tablero");
		} else {

			int gradosDeLibertad = 0;

			ArrayList<Celda> celdasAdy = obtenerCeldasAdyacentes(celda);

			for (int i = 0; i < celdasAdy.size(); i++) {

				if (obtenerCeldasConMismasCoordenadas(celdasAdy.get(i)).estaVacia()) {
					gradosDeLibertad++;
				}
			}

			return gradosDeLibertad;
		}
	}

	/**
	 * Método que devuelve las celdas adyacentes a una celda.
	 * 
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @param celda
	 *            celda
	 * @return adyacentes
	 * @throws CoordenadasIncorrectasException
	 */

	public ArrayList<Celda> obtenerCeldasAdyacentes(Celda celda) throws CoordenadasIncorrectasException {

		if (!estaEnTablero(celda.obtenerFila(), celda.obtenerColumna())) {
			throw new CoordenadasIncorrectasException("Las coordenadas de la celda no pertenecen a tablero");
		} else {

			ArrayList<Celda> adyacentes = new ArrayList<>();

			Celda cNorte = new Celda(celda.obtenerFila() + Sentido.NORTE.obtenerDesplazamientoHorizontal(),
					celda.obtenerColumna() + Sentido.NORTE.obtenerDesplazamientoVertical());

			Celda cEste = new Celda(celda.obtenerFila() + Sentido.ESTE.obtenerDesplazamientoHorizontal(),
					celda.obtenerColumna() + Sentido.ESTE.obtenerDesplazamientoVertical());
			Celda cSur = new Celda(celda.obtenerFila() + Sentido.SUR.obtenerDesplazamientoHorizontal(),
					celda.obtenerColumna() + Sentido.SUR.obtenerDesplazamientoVertical());
			Celda cOeste = new Celda(celda.obtenerFila() + Sentido.OESTE.obtenerDesplazamientoHorizontal(),
					celda.obtenerColumna() + Sentido.OESTE.obtenerDesplazamientoVertical());

			if (estaEnTablero(cNorte.obtenerFila(), cNorte.obtenerColumna())) {
				adyacentes.add(cNorte);
			}

			if (estaEnTablero(cEste.obtenerFila(), cEste.obtenerColumna())) {
				adyacentes.add(cEste);
			}

			if (estaEnTablero(cSur.obtenerFila(), cSur.obtenerColumna())) {
				adyacentes.add(cSur);
			}

			if (estaEnTablero(cOeste.obtenerFila(), cOeste.obtenerColumna())) {
				adyacentes.add(cOeste);
			}

			return adyacentes;
		}
	}

	/**
	 * Método que devuelve el número de piedras capturada de un jugador.
	 * 
	 * @since 2.0
	 * @version 1.0
	 * 
	 * @param color
	 *            color
	 * @return tamaño de piedrasCapturadasJ1 ó piedrasCapturadasJ2
	 */
	public int obtenerNumeroPiedrasCapturadas(Color color) {

		if (color == Color.NEGRO) {
			return piedrasCapturadasJ1.size();
		} else {
			return piedrasCapturadasJ2.size();
		}
	}

	/**
	 * Método que obtiene los grupos capturados de un jugador.
	 * 
	 * @since 2.0
	 * @version 1.0
	 * 
	 * @param color
	 * @return piedrasCapturadasJ1 ó piedrasCapturadasJ2
	 */
	private ArrayList<Grupo> obtenerPiedrasCapturadaJugador(Color color) {
		if (color == Color.NEGRO) {
			return piedrasCapturadasJ1;
		} else {
			return piedrasCapturadasJ2;
		}
	}

	/**
	 * Sobreescribe el metodo toString e imprime el tablero.
	 * 
	 * @since 1.0
	 * @version 2.0
	 */
	@Override
	public String toString() {

		try {
			String ret = "";

			for (int i = 0; i < obtenerNumeroFilas(); i++) {
				for (int j = 0; j < obtenerNumeroColumnas(); j++) {
					if (obtenerCelda(i, j).estaVacia()) {
						ret = ret.concat("-");
					} else {
						ret = ret
								.concat(Character.toString(obtenerCelda(i, j).obtenerPiedra().obtenerColor().toChar()));
					}
				}
				ret = ret.concat("\n");

			}

			return ret;
		} catch (CoordenadasIncorrectasException e) {
			throw new RuntimeException("Error al imprimir el tablero", e);
		}
	}

}