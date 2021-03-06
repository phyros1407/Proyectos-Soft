package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import beans.AsistenciasBean;
import beans.EmpleadoBean;
import beans.PlanillaBean;
import dao.factory.MySqlDAOFactory;
import dao.interfaces.I_PagosPlanilla;

public class MySqlPagosPlanillaDAO extends MySqlDAOFactory implements I_PagosPlanilla  {

	@Override
	public double obtenerSueldo(String dni) {
		// TODO Auto-generated method stub
		
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("select sueldo from t_detalle_pago where dni_trab='"+dni+"'");
			if (!rs.isBeforeFirst()){
				conexion.close();
				return 0;	
			}else{
			while(rs.next()){
			double sueldo=rs.getDouble("sueldo");				
			System.out.println(sueldo);
			return sueldo;
			}
			}
			
		}catch(Exception e){
			
		}
		
		return 0;
	}

	@Override
	public double aumentarHObreros(String dni) {
		// TODO Auto-generated method stub
		double aumento=0;
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT SUM(hor_ext) as aumentoH from t_obrero where dni_trab='"+dni+"' and MONTH(dia) = MONTH(CURDATE());");
			if (!rs.isBeforeFirst()){
				conexion.close();
				return 0;}
			else{
			while(rs.next()){
			aumento=rs.getDouble("aumentoH");				
			System.out.println(aumento);
			
			}
			aumento=(aumento/285)*1.5;			
			System.out.println(aumento);
			return aumento;
			}
			
		}catch(Exception e){
			
		}
		
		return 0;
		
	}

	@Override
	public double aumentarCVendedor(String dni) {
		// TODO Auto-generated method stub

		double aumento=0;
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT ROUND(SUM(venta) * (SELECT comision FROM t_vendedor WHERE dni_trab = '"+dni+"'),2) AS 'comisionN'"+
			"FROM t_detalle_com WHERE MONTH(dia) = MONTH(CURDATE()) AND dni_trab = '"+dni+"';");
			if (!rs.isBeforeFirst()){
				conexion.close();
				return 0;}
			else{
			while(rs.next()){
			aumento=rs.getDouble("comisionN");				
			System.out.println(aumento);
			
			}
			return aumento;
			}
			
		}catch(Exception e){
			
		}
		
		return 0;		
		
	}

	@Override
	public double aplicarDescuento(String dni,double sueldo) {
		// TODO Auto-generated method stub
		double descuento=0;
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT count(fecha) as descuento from t_asistencias where dni_trab='"+dni+"' and MONTH(fecha)=MONTH(CURDATE()) and estadoD='F';");
			if (!rs.isBeforeFirst()){
				conexion.close();
				return 0;
			}else{
			while(rs.next()){
			descuento=rs.getDouble("descuento");				
			System.out.println(descuento);
			
			}
			descuento=(descuento*sueldo)/30;
			System.out.println(descuento);
			return descuento;
			}
			
		}catch(Exception e){
			
		}
		
		return 0;
	}

	@Override
	public double descontarSeguroVida(String dni,double sueldoR) {
		// TODO Auto-generated method stub
		
		double porcentaje=0;
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("select porcentaje from t_detalle_parametro where idTrabajador='"+dni+"';");   
			if (!rs.isBeforeFirst()){
				conexion.close();
				return 0;
			}else{
			while(rs.next()){
			porcentaje=rs.getDouble("seguroVida");				
			System.out.println(porcentaje);			
			}						
			return (sueldoR*(porcentaje)/100.00);
			}
			
		}catch(Exception e){
			
		}
		
		return 0;
	}

	@Override
	public double calcularSeguroSalud(String dni,double sueldoR) {
		// TODO Auto-generated method stub
		double seguroSalud=0;
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT ROUND("+sueldoR+"*(select DISTINCT segMed as SEGMED from t_detalle_pago)/100,2) as seguroSalud from dual;");
			if (!rs.isBeforeFirst()){
				conexion.close();
				return 0;
			}else{
			while(rs.next()){
			seguroSalud=rs.getDouble("seguroSalud");				
			System.out.println(seguroSalud);
			
			}
			return seguroSalud;
			}
			
		}catch(Exception e){
			
		}
		
		return 0;
	}

	@Override
	public boolean registrarPlanilla(double sueldo, double sueldoR, String dni, double aumento, double descuento,
			double seguroV, double seguroS) {
		// TODO Auto-generated method stub
		
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			int rs=stmt.executeUpdate("call insertarPlanillas('"+dni+"',"+sueldo+","+aumento+","+descuento+","+seguroV+","+seguroS+","+sueldoR+")");
			
			if(rs!=0){
				return true;
			}
			
		return false;
	}catch(Exception e){
		
	}
	return false;
	
}

	@Override
	public ArrayList<PlanillaBean> listarPlanilla(int mes, int ano) {
		// TODO Auto-generated method stub
		ArrayList<PlanillaBean> planillas=new ArrayList<PlanillaBean>();
		PlanillaBean planilla;
		String fromA="";
		String exQuery="";
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM t_planilla where mes="+mes+" and ano="+ano+";");
			
			if (!rs.isBeforeFirst()){
				planillas=null;
			}else{
				while(rs.next()){
				planilla=new PlanillaBean();
				planilla.setDni(rs.getString("dni_trab"));
				planilla.setMes(Integer.parseInt(rs.getString("mes")));
				planilla.setAno(Integer.parseInt(rs.getString("ano")));
				planilla.setSueldo(Double.parseDouble(rs.getString("sueldo")));
				planilla.setAumento(Double.parseDouble(rs.getString("aumento")));
				planilla.setDescuento(Double.parseDouble(rs.getString("descuento")));
				planilla.setSeguroVida(Double.parseDouble(rs.getString("seguroVida")));
				planilla.setSeguroSalud(Double.parseDouble(rs.getString("seguroSalud")));
				planilla.setSueldoNeto(Double.parseDouble(rs.getString("sueldoNeto")));
				planillas.add(planilla);
				}
				return planillas;
			}
			conexion.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return planillas;
	}

	
}