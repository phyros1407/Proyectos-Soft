package beans;

public class UsuarioBean {

	private int idCuenta;
	private String usuario;
	private String contraseña;
	private String dni_trabajador;	
	private char estado;
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getDni_trabajador() {
		return dni_trabajador;
	}
	public void setDni_trabajador(String dni_trabajador) {
		this.dni_trabajador = dni_trabajador;
	}
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
	}
	
	
	
	
}
