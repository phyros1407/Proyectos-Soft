package beans;

import java.util.ArrayList;

public class EmpleadoBean {

	private String dni;
	private String nombre;
	private String apellido;
	private String residencia;
	private int perfil;
	private int celular;
	private int fijo;
	private double sueldo;
	private String estado;
	private int aestado;
	private String perfilD;
	private String sexo;
	
	
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
	public int getCelular() {
		return celular;
	}
	public void setCelular(int celular) {
		this.celular = celular;
	}
	public int getFijo() {
		return fijo;
	}
	public void setFijo(int fijo) {
		this.fijo = fijo;
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
	
	
	
	
}

