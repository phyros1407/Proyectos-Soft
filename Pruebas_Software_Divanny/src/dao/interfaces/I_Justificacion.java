package dao.interfaces;

import beans.AsistenciasBean;
import beans.EmpleadoBean;
import beans.JustificacionBean;

public interface I_Justificacion {
	public boolean registrarJustificacion(JustificacionBean just);
	public EmpleadoBean obtenerFechFaltas(int codigo);
	public boolean actualizarEstadoAsistencia(AsistenciasBean asi);
}
