package dao.interfaces;

import java.util.ArrayList;
import beans.AsistenciasBean;
import beans.EmpleadoBean;
import beans.JustificacionBean;


public interface I_Asistencias {

	
	public ArrayList<EmpleadoBean> listarEmpleados(int est);
	public boolean registrarAsistenciaEnt(String dni,int estado);
	public int obtenerEstado(String dni);
	public boolean registrarInasistencias(String dni);
	public boolean estadoInasistencias();
	public ArrayList<AsistenciasBean> listarAsistencias(String dni);
}
