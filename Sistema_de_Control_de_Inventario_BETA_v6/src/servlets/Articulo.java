package servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ArticuloBean;
import beans.MovimientoBean;
import dao.factory.DAOFactory;
import dao.interfaces.I_Articulo;
import dao.interfaces.I_Movimiento;
import domain.HolaMundoPDF;

/**
 * Servlet implementation class Articulo
 */
@WebServlet("/Articulo")
public class Articulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Articulo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String accion=request.getParameter("ph");
		
		//LISTAR AGOTADOS
		if(accion.equalsIgnoreCase("ifsLaa")){

			try{
				
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Articulo articuloDao = dao.getArticuloDAO();
				Vector<ArticuloBean> articulosA=articuloDao.listarAgotados();
				
				if(articulosA==null){
					request.setAttribute("mensaje", "No hay articulos fuera de stock");
					request.setAttribute("articulosA", articulosA);
					getServletContext().getRequestDispatcher("/inventarioAgotado.jsp").forward(request, response);
				}else{
					log("se lleno la tabla de agotados");
					request.setAttribute("articulosA", articulosA);
					getServletContext().getRequestDispatcher("/inventarioAgotado.jsp").forward(request, response);
				}
				
			}catch(Exception e){
				System.out.print(e.getMessage());
				response.sendRedirect("PageError.jsp");
			}
		}else{
			
		}
		
		//LISTAR ARTICULOS
		if(accion.equalsIgnoreCase("ifsLa")){
			try{
				
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Articulo articuloDao = dao.getArticuloDAO();
				Vector<ArticuloBean> articulos=articuloDao.listar();
				
				HolaMundoPDF pdf1=new HolaMundoPDF();
				pdf1.pdf();
				
				if(articulos==null){
					request.setAttribute("mensaje", "No hay articulos disponibles");
					request.setAttribute("articulos", articulos);
					getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
				}else{
					log("se lleno la tabla");
					request.setAttribute("articulos", articulos);
					getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
				}
				
			}catch(Exception e){
				System.out.print(e.getMessage());
				response.sendRedirect("PageError.jsp");
			}
			
		}
		
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String accion=request.getParameter("ph");
		
		//AGREGAR ARTICULOS
		if(accion.equalsIgnoreCase("ifsAa")){
			
			String nombre=request.getParameter("nombre");
			String medida=request.getParameter("medida");
			int cantidad=Integer.parseInt(request.getParameter("cantidad"));
			
			ArticuloBean articulo=new ArticuloBean();
			articulo.setNombre(nombre);
			articulo.setMedida(medida);
			articulo.setCantidad(cantidad);
			
			
			try{
				
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Articulo articuloDao = dao.getArticuloDAO();
				
				if(articuloDao.agregar(articulo)){
					log("se agrego correctamente");
					Vector<ArticuloBean> articulos=articuloDao.listar();
					request.setAttribute("mensaje","El Articulo se agrego correctamente" );
					request.setAttribute("articulos", articulos);
					getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
					
					
				}else{
					Vector<ArticuloBean> articulos=articuloDao.listar();
					request.setAttribute("mensaje","Error! ocurrio un inconveniente durante la operacion" );
					request.setAttribute("articulos", articulos);
					getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
					
				}
				
			}catch(Exception e){
				System.out.print(e.getMessage());
				response.sendRedirect("PageError.jsp");
				
			}
			
		}
		
		//MODIFICAR ARTICULOS
		if(accion.equalsIgnoreCase("ifsMa")){
			
			int id=Integer.parseInt(request.getParameter("codigo4"));
			int cantidad=Integer.parseInt(request.getParameter("c1"));
			
			
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Articulo artdao=dao.getArticuloDAO();
			ArticuloBean articulo =new ArticuloBean();
			articulo.setId(id);
			articulo.setCantidad(cantidad);
			
			if(artdao.modificar(articulo)){
				log("se modifico el articulo");
				Vector<ArticuloBean> articulos=artdao.listar();
				request.setAttribute("mensaje","Se actualizo el stock del articulo" );
				request.setAttribute("articulos", articulos);
				getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
			}else{
				log("No se modifico el articulo");
				Vector<ArticuloBean> articulos=artdao.listar();
				request.setAttribute("mensaje","Error! ocurrio un inconveniente durante la operacion" );
				request.setAttribute("articulos", articulos);
				getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
			}
		}
		
		
		//RETIRAR ARTICULOS
		if(accion.equalsIgnoreCase("ifsRa")){
			
			int codigo=Integer.parseInt(request.getParameter("codigo"));
			int codigoP=Integer.parseInt(request.getParameter("codigoP"));
			int retirada=Integer.parseInt(request.getParameter("retirada"));
			String descripcion=request.getParameter("comentario");
			
			System.out.println(descripcion);
			
			MovimientoBean movimiento=new MovimientoBean();
			movimiento.setId_articulo(codigo);
			movimiento.setId_usuario(codigoP);
			movimiento.setCantidad_retirada(retirada);
			movimiento.setDescripcion(descripcion);
			try{
				
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Articulo articuloDao = dao.getArticuloDAO();
				
				
				if(articuloDao.restar(movimiento)){
					
					I_Movimiento movimientodao = dao.getMovimientoDAO();
					log("se resto "+movimiento.getCantidad_retirada() );
					
					if(movimientodao.crearMovimiento(movimiento))
						log("Se guardo el movimiento");
					
						Vector<ArticuloBean> articulos=articuloDao.listar();
						request.setAttribute("mensaje", "Se retiro la cantidad solicitada, se genero un nuevo movimiento");
						request.setAttribute("articulos", articulos);
						getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
						
				}else{
					
					request.setAttribute("mensaje", "Error! ocurrio un inconveniente durante la operacion");
					Vector<ArticuloBean> articulos=articuloDao.listar();
					request.setAttribute("articulos", articulos);
					getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
					
				}
				
			}catch(Exception e){
				System.out.print(e.getMessage());
				response.sendRedirect("/PageError.jsp");
			}
		}
		
		
		//DESHABILITAR ARTICULOS
		if(accion.equalsIgnoreCase("ifsDa")){
			int codigo=Integer.parseInt(request.getParameter("codigo"));
			DAOFactory dao= DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Articulo arti= dao.getArticuloDAO();
			
			if(arti.deshabilitar(codigo)){

				Vector<ArticuloBean> articulos=arti.listar();
				request.setAttribute("mensaje", "Se deshabilito el articulo seleccionado con exito");
				request.setAttribute("articulos", articulos);
				getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
			}else{
				
				request.setAttribute("mensaje", "Error! ocurrio un inconveniente durante la operacion");
				Vector<ArticuloBean> articulos=arti.listar();
				request.setAttribute("articulos", articulos);
				getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);	
			}
			
		}
		
		
		
		//HABILITAR ARTICULOS
		if(accion.equalsIgnoreCase("ifsHa")){
			int codigo=Integer.parseInt(request.getParameter("codigo"));
			DAOFactory dao= DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Articulo arti= dao.getArticuloDAO();
					
			if(arti.habilitar(codigo)){
				
				Vector<ArticuloBean> articulos=arti.listar();
				request.setAttribute("mensaje", "Se habilito el articulo seleccionado con exito");
				request.setAttribute("articulos", articulos);
				getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
			}else{	
				
				request.setAttribute("mensaje", "Error! ocurrio un inconveniente durante la operacion");
				Vector<ArticuloBean> articulos=arti.listar();
				request.setAttribute("articulos", articulos);
				getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);	
			}
					
		}

	}

}
