package dao.interfaces;

import java.util.Vector;

import beans.ArticuloBean;
import beans.MovimientoBean;

public interface I_Articulo {

	 public Vector<ArticuloBean> listar(); 
	 public boolean agregar(ArticuloBean articulo);
	 public boolean restar(MovimientoBean movimiento);
	 public boolean modificar(ArticuloBean articulo);
	 public Vector<ArticuloBean> listarAgotados();
	 public boolean deshabilitar(int codigo);
	 public boolean habilitar(int codigo);
}
