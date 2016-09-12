package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.EmpleadoBean;
import beans.ObreroBean;
import beans.VendedorBean;
import dao.factory.DAOFactory;
import dao.interfaces.I_Empleado;

/**
 * Servlet implementation class Vendedor
 */
@WebServlet("/Tipo_Empleado")
public class Tipo_Empleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tipo_Empleado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion=request.getSession();
		if(sesion.getAttribute("sesion")==null){
			response.sendRedirect("login.jsp");
		}else{
		
		int tipo=Integer.parseInt(request.getParameter("tipo"));
		
		DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Empleado ven=dao.getEmpleadoDAO();
		ArrayList<EmpleadoBean> empleados=ven.listarTipo(tipo);
		if(empleados!=null&&tipo==5){
			request.setAttribute("empleados", empleados);
			getServletContext().getRequestDispatcher("/ventas.jsp").forward(request, response);
		}else if(empleados!=null&&tipo==4){
			request.setAttribute("empleados", empleados);
			getServletContext().getRequestDispatcher("/horasExtra.jsp").forward(request, response);
		}else if(empleados==null&&tipo==5){
			request.setAttribute("mensaje", "Lista de Vendedores no disponible");
			getServletContext().getRequestDispatcher("/ventas.jsp").forward(request, response);
		}else{
			request.setAttribute("mensaje", "Lista de Trabajadores no disponible");
			getServletContext().getRequestDispatcher("/ventas.jsp").forward(request, response);
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion=request.getSession();
		if(sesion.getAttribute("sesion")==null){
			response.sendRedirect("login.jsp");
		}else{
		String accion=request.getParameter("accion");
		
		if(accion.equalsIgnoreCase("venta")){
			
			int dni=Integer.parseInt(request.getParameter("dni"));
			System.out.println(dni);
			double valor=Double.parseDouble(request.getParameter("valor"));
			System.out.println(valor);
			String fecha=request.getParameter("fecha").toString();
			System.out.println(fecha);
			
			VendedorBean vendedor=new VendedorBean();
			vendedor.setDni_trab(dni);
			vendedor.setDia(fecha);
			vendedor.setVentas(valor);
			
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Empleado ven=dao.getEmpleadoDAO();
			ArrayList<EmpleadoBean> empleados=ven.listarTipo(5);
			if(ven.guardarVen(vendedor)){
				request.setAttribute("empleados", empleados);
				request.setAttribute("mensaje", "Registro de Venta Exitoso");
				getServletContext().getRequestDispatcher("/ventas.jsp").forward(request, response);
			}else{
				request.setAttribute("empleados", empleados);
				request.setAttribute("mensaje", "Fallo en el Registro de venta");
				getServletContext().getRequestDispatcher("/ventas.jsp").forward(request, response);
			}
		}
		
		if(accion.equalsIgnoreCase("hor")){
			
			int dni=Integer.parseInt(request.getParameter("dni"));
			System.out.println(dni);
			double horas=Double.parseDouble(request.getParameter("valor"));
			System.out.println(horas);
			String fecha=request.getParameter("fecha").toString();
			System.out.println(fecha);
			
			ObreroBean obrero=new ObreroBean();
			obrero.setDni_trab(dni);
			obrero.setHor_ext(horas);
			obrero.setDia(fecha);
			
			
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Empleado obr=dao.getEmpleadoDAO();
			ArrayList<EmpleadoBean> empleados=obr.listarTipo(4);
			if(obr.guardarHor(obrero)){
				request.setAttribute("empleados", empleados);
				request.setAttribute("mensaje", "Registro de Horas Exitoso");
				getServletContext().getRequestDispatcher("/horasExtra.jsp").forward(request, response);
			}else{
				request.setAttribute("empleados", empleados);
				request.setAttribute("mensaje", "Fallo en el Registro de horas");
				getServletContext().getRequestDispatcher("/horasExtra.jsp").forward(request, response);
			}

		}
	}
	}

}
