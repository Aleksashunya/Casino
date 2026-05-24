package paqueteCasino;

import javax.swing.JOptionPane;

public class Jugador extends Usuario {
	private String nombre;
	private Integer saldo;
	
	public Jugador(Integer id, String login, String password, String nombre) {
		super(id, login, password, "Jugador");
		this.nombre = nombre;
		this.saldo = 100;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}
	
	public Integer hacerApuesta(Integer minApuesta) {
		if (getSaldo() <= minApuesta) {
			JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar la apuesta.");
			return null;
		}
		
		int apuesta = 0;
		
		while (apuesta <= 0) {
			String apuestaString = JOptionPane.showInputDialog(null, "Saldo: " + getSaldo() + "\nMin apuesta: " + minApuesta + "\nIngresa apuesta");
			if (apuestaString == null) {
				return null;
			}
			try {
				apuesta = Integer.parseInt(apuestaString);
				if (apuesta < minApuesta) {
					JOptionPane.showMessageDialog(null, "Apuesta debe ser mayor a " + minApuesta);
					apuesta = 0;
				}
				if (apuesta > getSaldo()) {
					JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar la apuesta.");
					apuesta = 0;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Apuesta inválido");
			}
		}
		return apuesta;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + getId() + ", nombre=" + nombre  + ", saldo= $" + saldo + "]";
	}
}
