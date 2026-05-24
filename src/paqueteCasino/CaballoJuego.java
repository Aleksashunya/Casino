package paqueteCasino;

import javax.swing.JOptionPane;

public class CaballoJuego {
	private String[] caballos = { "Magic Wave", "Yo la Reina", "Noche de Poker", "Don Master" };
	private Integer[] wins = { 0, 0, 0, 0 };
	private Integer juegasCount = 0;

	public Integer jugar(Integer apuesta) {
		mostrarCaballos();

		Integer caballoId = preguntarCaballo();

		if (caballoId == null) {
			return apuesta;
		}
		Integer[] arrayDeCaballosIds = arrayDeCaballosIds();

		Integer winnerId = (int) (Math.random() * (arrayDeCaballosIds.length));

		JOptionPane.showMessageDialog(null, "Caballo ganador: " + caballos[arrayDeCaballosIds[winnerId]]);

		Double coef = getCoef(arrayDeCaballosIds[winnerId]);

		wins[arrayDeCaballosIds[winnerId]] += 1;
		juegasCount += 1;

		if (arrayDeCaballosIds[winnerId] == caballoId) {
			JOptionPane.showMessageDialog(null, "¡Ganaste! Tu premio: $" + (int) (apuesta * coef));
			return (int) ((double) apuesta * coef);
		} else {
			JOptionPane.showMessageDialog(null, "Perdiste tu apuesta de $" + apuesta);
			return 0;
		}
	}

	private void mostrarCaballos() {
		StringBuilder sb = new StringBuilder();
		sb.append("Caballos:\n");

		for (int i = 0; i < caballos.length; i++) {
			Double coef = getCoef(i);
			sb.append(caballos[i] + ", Cuota: x" + coef + "\n");
		}

		JOptionPane.showMessageDialog(null, sb);
	}

	public Integer preguntarCaballo() {
		Object[] opciones = caballos;
		String caballo = (String) JOptionPane.showInputDialog(null, "¿Qué caballo te gusta?", "Casino",
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		if (caballo == null) {
			return null;
		}

		for (int i = 0; i < opciones.length; i++) {
			if (opciones[i].equals(caballo)) {
				return i;
			}
		}
		return null;
	}

	public Integer[] arrayDeCaballosIds() {
		Integer count = 0;
		for (int i = 0; i < wins.length; i++) {
			count += wins[i] + 1;
		}

		Integer[] caballos = new Integer[count];
		Integer current = 0;

		for (int i = 0; i < this.caballos.length; i++) {
			for (int j = 0; j < this.wins[i] + 1; j++) {
				caballos[current] = i;
				current++;
			}
		}
		return caballos;
	}

	private Double getCoef(Integer index) {
		if (juegasCount == 0)
			return 2.0;

		double percent = (double) wins[index] / juegasCount;
		double coef = 2.0 - ((2.0 * percent) - 0.1);
		coef = Math.round(coef * 10.0) / 10.0;

		return Math.max(1.1, Math.min(coef, 3.0));
	}
}
