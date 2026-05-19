package paqueteCasino;

import javax.swing.JOptionPane;
import java.util.Set;

public class CasinoService {
	// ── Menú Principal
	// ──────────────────────────────────────────────────────────────────
	public String preguntarPaso() {
		Object[] opciones = { "Crear jugador", "Login", "Cancelar" };
		return (String) JOptionPane.showInputDialog(null, "¿Qué quieres hacer?", "Casino", JOptionPane.QUESTION_MESSAGE,
				null, opciones, opciones[0]);
	}

	// ── Menú de Jugador
	// ──────────────────────────────────────────────────────────────────
	public String preguntarPasoJugador(String nombre, Integer saldo) {
		Object[] opciones = { "Jugar caballo", "Cerrar Session" };
		return (String) JOptionPane.showInputDialog(null,
				"¿Qué quieres hacer, " + nombre + "?" + "\n" + "Tu saldo: $" + saldo, "Casino de " + nombre,
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
	}

	public Jugador crearJugador(Integer id) {
		String login = "";
		while (login.trim().isEmpty()) {
			login = JOptionPane.showInputDialog(null, "Ingresa login");
			if (login == null) {
				return null;
			}
			if (login.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Login inválido");
			}
		}

		String password = "";
		while (password.trim().isEmpty()) {
			password = JOptionPane.showInputDialog(null, "Ingresa password");
			if (password == null) {
				return null;
			}
			if (password.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Password inválido");
			}
		}

		String nombre = "";
		while (nombre.trim().isEmpty()) {
			nombre = JOptionPane.showInputDialog(null, "Ingresa nombre");
			if (nombre == null) {
				return null;
			}
			if (nombre.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nombre inválido");
			}
		}

		Jugador jugador = new Jugador(id, login, password, nombre);

		JOptionPane.showMessageDialog(null, "Nuevo Jugador creado:\n" + jugador);

		return jugador;
	}

	public Usuario login(Set<? extends Usuario> usuarios) {
		if (usuarios.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay usuarios registrados");
			return null;
		}

		String login = "";
		while (login.trim().isEmpty()) {
			login = JOptionPane.showInputDialog(null, "Ingresa login");
			if (login == null) {
				return null;
			}
			if (login.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Login inválido");
			}
		}

		String password = "";
		while (password.trim().isEmpty()) {
			password = JOptionPane.showInputDialog(null, "Ingresa password");
			if (password == null) {
				return null;
			}
			if (password.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Password inválido");
			}
		}

		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equalsIgnoreCase(login) && usuario.getPassword().equalsIgnoreCase(password)) {
				JOptionPane.showMessageDialog(null, "Login success");
				return usuario;
			}
		}
		JOptionPane.showMessageDialog(null, "No hay este usuario");
		return null;
	}

	public Jugador getJugador(Set<Jugador> jugadores, Integer id) {
		for (Jugador jugador : jugadores) {
			if (jugador.getId() == id) {
				JOptionPane.showMessageDialog(null, jugador.toString());
				return jugador;
			}
		}
		return null;
	}
}
