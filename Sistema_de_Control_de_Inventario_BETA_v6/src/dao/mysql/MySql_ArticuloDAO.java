package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.ArticuloBean;
import beans.MovimientoBean;
import dao.factory.MySqlDAOFactory;
import dao.interfaces.I_Articulo;


public class MySql_ArticuloDAO  extends MySqlDAOFactory implements I_Articulo {

	@Override
	public Vector<ArticuloBean> listar() {
		Vector<ArticuloBean> articulos =new Vector<ArticuloBean>();
		
	try{
			
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from articulo order by nombre");
			
			ArticuloBean articulo=null;
			while(rs.next()){
			articulo=new ArticuloBean();
			articulo.setId(rs.getInt("idarticulo"));
			articulo.setNombre(rs.getString("nombre"));
			articulo.setMedida(rs.getString("medida"));
			articulo.setCantidad(rs.getInt("cantidad"));
			articulo.setEstado(rs.getInt("estado"));
			articulo.setFecha_u_c(rs.getString("fecha_u_c"));
			articulo.setId_u_c(rs.getString("id_u_c"));
			articulo.setFecha_u_m(rs.getString("fecha_u_m"));
			articulo.setId_u_m(rs.getString("id_u_m"));
			articulos.add(articulo);
			}
			
			
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		
		
		
		return articulos;
	}

	@Override
	public boolean agregar(ArticuloBean articulo) {
		// TODO Auto-generated method stub
		
		
		boolean flag=false;
		
		try{
			
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			int filas=stmt.executeUpdate("insert into articulo (nombre, medida,cantidad,estado,fecha_u_c,id_u_c,fecha_u_m,id_u_m) "
										+ "values ('"+articulo.getNombre().toUpperCase()+"','"+articulo.getMedida().toUpperCase()+"',"+articulo.getCantidad()+",'1',now(),'1',NULL,NULL)");
			
			if(filas==1){
				flag=true;
			}
			
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		
		
		
		
		return flag;
	}

	@Override
	public boolean restar(MovimientoBean movimiento) {
		// TODO Auto-generated method stub
		
		boolean flag=false;
		
		try{
			
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			int filas=stmt.executeUpdate(" UPDATE articulo "
										+" SET cantidad=cantidad-"+movimiento.getCantidad_retirada()+",fecha_u_m=now(),id_u_m="+movimiento.getId_usuario()
										+" WHERE idarticulo="+movimiento.getId_articulo());
			
			
		
			if(filas==1){
				
				flag=true;
			}
			
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		
		
		return flag;
	}

	@Override
	public boolean modificar(ArticuloBean articulo) {
		
		boolean flag=false;
		int cantidadA=0;
		try{
			
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			ResultSet rs = stmt.executeQuery("select cantidad from articulo where idarticulo = "+articulo.getId());
			
			
			
			while(rs.next()){
				cantidadA = rs.getInt("cantidad");
			}
			
			int filas=stmt.executeUpdate( "update articulo "
										+ "set cantidad="+(articulo.getCantidad()+cantidadA)+", fecha_u_m=now() "
										+ "where idarticulo="+articulo.getId()+" ");
			
			if(filas==1){
				flag=true;
			}
			
		}catch(Exception e){
			e.getMessage();
		}
		// TODO Auto-generated method stub
		return flag;
	}
	
	@Override
	public Vector<ArticuloBean> listarAgotados() {
		// TODO Auto-generated method stub
		
		Vector<ArticuloBean> articulos =new Vector<ArticuloBean>();
		
		try{
				
				Connection conexion = MySqlDAOFactory.obtenerConexion();
				Statement stmt = conexion.createStatement();
				
				ResultSet rs=stmt.executeQuery("select * from articulo where cantidad<=5 and estado = '1' ");
				
				ArticuloBean articulo=null;
				while(rs.next()){
				articulo=new ArticuloBean();
				articulo.setId(rs.getInt("idarticulo"));
				articulo.setNombre(rs.getString("nombre"));
				articulo.setMedida(rs.getString("medida"));
				articulo.setCantidad(rs.getInt("cantidad"));
				articulo.setFecha_u_c(rs.getString("fecha_u_c"));
				articulo.setId_u_c(rs.getString("id_u_c"));
				articulo.setFecha_u_m(rs.getString("fecha_u_m"));
				articulo.setId_u_m(rs.getString("id_u_m"));
				articulos.add(articulo);
				}
				
				
			}catch(Exception e){
				System.out.print(e.getMessage());
			}
			
			
			
			return articulos;
		
		
	}

	@Override
	public boolean deshabilitar(int codigo) {
		// TODO Auto-generated method stub
		boolean flag=false;
		
		try{
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			int filas=stmt.executeUpdate("Update articulo set estado = '0' where idarticulo = "+codigo);
			
			if(filas==1){
				flag=true;
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean habilitar(int codigo) {
		// TODO Auto-generated method stub
				boolean flag=false;
				
				try{
					Connection conexion = MySqlDAOFactory.obtenerConexion();
					Statement stmt = conexion.createStatement();
					
					int filas=stmt.executeUpdate("Update articulo set estado = '1' where idarticulo = "+codigo);
					
					if(filas==1){
						flag=true;
					}
					
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
				return flag;
	}
	
	

}
