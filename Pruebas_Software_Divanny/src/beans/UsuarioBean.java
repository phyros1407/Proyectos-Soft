package beans;

public class UsuarioBean {

	private int idCuenta;
	private String usuario;
	private String contraseña;
	private int dni_trabajador;	
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
	public int getDni_trabajador() {
		return dni_trabajador;
	}
	public void setDni_trabajador(int dni_trabajador) {
		this.dni_trabajador = dni_trabajador;
	}
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
	}
	
	
	
	
}
