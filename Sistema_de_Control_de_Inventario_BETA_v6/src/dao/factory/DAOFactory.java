package dao.factory;
import dao.factory.MySqlDAOFactory;
import dao.interfaces.*;
public abstract class DAOFactory {
	
	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;
	public static final int ORACLE = 3;
	
	public abstract I_Usuario getUsuarioDAO();
	public abstract I_Articulo getArticuloDAO();
	public abstract I_Persona getPersonaDAO();
	public abstract I_Movimiento getMovimientoDAO();
	
	
	
	public static DAOFactory getDAOFactory(int factory){
		switch (factory) {
			case MYSQL:
				return new MySqlDAOFactory();

			/*case SQLSERVER:
				return new SqlServerDAOFactory();			
					break;
				
			case ORACLE:
				return new OracleDAOFactory();
					break;*/
	
			default:
					return null;
		}

	}

	
	
}
