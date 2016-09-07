package dao.mysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import beans.UsuarioBean;
import dao.factory.MySqlDAOFactory;
import dao.interfaces.I_Usuario;
import beans.PersonaBean;

public class MySql_UsuarioDAO  extends MySqlDAOFactory implements I_Usuario{

	
	@Override
	public boolean registrar(UsuarioBean usuario) {
		
		boolean flag=false;
		
		try{
			
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			int filas= stmt.executeUpdate("insert into usuario (idusuario,password,perfil,fecha_u_c,id_u_c,fecha_u_m,id_u_m) "
										  + "values('"+usuario.getId()+"','"+(usuario.getPassword())+"','"+usuario.getPerfil()+"',now(),1,NULL,NULL)");
			
			if(filas==1){
				flag=true;
			}
			
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		
		
		
		return flag;
	}

	
	
	@Override
	public PersonaBean ingresar(UsuarioBean usuario) {
			
		PersonaBean personalog=null;

		try{

			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from persona p,usuario u "
					+ "where p.idpersona=u.idusuario and p.correo='"+usuario.getUsuario()+"' and u.password='"+usuario.getPassword()+"'");
			
			while(rs.next()){
				personalog=new PersonaBean();
				personalog.setId(rs.getInt("idpersona"));
				personalog.setNombre(rs.getString("nombre"));
				personalog.setApellido(rs.getString("apellido"));
				personalog.setCorreo(rs.getString("correo"));
				personalog.setTelefono(rs.getString("telefono"));
				personalog.setEstadoCuenta(rs.getInt("estadoCuenta"));
				personalog.setEstadoCon(rs.getInt("estadoCon"));
				personalog.setPerfilUsuario(rs.getInt("perfil"));
			}
			
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		
		
		return personalog;
	}



	@Override
	public boolean desactivar(int codigo) {
		
		boolean flag=false;
		
		try{
			
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			int filas=stmt.executeUpdate("update persona set estadoCuenta='0'where idpersona="+codigo);
			
			if(filas==1){
				
				flag=true;
			}
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		
		
		return flag;
	}

	@Override
	public boolean restablecerCon(UsuarioBean usuario) {
		// TODO Auto-generated method stub
		
		boolean flag=false;
		
			try{
				Connection conexion = MySqlDAOFactory.obtenerConexion();
				Statement stmt = conexion.createStatement();
				
				int filas=stmt.executeUpdate("update usuario set password='"+usuario.getPassword()+"' where idusuario="+usuario.getId());
				
				if(filas==1){
					int filas2=stmt.executeUpdate("update persona,usuario set estadoCuenta='1',estadoCon='1' where idpersona=idusuario and idpersona="+usuario.getId());
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
	public UsuarioBean buscarUsuario(int codigo) {
		// TODO Auto-generated method stub
		
		UsuarioBean usuario=null;
		
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from usuario where idusuario="+codigo);
			
			while(rs.next()){
				usuario=new UsuarioBean();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setPassword(rs.getString("password"));
				usuario.setPerfil(rs.getString("perfil"));
				
			}
			
		}catch(Exception e){
			
		}
		
		
		
		return usuario;
	}
	
	
	
	
	
	
	
	
}
