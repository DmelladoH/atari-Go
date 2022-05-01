package juego.modelo;

import java.util.ArrayList;

import juego.util.CoordenadasIncorrectasException;

/**
 * Clase de grupos de celdas de un mismo jugador.
 * 
 * @author Daniel Mellado Hurtado.
 * 
 * @since 1.0
 * @version 1.0
 *
 */
public class Grupo {

	/** grupo. */
	private ArrayList<Celda> grupo;
	/** tablero. */
	private Tablero tablero;
	/** contId. */
	private static int contId = 0;
	/** Id. */
	private int Id;

	/**
	 * Constructor.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param celda
	 *            celda
	 * @param tablero
	 *            tablero
	 */
	public Grupo(Celda celda, Tablero tablero) {

		grupo = new ArrayList<Celda>();
		this.tablero = tablero;

		grupo.add(celda);

		this.Id = contId;
		contId++;
	}

	/**
	 * Método que devuelve la id del grupo.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return id id
	 */
	public int obtenerId() {
		return Id;
	}

	/**
	 * Método que devuelve el color del grupo.
	 * <p>
	 * devuelve el color de la primera celda del grupo.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return color o null si el grupo no esta vivo
	 */
	public Color obtenerColor() {

		if (estaVivo()) {
			return grupo.get(0).obtenerColorDePiedra();
		} else {
			return null;
		}

	}

	/**
	 * Método que valida si un grupo esta vivo o muerto.
	 * 
	 * @since 1.0
	 * @version 2.0
	 * 
	 * @return true si esta vivo, false si esta muerto
	 */
	public boolean estaVivo() {

		try {
			int gradosLibertad = 0;

			for (int i = 0; i < obtenerTamaño(); i++) {
				gradosLibertad += tablero.obtenerGradosDeLibertad(grupo.get(i));
			}

			if (gradosLibertad > 0) {
				return true;
			} else {
				return false;
			}
		} catch (CoordenadasIncorrectasException e) {
			throw new RuntimeException("Error al validar si un grupo esta vivo", e);
		}
	}

	/**
	 * Método que devuelve el tamaño del grupo.
	 * <p>
	 * Devuelve la cantidad de celdas que contiene el grupo.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @return tamaño
	 */
	public int obtenerTamaño() {
		return grupo.size();
	}

	/**
	 * Método que valida si una celda pasada por parámetro se encuetra en el grupo.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param celda
	 *            celda
	 * @return true si la celda esta en el grupo, false si no
	 */
	public boolean contiene(Celda celda) {

		for (int i = 0; i < this.obtenerTamaño(); i++) {
			if (celda == grupo.get(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método que añade un grupo al grupo de la celda.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param otroGrupo
	 *            grupo
	 */
	public void añadirCeldas(Grupo otroGrupo) {

		for (int i = 0; i < otroGrupo.obtenerTamaño(); i++) {
			grupo.add(otroGrupo.grupo.get(i));
		}
	}

	/**
	 * Método que elimina todas las piedras del grupo.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 */
	public void eliminarPiedras() {

		for (int i = 0; i < obtenerTamaño(); i++) {

			grupo.get(i).eliminarPiedra();
		}

	}

	/**
	 * Método que devuelve una copia del grupo de la celda en un tablero pasado por
	 * parámetro.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 * @param otroTablero
	 *            tablero
	 * @return grupo
	 */
	public Grupo generarCopiaEnOtroTabrero(Tablero otroTablero) {

		Grupo grupoCopia = new Grupo(grupo.get(0), otroTablero);

		for (int i = 1; i < obtenerTamaño(); i++) {

			grupoCopia.grupo.add(grupo.get(i));

		}

		return grupoCopia;
	}

	/**
	 * Sobreescribe el metodo toString e imprime las celdas del grupo.
	 * 
	 * @since 1.0
	 * @version 1.0
	 * 
	 */
	@Override
	public String toString() {

		String ret = "[";

		for (int i = 0; i < obtenerTamaño(); i++) {
			ret = ret.concat(grupo.get(i).toString());
		}

		ret = ret.concat("]");

		return ret;
	}

}
