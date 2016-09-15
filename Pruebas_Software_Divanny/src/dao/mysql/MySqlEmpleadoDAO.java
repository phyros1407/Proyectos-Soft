package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import beans.AsistenciasBean;
import beans.ContactoBean;
import beans.CorreoBean;
import beans.DetalleTrabajadorBean;
import beans.EmpleadoBean;
import beans.ObreroBean;
import beans.VendedorBean;
import dao.factory.MySqlDAOFactory;
import dao.interfaces.I_Empleado;

public class MySqlEmpleadoDAO extends MySqlDAOFactory implements I_Empleado{

	

	public boolean registrar(EmpleadoBean empleado){
	
		
		
		try {
		Connection conexion=MySqlDAOFactory.obtenerConexion();
		Statement stmt=conexion.createStatement();
		
		int filas1=stmt.executeUpdate("insert into t_trabajador values('"+empleado.getDni()+"','"+empleado.getNombre().toUpperCase()+"','"+empleado.getApellido().toUpperCase()+"','"+empleado.getResidencia().toUpperCase()+"','"+empleado.getPerfil()+"','A','"+empleado.getSexo()+"');");
		if(filas1==1){
			return true;
		}
				
		} catch (Exception e) {
		// TODO: handle exception
		System.out.print(e.getMessage());
		}
	
		return false;	
	}
	

	@Override
	public boolean registrarDet(DetalleTrabajadorBean det) {
		// TODO Auto-generated method stub
		
		try {
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			int filas1=stmt.executeUpdate("insert into t_detalle_pago values('"+det.getDni_trab()+"',"+det.getSueldo()+",15,15,9,1)");
			if(filas1==1){
				return true;
			}
					
			} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
			}
		
		
		
		return false;
	}

	@Override
	public boolean guardarCon(ContactoBean contacto) {
		// TODO Auto-generated method stub
		
		try {
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			int filas1=stmt.executeUpdate("insert into t_contacto (dni_trab,descripcion,telefono) values('"+contacto.getDni_trab()+"','"+contacto.getDescripcion()+"','"+contacto.getTelefono()+"')");
			if(filas1==1){
				return true;
			}
					
			} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
			}
		
		return false;
	}


	@Override
	public boolean guardarCorreo(CorreoBean correo) {
		// TODO Auto-generated method stub
		

		try {
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			int filas1=stmt.executeUpdate("insert into t_correo (dni_trab,correo) values('"+correo.getDni_trab()+"','"+correo.getCorreo()+"')");
			if(filas1==1){
				return true;
			}
					
			} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
			}
		return false;
	}

	
	
	
	public boolean generarUsuario(String nombre,String apellido,String DNI){
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			int filas1=stmt.executeUpdate("call crearUsuario('"+nombre+"','"+apellido+"','"+DNI+"')");
			if(filas1==1){
				return true;
			}
			}catch(Exception e){
			System.out.print(e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean registrarVen(VendedorBean ven) {
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			int filas1=stmt.executeUpdate("insert into t_vendedor values('"+ven.getDni_trab()+"',"+ven.getPorcentaje()+")");
			if(filas1==1){
				return true;
			}
			}catch(Exception e){
			System.out.print(e.getMessage());
		}
		return false;
	}

	
	
	
	@Override
	public EmpleadoBean login(String usuario,String contraseña) {		
		
		EmpleadoBean empleado=null;		
		try{
			
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery("select t.dni,t.nombre,t.apellido,t.perfil,t.estado from t_trabajador t,t_usuario u where " 
					+"u.usuario='"+usuario+"' and u.contraseña='"+contraseña+"'and t.dni=u.dni_trabajador;");
			
			if(!rs.isBeforeFirst()){
				return null;
			}else{
				while(rs.next()){
					empleado=new EmpleadoBean();
					empleado.setDni(rs.getString("dni"));
					empleado.setNombre(rs.getString("nombre"));
					empleado.setApellido(rs.getString("apellido"));
					empleado.setPerfilD(obtenerPerfil(Integer.parseInt(rs.getString("perfil"))));
					empleado.setEstado(rs.getString("estado"));
					System.out.println(empleado.getNombre());
					return empleado;
				}
			}
			
			
		}catch(Exception e){
		System.out.print(e.getMessage());
			
		} 
		return null;
		
		
	}
	
	@Override
	public String obtenerPerfil(int perfil) {
		// TODO Auto-generated method stub
		
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("select perfilDes from t_perfil where id_perfil='"+perfil+"';");
			
			System.out.println("Perfil");
			while(rs.next()){
			String perfilDes =rs.getString("perfilDes");

			System.out.println(perfilDes);
			return perfilDes;
			}			
			
		}catch(Exception e){
			
		}
		
		return "";
	}

	@Override
	public ArrayList<EmpleadoBean> listarTipo(int tipo) {
		// TODO Auto-generated method stub
		ArrayList<EmpleadoBean> empleados=new ArrayList<EmpleadoBean>();
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * "
										+  "from t_trabajador  "
										+  "where perfil = "+tipo+" ");
			
			EmpleadoBean empleado=null;
			while(rs.next()){
				empleado=new EmpleadoBean();
				empleado.setDni(rs.getString("dni"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellido(rs.getString("apellido"));
				empleados.add(empleado);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return empleados;
	}

	@Override
	public boolean guardarVen(VendedorBean vendedor) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			System.out.println(vendedor.getDia());
			ResultSet rs = stmt.executeQuery(" select * "
											+" from t_detalle_com "
											+" where dni_trab = '"+vendedor.getDni_trab()+"' and dia = date_format('"+vendedor.getDia()+"','%Y-%m-%d') ;");
			
			System.out.println("LLEGUE  A COMPROBAR");
			
			if(rs!=null){
				System.out.println("NO EXISTE ENTONCES SE AGREGA");
				int filas =stmt.executeUpdate("insert into t_detalle_com (dni_trab,dia,venta) values('"+vendedor.getDni_trab()+"',date_format('"+vendedor.getDia()+"','%Y-%m-%d'),"+vendedor.getVentas()+");");
				if(filas==1){
					flag=true;
				}
			}else{
				System.out.println("EXISTE ENTONCES SE NOS VA");
				flag=false;
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean guardarHor(ObreroBean obrero) {
		boolean flag=false;
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			System.out.println(obrero.getDia());
			ResultSet rs = stmt.executeQuery(" select * "
											+" from t_obrero "
											+" where dni_trab = '"+obrero.getDni_trab()+"' and dia = date_format('"+obrero.getDia()+"','%Y-%m-%d') ;");
			
			System.out.println("LLEGUE  A COMPROBAR");
			
			if(rs!=null){
				System.out.println("NO EXISTE ENTONCES SE AGREGA");
				int filas =stmt.executeUpdate(" insert into t_obrero "
											+ " (dni_trab,hor_ext,dia) "
											+ " values('"+obrero.getDni_trab()+"',"+obrero.getHor_ext()+",date_format('"+obrero.getDia()+"','%Y-%m-%d')); ");
				if(filas==1){
					flag=true;
				}
			}else{
				System.out.println("EXISTE ENTONCES SE NOS VA");
				flag=false;
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return flag;
	}


	@Override
	public ArrayList<EmpleadoBean> listar() {
		// TODO Auto-generated method stub
		ArrayList<EmpleadoBean> empleados =new ArrayList<EmpleadoBean>();
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select * from t_trabajador ");
			
			EmpleadoBean empleado=null;
			while(rs.next()){
				empleado=new EmpleadoBean();
				empleado.setDni(rs.getString("dni"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellido(rs.getString("apellido"));
				empleado.setResidencia(rs.getString("residencia"));
				empleado.setPerfilD(rs.getString("perfil"));
				empleado.setEstado(rs.getString("estado"));
				empleado.setSexo(rs.getString("sexo"));
				empleados.add(empleado);
			}
			
			
		}catch(Exception e){
		System.out.println(e.getMessage());
		}
		return empleados;
	}



	@Override
	public EmpleadoBean buscar(String dni) {
		// TODO Auto-generated method stub
		EmpleadoBean empleado= new EmpleadoBean();
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			Statement stmt2=conexion.createStatement();
			
			String query = "select * from t_trabajador t,t_perfil p,t_detalle_pago dp,t_correo cr where p.id_perfil = t.perfil and  t.dni = dp.dni_trab and  cr.dni_trab = t.dni and t.dni = '"+dni+"';";
			
			System.out.println("BUSCAR EMPLEADO ------>"+query);
			
			ResultSet rs=stmt.executeQuery(query);
			
			
			
			while(rs.next()){
				empleado.setDni(rs.getString("dni"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellido(rs.getString("apellido"));
				empleado.setResidencia(rs.getString("residencia"));
				empleado.setPerfil(rs.getInt("perfil"));
				empleado.setPerfilD(rs.getString("perfilDes"));
				empleado.setSexo(rs.getString("sexo"));
				empleado.setSueldo(rs.getDouble("sueldo"));
				empleado.setCorreo(rs.getString("correo"));
				empleado.setSegVid(rs.getInt("segVid"));
				empleado.setSegMed(rs.getDouble("segMed"));
				
			}
			
			String query2 = "select telefono from t_contacto where dni_trab = '"+dni+"';";
			System.out.println("BUSCAR CONTACTOS ------->"+query2);
			ResultSet rs2 = stmt2.executeQuery(query2);
			
			ArrayList<ContactoBean> contactos = new ArrayList<ContactoBean>();
			ContactoBean contacto = null;
			
			while(rs2.next()){
				contacto = new ContactoBean();
				contacto.setTelefono(rs2.getString("telefono"));
				contactos.add(contacto);
			}
			
			empleado.setContactos(contactos);
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return empleado;
	}


	@Override
	public boolean eliminarEmpleado(String dni) {
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			int filas=stmt.executeUpdate(" update t_trabajador set estado = 'I' where dni = '"+dni+"' ");
			
			if(filas==1){
				return true;
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}


	@Override
	public boolean comprobarExistencia(String dni) {
		// TODO Auto-generated method stub
		
		try{
			
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from t_trabajador where dni = '"+dni+"'");
			
			
			if(rs.first()){
				return false;
			}else{
				return true;
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return false;
		
	}


	@Override
	public boolean rccuperarEmpleado(String dni) {
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			int filas=stmt.executeUpdate(" update t_trabajador set estado = 'A' where dni = '"+dni+"' ");
			
			if(filas==1){
				return true;
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}


	
	

	
	

	
}
