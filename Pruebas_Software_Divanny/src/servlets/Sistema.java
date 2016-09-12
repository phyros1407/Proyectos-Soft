package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.websocket.Session;

import beans.EmpleadoBean;
import dao.factory.DAOFactory;
import dao.interfaces.I_Asistencias;
import dao.interfaces.I_Empleado;

/**
 * Servlet implementation class Sistema
 */
@WebServlet("/Sistema")
public class Sistema extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sistema() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion=request.getSession();
		sesion.setAttribute("sesion", null);
		response.sendRedirect("login.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Empleado empleado=dao.getEmpleadoDAO();
		EmpleadoBean empleados;
		HttpSession sesion=request.getSession();
		empleados=empleado.login(request.getParameter("usuario"), request.getParameter("contraseña"));
		
		if(empleados==null){
			out.println("<script type=\"text/javascript\">");
			   out.println("alert('Usuario o contraseña incorrecta');");
			   out.println("location='login.jsp';");
			   out.println("</script>");
		}else{
			if(empleados.getEstado().equalsIgnoreCase("I")){
					out.println("<script type=\"text/javascript\">");
				   out.println("alert('Su cuenta está inactiva comuniquese con su administrador');");
				   out.println("location='login.jsp';");
				   out.println("</script>");
			}else{
				System.out.println("inicio");
			sesion.setAttribute("sesion", empleados);
			response.sendRedirect("inicio.jsp");
			}
		}	
		
	}

}
