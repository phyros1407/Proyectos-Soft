package dao.factory;



	import java.sql.Connection;
import java.sql.DriverManager;

import dao.interfaces.I_Articulo;
import dao.interfaces.I_Movimiento;
import dao.interfaces.I_Persona;
import dao.interfaces.I_Usuario;
import dao.mysql.MySql_ArticuloDAO;
import dao.mysql.MySql_MovimientoDAO;
import dao.mysql.MySql_PersonaDAO;
import dao.mysql.MySql_UsuarioDAO;


	public class MySqlDAOFactory  extends DAOFactory{

		
		public static Connection obtenerConexion(){
			Connection conexion = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				//LOCALHOST
				String url = "jdbc:mysql://localhost:3306/bd_inventario";
				conexion = DriverManager.getConnection(url, "root", "root");
				
				//JELASTIC
				//String url = "jdbc:mysql://mysql15831-pruebas.j.facilcloud.com/bd_inventario";
				//conexion = DriverManager.getConnection(url, "root", "KHPadt78181");
				
				
				//AZURE
				//String url = "jdbc:mysql://us-cdbr-azure-northcentral-b.cloudapp.net/br_inve";
				//conexion = DriverManager.getConnection(url, "b13ca2ffd70ada", "ecbbe418");
				
					
			} catch (Exception e) {
				// TODO: handle exception
				System.out.print(e.getMessage());
			}
			
			return conexion;
		}
		
		@Override
		public I_Usuario getUsuarioDAO() {
			return new MySql_UsuarioDAO();
		
		}

		@Override
		public I_Articulo getArticuloDAO() {
			// TODO Auto-generated method stub
			return new MySql_ArticuloDAO();
		}

		@Override
		public I_Persona getPersonaDAO() {
			// TODO Auto-generated method stub
			return new MySql_PersonaDAO();
		}

		@Override
		public I_Movimiento getMovimientoDAO() {
			// TODO Auto-generated method stub
			return new MySql_MovimientoDAO();
		}
		
		
	
	
}
