package dao.interfaces;

import java.util.Vector;

import beans.MovimientoBean;

public interface I_Movimiento {

	
	public boolean crearMovimiento(MovimientoBean movimiento);
	public Vector<MovimientoBean> listar();
	public Vector<MovimientoBean>listarxPer(int codigo);
	public MovimientoBean buscar(int codigo);
}
