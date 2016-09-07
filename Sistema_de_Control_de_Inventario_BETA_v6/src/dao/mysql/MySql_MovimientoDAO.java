package dao.mysql;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import dao.factory.MySqlDAOFactory;
import dao.interfaces.I_Movimiento;
import beans.MovimientoBean;


public class MySql_MovimientoDAO extends MySqlDAOFactory implements I_Movimiento {

	@Override
	public boolean crearMovimiento(MovimientoBean movimiento) {
		boolean flag=false;
		// TODO Auto-generated method stub
		
			try{
			
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			int filas=stmt.executeUpdate("insert into movimiento "
									+ "(fecha_mov,cantidad_retirada,descripcion,idarticulo,idusuario)"
									+ " values "
									+ "(now(),"+movimiento.getCantidad_retirada()+",'"+movimiento.getDescripcion()+"',"+movimiento.getId_articulo()+","+movimiento.getId_usuario()+")");
			
			
			if(filas==1){
				flag=true;
			}
			
			
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		return flag;
	}

	
	
	@Override
	public Vector<MovimientoBean> listar() {
		// TODO Auto-generated method stub
		Vector<MovimientoBean>movimientos=new Vector<MovimientoBean>();
		try{
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery(" select m.idmovimiento,date_format(m.fecha_mov,'%m-%d-%Y') as fecha, date_format(m.fecha_mov,'Hora: %h:%i %p') as hora,m.idusuario,concat(p.nombre,' ',p.apellido) as nombre,m.idarticulo,a.nombre,a.medida,m.cantidad_retirada,m.descripcion  "
										 + " from movimiento m,persona p,articulo a,usuario u "
										 + " where m.idarticulo=a.idarticulo and m.idusuario=u.idusuario and u.idusuario=p.idpersona "
										 + " order by hora desc,fecha desc");
			
			MovimientoBean movimiento=null;
			while(rs.next()){
				movimiento=new MovimientoBean();
				movimiento.setId(rs.getInt("m.idmovimiento"));
				String fecha = rs.getString("fecha");
				String hora = rs.getString("hora");
				movimiento.setFecha_mov(fecha+" "+hora);
				movimiento.setCantidad_retirada(rs.getInt("m.cantidad_retirada"));
				movimiento.setDescripcion(rs.getString("m.descripcion"));
				movimiento.setId_articulo(rs.getInt("m.idarticulo"));
				movimiento.setNombreArticulo(rs.getString("a.nombre"));
				movimiento.setMedida(rs.getString("a.medida"));
				movimiento.setId_usuario(rs.getInt("m.idusuario"));
				movimiento.setNombrePersona(rs.getString("nombre"));
				movimientos.add(movimiento);
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return movimientos;
	}



	@Override
	public Vector<MovimientoBean> listarxPer(int codigo) {
		
		Vector<MovimientoBean>movimientos=new Vector<MovimientoBean>();
		try{
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery(" select m.idmovimiento,m.idusuario,date_format(m.fecha_mov,'%m-%d-%Y') as fecha, date_format(m.fecha_mov,'Hora: %h:%i %p') as hora,m.idarticulo,a.nombre,a.medida,m.cantidad_retirada,m.descripcion  "
					 + " from movimiento m,persona p,articulo a,usuario u "
					 + " where m.idarticulo=a.idarticulo and m.idusuario=u.idusuario and u.idusuario=p.idpersona and m.idusuario="+codigo+" "
					 + "  order by fecha desc, hora  ;");

			MovimientoBean movimiento=null;
			while(rs.next()){	
				movimiento=new MovimientoBean();
				movimiento.setId(rs.getInt("m.idmovimiento"));
				String fecha = rs.getString("fecha");
				String hora = rs.getString("hora");
				movimiento.setFecha_mov(fecha+" "+hora);
				movimiento.setCantidad_retirada(rs.getInt("m.cantidad_retirada"));
				movimiento.setDescripcion(rs.getString("m.descripcion"));
				movimiento.setId_articulo(rs.getInt("m.idarticulo"));
				movimiento.setNombreArticulo(rs.getString("a.nombre"));
				movimiento.setMedida(rs.getString("a.medida"));
				movimiento.setId_usuario(rs.getInt("idusuario"));
				movimiento.setNombrePersona(rs.getString("nombre"));
				movimientos.add(movimiento);
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return movimientos;
	}



	@Override
	public MovimientoBean buscar(int codigo) {
		// TODO Auto-generated method stub
		
		MovimientoBean mov = new MovimientoBean();
		try{
			Connection conexion = MySqlDAOFactory.obtenerConexion();
			Statement stmt = conexion.createStatement();
			
			ResultSet rs = stmt.executeQuery(" select m.idmovimiento,date_format(m.fecha_mov,'%m-%d-%Y Hora: %h:%i %p') as fecha,m.idarticulo,a.nombre,a.medida,m.cantidad_retirada,m.descripcion, concat(p.nombre,' ',p.apellido) as nombrePersona "
											+" from movimiento m,articulo a ,usuario u,persona p "
											+" where m.idarticulo=a.idarticulo and m.idusuario=u.idusuario and u.idusuario=p.idpersona and m.idmovimiento= "+codigo);
			
			while(rs.next()){
				mov.setId(rs.getInt("m.idmovimiento"));
				mov.setNombrePersona(rs.getString("nombrePersona"));
				mov.setNombreArticulo(rs.getString("a.nombre"));
				mov.setMedida(rs.getString("a.medida"));
				mov.setId_articulo(rs.getInt("m.idarticulo"));
				mov.setFecha_mov(rs.getString("fecha"));
				mov.setComientario(rs.getString("m.descripcion"));
				mov.setCantidad_retirada(rs.getInt("m.cantidad_retirada"));
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return mov;
	}


}
