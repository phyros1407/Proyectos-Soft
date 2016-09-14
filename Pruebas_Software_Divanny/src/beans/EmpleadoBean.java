package beans;

import java.util.ArrayList;

public class EmpleadoBean {

	private String dni;
	private String nombre;
	private String apellido;
	private String residencia;
	private int perfil;

	private double sueldo;
	private String estado;
	private int aestado;
	private String perfilD;
	private String sexo;
	
	private UsuarioBean usuario;
	
	private VendedorBean vendedor;
	
	private ArrayList<ContactoBean> contactos;
	
	private ArrayList<AsistenciasBean> asis;
	
	public String getPerfilD() {
		return perfilD;
	}
	public void setPerfilD(String perfilD) {
		this.perfilD = perfilD;
	}
	public int getAestado() {
		return aestado;
	}
	public void setAestado(int aestado) {
		this.aestado = aestado;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getResidencia() {
		return residencia;
	}
	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}
	public int getPerfil() {
		return perfil;
	}
	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String string) {
		this.estado = string;
	}
	
	public ArrayList<AsistenciasBean> getAsis() {
		return asis;
	}
	public void setAsis(ArrayList<AsistenciasBean> asis) {
		this.asis = asis;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public UsuarioBean getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}
	public VendedorBean getVendedor() {
		return vendedor;
	}
	public void setVendedor(VendedorBean vendedor) {
		this.vendedor = vendedor;
	}
	public ArrayList<ContactoBean> getContactos() {
		return contactos;
	}
	public void setContactos(ArrayList<ContactoBean> contactos) {
		this.contactos = contactos;
	}
	
	
	
	
}

