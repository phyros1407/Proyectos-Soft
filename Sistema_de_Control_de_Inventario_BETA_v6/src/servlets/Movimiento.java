package servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.MovimientoBean;
import beans.ResponseObject;
import dao.factory.DAOFactory;
import dao.interfaces.I_Movimiento;


/**
 * Servlet implementation class Movimiento
 */
@WebServlet("/Movimiento")
public class Movimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Movimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String accion = request.getParameter("ph");
		
		//LISTAR MOVIMIENTOS
		if(accion.equalsIgnoreCase("ifsLm")){

			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Movimiento movidao=dao.getMovimientoDAO();
			
			Vector<MovimientoBean> movimientos=movidao.listar();
			
			if(movimientos!=null){
				
				request.setAttribute("movimientos", movimientos);
				getServletContext().getRequestDispatcher("/movimientos.jsp").forward(request, response);
				
			}else{
				
				response.sendRedirect("Articulo");
				
			}
		}
		
		
		//LISTAR MIS MOVIMIENTOS
		if(accion.equalsIgnoreCase("ifsLmm")){
			System.out.println("mis movimientos");
			String cod=request.getParameter("npp");
			int le=Integer.parseInt(request.getParameter("t"));
			
			int codigo=Integer.parseInt(cod.substring(3, 3+le));
			System.out.println(codigo);
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Movimiento movidao=dao.getMovimientoDAO();
			
			Vector<MovimientoBean> movimientos=movidao.listarxPer(codigo);
			
			if(movimientos!=null){
				
				request.setAttribute("movimientos", movimientos);
				getServletContext().getRequestDispatcher("/misMovimientos.jsp").forward(request, response);
				
			}else{
				
				response.sendRedirect("Articulo");
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String accion = request.getParameter("ph");
		
		if(accion.equalsIgnoreCase("detalle")){
			try{
				int codigo = Integer.parseInt(request.getParameter("mov"));
				
				DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Movimiento mov = dao.getMovimientoDAO();
				MovimientoBean  movi = mov.buscar(codigo);
				ResponseObject responseobj=null;
				if(movi!=null){
					responseobj=new ResponseObject();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					responseobj.setSuccess(true);
					responseobj.setObject(movi);
				}
				response.getWriter().write(new Gson().toJson(responseobj));
				 
				
				System.out.println("json" + new Gson().toJson(responseobj));
			}catch(Exception e){
				response.sendRedirect("PageError.jsp");
				System.out.println(e.getMessage());
			}
		}
		
	}

}
