package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import beans.UsuarioBean;
import dao.factory.DAOFactory;
import dao.interfaces.I_Usuario;

/**
 * Servlet implementation class RestablecerContraseña
 */
@WebServlet("/RestablecerPassword")
public class RestablecerPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestablecerPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		String password=request.getParameter("pass1");
		
		System.out.println(password);
		
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Usuario usuarioDao = dao.getUsuarioDAO();
		
		UsuarioBean usuario=new UsuarioBean();
		usuario.setId(id);
		usuario.setPassword(DigestUtils.md5Hex(password));
		
		
		if(usuarioDao.restablecerCon(usuario)){
			log("se restablecio contraseña exitosamente");
			response.sendRedirect("Articulo?ph=ifsLa");
		}else{
			response.sendRedirect("restablecerPassword.jsp");
		}
		
		
	}

}
