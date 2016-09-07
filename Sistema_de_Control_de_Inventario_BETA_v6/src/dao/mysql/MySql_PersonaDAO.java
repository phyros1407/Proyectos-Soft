package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.PersonaBean;
import dao.factory.MySqlDAOFactory;
import dao.interfaces.I_Persona;

public class MySql_PersonaDAO extends MySqlDAOFactory implements I_Persona {

	@Override
	public Vector<PersonaBean> listar() {
		// TODO Auto-generated method stub
		Vector<PersonaBean> personas=new Vector<PersonaBean>();
		
		try{
			
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery(" select idpersona,nombre,apellido,correo,telefono,estadoCuenta,perfil "
										 + " from persona,usuario "
										 + " where idusuario=idpersona "
										 + " order by apellido ");
			PersonaBean persona=null;
			while(rs.next()){
				persona=new PersonaBean();
				persona.setId(rs.getInt("idpersona"));
				persona.setNombre(rs.getString("nombre"));
				persona.setApellido(rs.getString("apellido"));
				persona.setCorreo(rs.getString("correo"));
				persona.setTelefono(rs.getString("telefono"));
				persona.setEstadoCuenta(rs.getInt("estadoCuenta"));
				persona.setPerfilUsuario(rs.getInt("perfil"));
				personas.add(persona);
				
			}	
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		
		
		
		return personas;
	}

	@Override
	public int agregar(PersonaBean persona) {
		
		 int id = 0;
		
		try{
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from persona where correo = '"+persona.getCorreo()+"' ");
			
			if(!(rs.isBeforeFirst())){
				System.out.println("Entro a crear usuario");
				int fila=stmt.executeUpdate("insert into persona (nombre,apellido,correo,telefono,estadoCuenta,estadoCon,fecha_u_c,id_u_c,fecha_u_m,id_u_m)"
											+ "values ('"+persona.getNombre().toUpperCase()+"','"+persona.getApellido().toUpperCase()+"','"+persona.getCorreo().toLowerCase()+"','"+persona.getTelefono()+"','0','0',now(),1,NULL,NULL)");
				if(fila==1){
					ResultSet valorPersona=stmt.executeQuery("select max(idpersona) from persona");
					while(valorPersona.next()){
						 id=valorPersona.getInt("max(idpersona)");
					}
				}
			}
			System.out.println(id);
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		
		return id;
	}

	@Override
	public boolean eliminar(int codigo) {
		
		boolean flag=false;
		
		try{
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			int fila=stmt.executeUpdate("update persona set estadoCuenta='0' where idpersona ="+codigo);
			
			if(fila==1){
				flag=true;
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		return flag;
	}


	@Override
	public PersonaBean buscarPersona(int codigo) {
			PersonaBean persona=null;
		
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from persona where idpersona="+codigo);
			
			while(rs.next()){
				persona=new PersonaBean();
				persona.setId(rs.getInt("idpersona"));
				persona.setNombre(rs.getString("nombre"));
				persona.setApellido(rs.getString("apellido"));
				persona.setCorreo(rs.getString("correo"));
				persona.setTelefono(rs.getString("telefono"));
				persona.setEstadoCuenta(rs.getInt("estadoCuenta"));
				persona.setEstadoCon(rs.getInt("estadoCon"));
				
				
			}
			
		}catch(Exception e){
			
		}
		
		
		return persona;
	}

	@Override
	public boolean actualizarPersona(PersonaBean persona) {
		// TODO Auto-generated method stub
		boolean flag=false;
			try{
				Connection conexion=MySqlDAOFactory.obtenerConexion();
				Statement stmt=conexion.createStatement();
				
				int filas=stmt.executeUpdate( " update persona "
											+ " set nombre ='"+persona.getNombre().toUpperCase()+"',apellido ='"+persona.getApellido().toUpperCase()+"',correo ='"+persona.getCorreo().toLowerCase()+"',telefono ="+persona.getTelefono()+",fecha_u_m = now(),id_u_m = '1' "
											+ " where idpersona="+persona.getId());
				if(filas==1){
					int filas2=stmt.executeUpdate( " update usuario "
												 + " set perfil ="+persona.getPerfilUsuario()+" "
												 + " where idusuario="+persona.getId());
					if(filas2==1){
						flag=true;
					}
				}
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		
		
		return flag;
	}

	@Override
	public boolean configurarUsuario(PersonaBean persona) {
		// TODO Auto-generated method stub
		boolean flag=false;
		
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			int filas=stmt.executeUpdate( " update persona "
					+ " set nombre ='"+persona.getNombre().toUpperCase()+"',apellido ='"+persona.getApellido().toUpperCase()+"',telefono ="+persona.getTelefono()+",fecha_u_m = now(),id_u_m = '1' "
					+ " where idpersona="+persona.getId());
			
			if(filas==1){
				if(!(persona.getContraseña()==null||persona.getContraseña()==" ")){
					System.out.println("no era null");
					int filas2=stmt.executeUpdate( " update usuario "
							 + " set password ='"+persona.getContraseña()+"' "
							 + " where idusuario="+persona.getId());
					if(filas2==1){
						flag=true;
					}
				}
				System.out.println("era null");
				flag=true;
			}
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	public int obtenerPerfil(int codigo) {
		// TODO Auto-generated method stub
		int perfil=0;
		
		try{
			
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery("select perfil from usuario where idusuario="+codigo);
			
			if(rs.next()){
				perfil = rs.getInt("perfil");
				System.out.println("perfil del usuario es : "+perfil);
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return perfil;
	}

	@Override
	public int cantidadAdm() {
		// TODO Auto-generated method stub
		
		int cantidad = 0;
		
		try{
			
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			ResultSet rs = stmt.executeQuery("select count(*) as can from persona, usuario where estadoCuenta = 1  and estadoCon = 1 and idusuario=idpersona  and perfil = 1;");
			
			if(rs.next()){
				cantidad = rs.getInt("can");
				System.out.println("cantidad de administradores : "+cantidad);
			}
		}catch(Exception e){
			
		}
		
		return cantidad;
	}

}
