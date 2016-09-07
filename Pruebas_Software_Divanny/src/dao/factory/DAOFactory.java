package dao.factory;

import dao.interfaces.I_Asistencias;
import dao.interfaces.I_Empleado;
import dao.interfaces.I_Justificacion;
import dao.interfaces.I_PagosPlanilla;
import dao.interfaces.I_Parametro;
import dao.factory.DAOFactory;
import dao.factory.MySqlDAOFactory;



	
	public abstract class DAOFactory {
		public static final int MYSQL = 1;
		public static final int SQLSERVER = 2;
		public static final int ORACLE = 3;
		
		public abstract I_Asistencias getAsistenciasDAO();
		public abstract I_Parametro getParametroDAO();
		public abstract I_Empleado getEmpleadoDAO();
		public abstract I_PagosPlanilla getPagosPlanillaDAO();
		public abstract I_Justificacion getJustificacionDAO();
		
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
