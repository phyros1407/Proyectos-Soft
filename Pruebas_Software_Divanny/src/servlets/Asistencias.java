package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import beans.EmpleadoBean;
import dao.factory.DAOFactory;
import dao.interfaces.I_Asistencias;

/**
 * Servlet implementation class ListarEmpleado
 */
@WebServlet("/Asistencias")
public class Asistencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Asistencias() {
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
		
		DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Asistencias asistencias=dao.getAsistenciasDAO();
		int est=Integer.parseInt((request.getParameter("est")));
		ArrayList<EmpleadoBean> empleados= new ArrayList<EmpleadoBean>();
		empleados=asistencias.listarEmpleados(est);
		String titulo="";
		
		switch(est){
		case 0: titulo="Registro de Entrada"; break;
		case 1: titulo="Registro de Ingreso al Refrigerio"; break;
		case 2: titulo="Registro de Salida del Refrigerio"; break;
		case 3: titulo="Registro de Salida"; break;
		}
		
		
		request.setAttribute("titulo", titulo);
		request.setAttribute("estadoOpcion", est);
		request.setAttribute("empleados", empleados);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
		getServletContext().getRequestDispatcher("/asistencias.jsp").forward(request, response);
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
		PrintWriter out = response.getWriter();
		DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Asistencias asistencias=dao.getAsistenciasDAO();
		String dni=request.getParameter("dni");
		
		int estado=asistencias.obtenerEstado(dni);
		System.out.println(estado+"Estado ingreso");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
		if(asistencias.registrarAsistenciaEnt(dni,estado)){
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Se registró correctamente la asistencia');");
			out.println("location='Asistencias?est="+estado+"'");
			out.println("</script>");	
		}else{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Registro fallido, fuera de hora');");
			out.println("location='Asistencias?est="+estado+"'");
			out.println("</script>");	
		}
		System.out.println(estado+"servlet");
		}
		
		
	}

}
