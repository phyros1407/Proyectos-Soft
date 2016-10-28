package dao.interfaces;
import java.util.ArrayList;

import beans.ParametrosBean;

public interface I_Parametro {
	
	public ArrayList<ParametrosBean> obtenerParametros();
	public boolean actualizarParametros(String id,double porcentaje);

}
