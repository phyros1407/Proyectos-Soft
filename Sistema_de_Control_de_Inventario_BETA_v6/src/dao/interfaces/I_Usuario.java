package dao.interfaces;

import beans.PersonaBean;
import beans.UsuarioBean;


public interface I_Usuario {

		public boolean registrar(UsuarioBean usuario);
		public PersonaBean ingresar(UsuarioBean usuario);
		public boolean desactivar(int id);
		public boolean restablecerCon(UsuarioBean usuario);
		public UsuarioBean buscarUsuario(int codigo);
	
}
