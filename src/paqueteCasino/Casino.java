package paqueteCasino;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class Casino {
	public static void main(String[] args) {
		CasinoService service = new CasinoService();
		CasinoData casinoData = new CasinoData(10000, 500);
		
		CaballoJuego caballoJuego = new CaballoJuego();

		Set<Jugador> jugadores = new HashSet<>();
		Administrador administrador = new Administrador(0, "login", "password");

		Integer usuarioLoginSuccessId;

		boolean deseaOtraOperacion = true;

		JOptionPane.showMessageDialog(null, "Bienvenido a Casino");

		while (deseaOtraOperacion) {
			String paso = service.preguntarPaso();

			if (paso == null) {
				deseaOtraOperacion = false;
				JOptionPane.showMessageDialog(null, "Chao!");
				break;
			}

			switch (paso) {
			case "Crear jugador":
				Jugador nuevoJugador = service.crearJugador(jugadores.size() + 1);

				if (nuevoJugador != null) {
					jugadores.add(nuevoJugador);
				}
				break;
			case "Login":
				Set<Usuario> totalUsuarios = new HashSet<>();
				totalUsuarios.addAll(jugadores);
				totalUsuarios.add(administrador);

				Usuario usuarioDeLogin = service.login(totalUsuarios);

				if (usuarioDeLogin != null) {
					
					if (usuarioDeLogin.getTipo() == "Administrador") {
						 boolean deseaOtraOperacionAdmin = true;

	                    while (deseaOtraOperacionAdmin) {

	                        String pasoAdmin = service.preguntarPasoAdministrador(casinoData.getSaldoCasino());
	                        if (pasoAdmin == null) {
	                            deseaOtraOperacionAdmin = false;
	                            break;
	                        }

	                        switch (pasoAdmin) {
	                        case "Ver saldo casino":
	                            administrador.verSaldoCasino(casinoData);
	                            break;

	                        case "Ingresar dinero casino":
	                            administrador.ingresarDineroCasino(casinoData);
	                            break;

	                        case "Retirar dinero casino":
	                            administrador.retirarDineroCasino(casinoData);
	                            break;
	                            
	                        case "Ver préstamos":
	                        	administrador.verPrestamos(casinoData);
	                            break;
	                            
	                        case "Ver préstamo máximo":
	                            administrador.verMaxPrestamo(casinoData);
	                            break;
	                            
	                        case "Cambiar préstamo máximo":
	                            administrador.cambiarMaxPrestamo(casinoData);
	                            break;


	                        case "Ver estadisticas":
	                            administrador.verEstadisticas(casinoData);
	                            break;

	                        case "Ver historial":
	                            administrador.verHistorial(casinoData);
	                            break;
	                            
	                        default:
								break;
	                        }
	                    }
					} else {
						usuarioLoginSuccessId = usuarioDeLogin.getId();

						Jugador jugadorCurrent = service.getJugador(jugadores, usuarioLoginSuccessId);

						if (jugadorCurrent != null) {
							boolean deseaOtraOperacionJugador = true;

							while (deseaOtraOperacion) {
								String pasoJugador = service.preguntarPasoJugador(jugadorCurrent.getNombre(),
										jugadorCurrent.getSaldo());

								if (pasoJugador == null) {
									deseaOtraOperacionJugador = false;
									break;
								}

								switch (pasoJugador) {
								case "Cerrar Session":
									deseaOtraOperacionJugador = false;
									break;
								case "Jugar caballo":
									jugadorCurrent.setSaldo(jugadorCurrent.getSaldo() - 10);
									jugadorCurrent.setSaldo(jugadorCurrent.getSaldo() + caballoJuego.jugar(10));
									break;
								default:
									break;
								}
							}
						}
					}
				}
				break;
			case "Cancelar":
				deseaOtraOperacion = false;
				JOptionPane.showMessageDialog(null, "Chao!");
				break;
			default:
				break;
			}
		}
	}
}
