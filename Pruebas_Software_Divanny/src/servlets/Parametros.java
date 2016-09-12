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
import javax.swing.JOptionPane;

import beans.ParametrosBean;
import dao.factory.DAOFactory;
import dao.interfaces.I_Parametro;

/**
 * Servlet implementation class Parametros
 */
@WebServlet("/Parametros")
public class Parametros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Parametros() {
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
    	PrintWriter out = response.getWriter();
		DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Parametro parametro=dao.getParametroDAO();
		ArrayList<ParametrosBean> parametros=new ArrayList<ParametrosBean>();
		parametros=parametro.obtenerParametros();
		System.out.println(parametros);
		if (parametros==null){
			out.println("<script type=\"text/javascript\">");
			   out.println("alert('No existen empleados en el sistema');");
			   out.println("location='login.jsp';");
			   out.println("</script>");
		}else{
		request.setAttribute("parametros", parametros);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
		getServletContext().getRequestDispatcher("/actualizarParametros.jsp").forward(request, response);
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
		PrintWriter out = response.getWriter();
		double pSeguro1=Double.parseDouble(request.getParameter("seguro1"));
		double pSeguro2=Double.parseDouble(request.getParameter("seguro2"));
		double pSeguro3=Double.parseDouble(request.getParameter("seguro3"));
		
		DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Parametro parametro=dao.getParametroDAO();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.		
		if(parametro.actualizarParametros(1,pSeguro1)&&parametro.actualizarParametros(2,pSeguro2)&&parametro.actualizarParametros(3,pSeguro3)){
			out.println("<script type=\"text/javascript\">");
			   out.println("alert('Actualización exitosa');");
			   out.println("location='Parametros';");
			   out.println("</script>");
		}else{
			out.println("<script type=\"text/javascript\">");
			   out.println("alert('No se logró guardar el nuevo porcentaje, inténtelo de nuevo luego');");
			   out.println("location='Parametros';");
			   out.println("</script>");	
		}
		}
	}

}
