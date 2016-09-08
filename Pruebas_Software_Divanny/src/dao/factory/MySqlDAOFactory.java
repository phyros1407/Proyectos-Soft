package dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import dao.interfaces.I_Asistencias;
import dao.interfaces.I_Empleado;
import dao.interfaces.I_Justificacion;
import dao.interfaces.I_PagosPlanilla;
import dao.interfaces.I_Parametro;
import dao.mysql.MySqlAsistenciaDAO;
import dao.mysql.MySqlEmpleadoDAO;
import dao.mysql.MySqlJustificacionDAO;
import dao.mysql.MySqlPagosPlanillaDAO;
import dao.mysql.MySqlParametrosDAO;

public class MySqlDAOFactory  extends DAOFactory{

	
	public static Connection obtenerConexion(){
		Connection conexion = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//JELASTIC
			//String url = "jdbc:mysql://mysql16920-pruebas1.j.facilcloud.com/bd_divanny_1_4_1";
			//conexion = DriverManager.getConnection(url, "root", "SLGsev48189");
			
			//LOCALHOST
			String url = "jdbc:mysql://localhost:3306/bd_divanny_1_4_1";
			conexion = DriverManager.getConnection(url, "root", "root");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		
		return conexion;
	}

	@Override
	public I_Asistencias getAsistenciasDAO() {
		// TODO Auto-generated method stub
		return new MySqlAsistenciaDAO();
	}
	
	@Override
	public I_Parametro getParametroDAO(){
		return new MySqlParametrosDAO();
	}

	@Override
	public I_Empleado getEmpleadoDAO() {
		// TODO Auto-generated method stub
		return new MySqlEmpleadoDAO();
	}	

	@Override
	public I_PagosPlanilla getPagosPlanillaDAO() {
		// TODO Auto-generated method stub
		return new MySqlPagosPlanillaDAO();
	}
	@Override
	public I_Justificacion getJustificacionDAO() {
		// TODO Auto-generated method stub
		return new MySqlJustificacionDAO();
	}

	
}
