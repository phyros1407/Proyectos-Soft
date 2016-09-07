package dao.interfaces;

import java.util.ArrayList;

import beans.ContactoBean;
import beans.CorreoBean;
import beans.DetalleTrabajadorBean;
import beans.EmpleadoBean;
import beans.ObreroBean;
import beans.VendedorBean;

public interface I_Empleado {
	//REGISTRAR TRABAJADOR
		public boolean registrar(EmpleadoBean empleado);
		public boolean registrarDet(DetalleTrabajadorBean det);
		public boolean guardarCon(ContactoBean contacto);
		public boolean guardarCorreo(CorreoBean correo);
		public boolean generarUsuario(String nombre,String apellido,int DNI);
		public boolean registrarVen(VendedorBean ven);
		
		//LISTAR
		public ArrayList<EmpleadoBean> listar();
		public ArrayList<EmpleadoBean> listarTipo(int tipo);
		
		//OPERACIONES
		public EmpleadoBean login(String usuario,String contraseña);
		public String obtenerPerfil(int perfil);
		public boolean guardarVen(VendedorBean vendedor);
		public boolean guardarHor(ObreroBean obrero);
		public EmpleadoBean buscar(String dni);
		public boolean eliminarEmpleado(String dni);
	
}
