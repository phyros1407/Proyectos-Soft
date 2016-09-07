package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import beans.AsistenciasBean;
import beans.DetalleTrabajadorBean;
import beans.EmpleadoBean;
import beans.JustificacionBean;
import beans.UsuarioBean;
import dao.factory.MySqlDAOFactory;
import dao.interfaces.I_Asistencias;
import javafx.scene.control.Alert;


public class MySqlAsistenciaDAO extends MySqlDAOFactory implements I_Asistencias {

	@Override
	public ArrayList<EmpleadoBean> listarEmpleados(int est) {
		// TODO Auto-generated method stub
		ArrayList<EmpleadoBean> empleados=new ArrayList<EmpleadoBean>();
		EmpleadoBean empleado;
		String fromA="";
		String exQuery="";
		//if(est!=0){
		//	exQuery="and t.dni=a.dni_trab and fecha=curdate()";
			//fromA=",t_asistencias a";
		//}
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("select t.dni,t.nombre,t.apellido,p.perfilDes from t_trabajador t,t_perfil p"+fromA+" "
					+ "where t.perfil=p.id_perfil "+exQuery+" and t.estado='A' group by 1");
			
			if (!rs.isBeforeFirst()){
				empleados=null;
			}else{
				while(rs.next()){
				empleado=new EmpleadoBean();
				empleado.setDni(Integer.parseInt(rs.getString("dni")));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellido(rs.getString("apellido"));
				empleado.setPerfilD(rs.getString("perfilDes"));
				empleado.setAestado(obtenerEstado(Integer.parseInt(rs.getString("dni"))));
				empleados.add(empleado);
				}
				return empleados;
			}
			conexion.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return empleados;
	}

	@Override
	public boolean registrarAsistenciaEnt(int dni, int estado) {
		// TODO Auto-generated method stub
		
		System.out.println(dni);
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			Statement stmt1=conexion.createStatement();
			ResultSet rs;
			
			//Registrar faltas
			if(estado>0){
				System.out.println("Estado mayor");
				if(estadoInasistencias()){					
				
				System.out.println("Debe registrar");				
				rs=stmt.executeQuery("select dni from t_trabajador t left join (SELECT dni as dniF FROM t_trabajador t "+ 
"inner JOIN t_asistencias a on t.dni=a.dni_trab where fecha=curdate()) x on t.dni=x.dniF where x.dniF is null and t.estado='A'");
				
				if(!rs.isBeforeFirst()){
					System.out.println("No carga datos");
				}else{
				while(rs.next()){
					System.out.println(rs.getInt("dni"));
					registrarInasistencias(rs.getInt("dni"));
				}
				System.out.println("Registró inasistencias");}
			}}
			
			int resultado=stmt1.executeUpdate("call registrarAsistencia("+dni+","+estado+")");
			System.out.println(resultado+"Registro asistencia");
			if(resultado!=0){
				System.out.println("Debe dar el alert");
				conexion.close();
				return true;
			}else{
				conexion.close();
			}

		}catch(Exception e){
			
		}
		
		return false;
	}

	@Override
	public int obtenerEstado(int dni) {
		// TODO Auto-generated method stub
		
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("select estadoH from t_asistencias where dni_trab="+dni+" and fecha=curdate();");
			if (!rs.isBeforeFirst()){
				conexion.close();
				return 0;}
			else{
			while(rs.next()){
			int aest=rs.getInt("estadoH");				
			System.out.println(aest);
			return aest;
			}
			}
			
		}catch(Exception e){
			
		}
		
		return 3;
	}

	
	
	
	

	@Override
	public boolean registrarInasistencias(int dni) {
		
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();	
			int resultado=stmt.executeUpdate("insert into t_asistencias(fecha,dni_trab,estadoH,estadoD) "
					+ "values(curdate(),"+dni+",'5','F');");
			if(resultado!=0){
				System.out.println("Llega al insert Asistencia");
				return true;
			}
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println("Se jode en la inasistencia");
		return false;
	}
	
	@Override
	public boolean estadoInasistencias() {
		
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();	
			ResultSet rs=stmt.executeQuery("select * from t_asistencias where estadoD='F' and fecha=curdate();");
			
			if (!rs.isBeforeFirst()){
				conexion.close();
				return true;
			}else{
			while(rs.next()){
				return false;
			}}					
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public ArrayList<AsistenciasBean> listarAsistencias(int dni) {
		// TODO Auto-generated method stub
		
		ArrayList<AsistenciasBean> asistencias=new ArrayList<AsistenciasBean>();
		AsistenciasBean asistencia;
			
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("select fecha,horaEnt,horaRef,horaSref,horaSal,estadoD "
					+ "from t_asistencias where DATE_SUB(curdate(), INTERVAL 1 MONTH) and dni_trab="+dni+";");
			
			if (!rs.isBeforeFirst()){
				asistencias=null;
			}else{
				while(rs.next()){
				asistencia=new AsistenciasBean();
				asistencia.setFecha(rs.getString("fecha"));
				asistencia.setHoraEnt(rs.getString("horaEnt"));
				asistencia.setHoraRef(rs.getString("horaRef"));
				asistencia.setHoraSref(rs.getString("horaSref"));
				asistencia.setHoraSal(rs.getString("horaSal"));
				asistencia.setEstadoD(rs.getString("estadoD"));
				asistencias.add(asistencia);
				}
				return asistencias;
			}
			conexion.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return asistencias;
	}
	
}
