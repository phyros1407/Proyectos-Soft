package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.EmpleadoBean;
import beans.ResponseObject;
import dao.factory.DAOFactory;
import dao.interfaces.I_Empleado;
import dao.interfaces.I_Parametro;

/**
 * Servlet implementation class Seguro
 */
@WebServlet("/Seguro")
public class Seguro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Seguro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
			String accion = request.getParameter("accion");

			
			if (accion.equalsIgnoreCase("buscarA")) {
				System.out.println("Entro al buscar");
				String dniJ = request.getParameter("dniJ");

				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				
				I_Empleado emple= dao.getEmpleadoDAO();
				EmpleadoBean empleadoB = emple.obtenerSeguros(Integer.parseInt(dniJ));
				
				
				ResponseObject responseobj = null;

				if (empleadoB != null) {
					responseobj = new ResponseObject();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					responseobj.setSuccess(true);
					responseobj.setObject(empleadoB);
				}

				response.getWriter().write(new Gson().toJson(responseobj));
				System.out.println("json" + new Gson().toJson(responseobj));

			}
			
			if(accion.equalsIgnoreCase("modificar")){
			HttpSession sesion=request.getSession();
			if(sesion.getAttribute("sesion")==null){
				response.sendRedirect("login.jsp");
			}else{
			PrintWriter out = response.getWriter();
			
			double pSeguro1=Double.parseDouble(request.getParameter("porcentaje"));
			String dni=request.getParameter("dni");
			
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Parametro parametro=dao.getParametroDAO();
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	        response.setDateHeader("Expires", 0); // Proxies.		
			if(parametro.actualizarParametros(dni,pSeguro1)){
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('Actualización exitosa');");
				   out.println("location='seguro.jsp';");
				   out.println("</script>");
			}else{
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('No se logró guardar el nuevo porcentaje, inténtelo de nuevo luego');");
				   out.println("location='seguro.jsp';");
				   out.println("</script>");	
			}
			}
			}
		}

	}


