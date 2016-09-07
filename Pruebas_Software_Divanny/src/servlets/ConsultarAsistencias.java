package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AsistenciasBean;
import beans.EmpleadoBean;
import dao.factory.DAOFactory;
import dao.interfaces.I_Asistencias;

/**
 * Servlet implementation class ConsultarAsistencias
 */
@WebServlet("/ConsultarAsistencias")
public class ConsultarAsistencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarAsistencias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Asistencias asistencias=dao.getAsistenciasDAO();
		ArrayList<EmpleadoBean> empleados= new ArrayList<EmpleadoBean>();
		
		empleados=asistencias.listarEmpleados(0);
		
		request.setAttribute("empleados", empleados);
		getServletContext().getRequestDispatcher("/consultarAsistencias.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		int dni=Integer.parseInt(request.getParameter("dni"));
		String nombreC=request.getParameter("nombreC");
		DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Asistencias asistencias=dao.getAsistenciasDAO();
		ArrayList<AsistenciasBean> asistencia= new ArrayList<AsistenciasBean>();
		
		asistencia=asistencias.listarAsistencias(dni);
		if(asistencia==null){
			out.println("<script type=\"text/javascript\">");
			   out.println("alert('No hay registros de asistencia para este empleado');");
			   out.println("location='ConsultarAsistencias';");
			   out.println("</script>");
		}else{
		request.setAttribute("nombreC",nombreC);
		request.setAttribute("asistencia", asistencia);
		getServletContext().getRequestDispatcher("/consultarAsistenciasP.jsp").forward(request, response);
		}
	}

}
