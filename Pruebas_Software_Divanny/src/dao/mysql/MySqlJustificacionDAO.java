package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import beans.AsistenciasBean;
import beans.EmpleadoBean;
import beans.JustificacionBean;
import dao.factory.MySqlDAOFactory;
import dao.interfaces.I_Justificacion;

public class MySqlJustificacionDAO extends MySqlDAOFactory implements I_Justificacion{

	
	@Override
	public boolean registrarJustificacion(JustificacionBean just) { 
		 boolean flag=false; 
		 try{ 
		 	Connection conexion=MySqlDAOFactory.obtenerConexion(); 
		 	String query =" insert into t_justificacion (asis_id,comen,motivo,archivo) " 
		 	+ " values (?,?,?,?)"; 
		 	
		 	PreparedStatement stmt2=conexion.prepareStatement(query); 
		 	stmt2.setInt(1, just.getId_asis()); 
		 	stmt2.setString(2, just.getComentario()); 
		 	stmt2.setString(3, just.getMotivo()); 
		 	stmt2.setBlob(4, just.getInput());

		 	int filas=stmt2.executeUpdate(); 
		 	if(filas==1){ 
		 		flag=true; 
		 	} 
		 }catch(Exception e ){ 
		 	System.out.println(e.getMessage()); 
		 } 
		 return flag; 
		}
	
	public EmpleadoBean obtenerFechFaltas(int codigo) {
		// TODO Auto-generated method stub
		EmpleadoBean empleado=null;
		
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			String query="select * from t_trabajador where dni='"+codigo+"'";
			
			
			ResultSet rs=stmt.executeQuery(query);
			
			while(rs.next()){
				System.out.println("llenando empleado");
				empleado=new EmpleadoBean();
				empleado.setDni(rs.getString("dni"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellido(rs.getString("apellido"));
				empleado.setResidencia(rs.getString("residencia"));
			}
			
			ResultSet rs1=stmt.executeQuery("select id_calendario,date_format(fecha,'%d-%m-%Y') as fechaM, fecha "
					 + "from t_asistencias "
					 + "where dni_trab = "+codigo+" and estadoD='F'"
					 + "order by 1;");
			ArrayList<AsistenciasBean> asis= new ArrayList<AsistenciasBean>();
			AsistenciasBean asi=null;
			while(rs1.next()){
				asi=new AsistenciasBean();
				asi.setId(rs1.getInt("id_calendario"));
				asi.setFecha(rs1.getString("fecha"));
				asi.setHoraEnt(rs1.getString("fechaM"));
				asis.add(asi);
			}
			
			empleado.setAsis(asis);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return empleado;
	}
	
	@Override
	public boolean actualizarEstadoAsistencia(AsistenciasBean asi) {
		// TODO Auto-generated method stub
		boolean flag=false;
		
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			int filas=stmt.executeUpdate( "update t_asistencias "
										+ " set estadoD = 'J' "
										+ " where id_calendario= '"+asi.getId()+"' and dni_trab = '"+asi.getDni_trab()+"' " );
		
			if(filas==1){
				flag=true;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
}
