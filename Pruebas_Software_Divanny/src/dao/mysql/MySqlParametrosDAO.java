package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import beans.ParametrosBean;
import dao.factory.DAOFactory;
import dao.factory.MySqlDAOFactory;
import dao.interfaces.I_Parametro;

public class MySqlParametrosDAO implements I_Parametro{

	@Override
	public ArrayList<ParametrosBean> obtenerParametros() {
		// TODO Auto-generated method stub
		
		ArrayList<ParametrosBean> parametros=new ArrayList<ParametrosBean>();
		ParametrosBean parametro;
		try {
			
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			ResultSet rs=stmt.executeQuery("select id_seg,nombre,porcentaje from t_parametros;");
			
			
			if(!rs.isBeforeFirst()){
				parametros=null;
			}else{
				while(rs.next()){
					parametro=new ParametrosBean();
					parametro.setId_seg(Integer.parseInt(rs.getString("id_seg")));
					parametro.setNombre(rs.getString("nombre"));
					parametro.setPorcentaje(Double.parseDouble(rs.getString("porcentaje")));
					parametros.add(parametro);
					System.out.println(parametros);
				}

				ResultSet rs1=stmt.executeQuery("select DISTINCT segMed as SEGMED from t_detalle_pago;");
				parametro=new ParametrosBean();
				parametro.setId_seg(3);
				parametro.setNombre("Seguro médico");
				
				if(!rs1.isBeforeFirst()){
					parametros=null;
				}else{
				while(rs1.next()){
				parametro.setPorcentaje(Double.parseDouble(rs1.getString("SEGMED")));
				System.out.println(parametro);
				}
				}
				
				parametros.add(parametro);
				conexion.close();
				
				return parametros;
			}

			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	public boolean actualizarParametros(int id, double porcentaje) {
		// TODO Auto-generated method stub
		
		try{
			int resultado=-1;
			int resultado1=-1;
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			
			if(id==1||id==2){
				resultado=stmt.executeUpdate("update t_parametros set porcentaje="+porcentaje+"where id_seg='"+id+"'");
				System.out.println(resultado);
			}else{
				if(id==3){
				resultado1=stmt.executeUpdate("update t_detalle_pago set segMed="+porcentaje+";");
				System.out.println(resultado1);
				}
				if(resultado1==0){
					System.out.println("No hizo el update");
					return false;
				}
				
			}
			
			if(resultado!=0||resultado1!=0){
				return true;
			}else{
				return false;	
			}
		}catch(Exception e){
			
		}
		
		return false;
	}

}
