package paqueteCasino;

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
	
	@Override
	public String toString() {
		return "Usuario [id=" + getId() + ", nombre=" + nombre  + ", saldo= $" + saldo + "]";
	}
}
