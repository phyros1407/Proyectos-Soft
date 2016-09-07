package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.factory.MySqlDAOFactory;
import dao.interfaces.I_PagosPlanilla;

public class MySqlPagosPlanillaDAO extends MySqlDAOFactory implements I_PagosPlanilla  {

	@Override
	public double obtenerSueldo(int dni) {
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
	public double aumentarHObreros(int dni) {
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
	public double aumentarCVendedor(int dni) {
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
	public double aplicarDescuento(int dni,double sueldo) {
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
	public double descontarSeguroVida(int dni,double sueldoR) {
		// TODO Auto-generated method stub
		
		double seguroVida=0;
		try{
			Connection conexion=MySqlDAOFactory.obtenerConexion();
			Statement stmt=conexion.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT ROUND("+sueldoR+"*(SELECT porcentaje from t_parametros where id_seg=(select segVid from t_detalle_pago "
					+ "where dni_trab='"+dni+"'))/100,2) as seguroVida from dual; ");   
			if (!rs.isBeforeFirst()){
				conexion.close();
				return 0;
			}else{
			while(rs.next()){
			seguroVida=rs.getDouble("seguroVida");				
			System.out.println(seguroVida);
			
			}
			return seguroVida;
			}
			
		}catch(Exception e){
			
		}
		
		return 0;
	}

	@Override
	public double calcularSeguroSalud(int dni,double sueldoR) {
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
	public boolean registrarPlanilla(double sueldo, double sueldoR, int dni, double aumento, double descuento,
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
}