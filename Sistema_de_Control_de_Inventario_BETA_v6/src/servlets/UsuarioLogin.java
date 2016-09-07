package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





import org.apache.commons.codec.digest.DigestUtils;

import beans.PersonaBean;
import beans.UsuarioBean;
import dao.factory.DAOFactory;
import dao.interfaces.I_Usuario;

/**
 * Servlet implementation class UsuarioLogin
 */
@WebServlet("/UsuarioLogin")
public class UsuarioLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//PRIMERA VEZ CONEXION O REACTIVAR CUENTA
		String usuario=request.getParameter("idpu");
		String password=request.getParameter("cod");
	
		System.out.println(usuario);
		System.out.println(password);
		UsuarioBean usuarioL=new UsuarioBean();
		usuarioL.setUsuario(usuario);
		usuarioL.setPassword(password);
		
		try{
			
			DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Usuario usuarioDao = dao.getUsuarioDAO();
			PersonaBean persona = usuarioDao.ingresar(usuarioL);
		
			
			if(persona.getEstadoCon()==0||persona.getEstadoCuenta()==0){
				
				HttpSession sesion= request.getSession();
				sesion.setAttribute("misesion", persona);
				log("entro el usuario por primera vez");
				getServletContext().getRequestDispatcher("/restablecerPassword.jsp").forward(request, response);
				
				
			}else{
				response.sendRedirect("index.jsp");
			}
			
		}catch(Exception e){
			System.out.print(e.getMessage());
			response.sendRedirect("PageError.jsp");
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String usuario=request.getParameter("usuario");
		String password=request.getParameter("pass");
		
		
		UsuarioBean usuarioL=new UsuarioBean();
		usuarioL.setUsuario(usuario);
		usuarioL.setPassword(DigestUtils.md5Hex(password));
		System.out.println(usuarioL.getPassword());

		
		
		try{
			
			DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Usuario usuarioDao = dao.getUsuarioDAO();
			PersonaBean persona = usuarioDao.ingresar(usuarioL);
		
			if(persona==null){
			
				request.setAttribute("error3", "La cuenta ha sido desactivada o no existe");
				getServletContext().getRequestDispatcher("/ErrorD.jsp").forward(request, response);
			}else{
				HttpSession sesion= request.getSession();
				sesion.setAttribute("misesion", persona);
				log("entro el usuario");
				response.sendRedirect("Articulo?ph=ifsLa");
				
			}
			
		}catch(Exception e){
			System.out.print(e.getMessage());
			response.sendRedirect("index.jsp");
		}
		
	}

}
