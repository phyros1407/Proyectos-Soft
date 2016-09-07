package dao.interfaces;

import java.util.ArrayList;
import beans.AsistenciasBean;
import beans.EmpleadoBean;
import beans.JustificacionBean;


public interface I_Asistencias {

	
	public ArrayList<EmpleadoBean> listarEmpleados(int est);
	public boolean registrarAsistenciaEnt(int dni,int estado);
	public int obtenerEstado(int dni);
	public boolean registrarInasistencias(int dni);
	public boolean estadoInasistencias();
	public ArrayList<AsistenciasBean> listarAsistencias(int dni);
}
