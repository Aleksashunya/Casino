package paqueteCasino;

public class Usuario {
	private Integer id;
	private String login;
	private String password;
	private String tipo;
	
	public Usuario(Integer id, String login, String password, String tipo) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", password=" + password + ", tipo=" + tipo + "]";
	}
}
