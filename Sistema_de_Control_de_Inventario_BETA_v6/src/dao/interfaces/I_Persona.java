package dao.interfaces;

import java.util.Vector;

import beans.PersonaBean;

public interface I_Persona {

	public Vector<PersonaBean> listar(); 
	public int agregar(PersonaBean persona);
	public boolean eliminar(int codigo);
	public PersonaBean buscarPersona(int codigo);
	public boolean actualizarPersona(PersonaBean persona);
	public boolean configurarUsuario(PersonaBean persona);
	public int obtenerPerfil(int codigo);
	public int cantidadAdm();
	
}
