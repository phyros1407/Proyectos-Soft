package servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.gson.Gson;

import beans.ArticuloBean;

import beans.PersonaBean;
import beans.ResponseObject;
import beans.UsuarioBean;
import dao.factory.DAOFactory;
import dao.interfaces.I_Articulo;

import dao.interfaces.I_Persona;
import dao.interfaces.I_Usuario;
import domain.Email;
import domain.Metodos;

/**
 * Servlet implementation class Persona
 */
@WebServlet("/Persona")
public class Persona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Persona() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
			
			DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Persona personaDao = dao.getPersonaDAO();
			Vector<PersonaBean> personas=personaDao.listar();
			
			if(personas==null){
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}else{
				log("se lleno la tabla");
				request.setAttribute("personas", personas);
				getServletContext().getRequestDispatcher("/usuario.jsp").forward(request, response);
			}
			
		}catch(Exception e){
			System.out.print(e.getMessage());
			response.sendRedirect("PageError.jsp");
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String accion = request.getParameter("ph");
		
		//PERSONA AGREGAR
		if(accion.equalsIgnoreCase("ifsAu")){
			
			String nombre=request.getParameter("nombre");
			String apellidoP=request.getParameter("apellidoP");
			String apellidoM=request.getParameter("apellidoM");
			String telefono=request.getParameter("celular");
			String correo=request.getParameter("correo");
			String permiso=request.getParameter("permiso");
			
			PersonaBean persona=new PersonaBean();
			persona.setNombre(nombre.trim());
			persona.setApellido(apellidoP.trim()+" "+apellidoM.trim());
			persona.setTelefono(telefono.trim());
			persona.setCorreo(correo.trim());
			
			try{
				
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Persona personaDao=dao.getPersonaDAO();
				
				//Metodos meth= new Metodos();
				//String usu=meth.generarUsuario(nombre,apellidoP,apellidoM);
				int id_persona=personaDao.agregar(persona);
				String password=Metodos.generarPassword();
				String genpass=DigestUtils.md5Hex(password);
				
				System.out.println(genpass);
				
				if(id_persona!=0){
					log("se agrego correctamente LA PERSONA");
					
					I_Usuario usuarioDao=dao.getUsuarioDAO();
					UsuarioBean usuario=new UsuarioBean();
					
					usuario.setId(id_persona);
					//usuario.setUsuario(usu.toLowerCase());
					usuario.setPassword(genpass);
					usuario.setPerfil(permiso);
					
					if(usuarioDao.registrar(usuario)){
						String sub="Usuario y password - Sistema de Inventario de Fabrica de Software";
						String mess="Estimado(a) Sr.(a) "+persona.getNombre().toUpperCase()+" "+persona.getApellido().toUpperCase()+",\n"
								+ "\n"
								+"Se le envia los siguientes datos :\n"
								+"\n"
								+"@correo : "+persona.getCorreo()+"\n"
								+"\n"
								+"para que acceda al sistema mediante este link : \n"
								/*AZURE*/       
								//+"http://mocoso.azurewebsites.net/Sistema_de_Control_de_Inventario/UsuarioLogin?idpu="+usuario.getUsuario()+"&cod="+usuario.getPassword()+"\n"
								//JELASTIC		
								//+"http://pruebas.j.facilcloud.com/UsuarioLogin?idpu="+persona.getCorreo()+"&cod="+usuario.getPassword()+"\n"
								+"http://172.25.18.49:8080/Sistema_de_Control_de_Inventario_BETA_v4/UsuarioLogin?idpu="+persona.getCorreo()+"&cod="+usuario.getPassword()+"\n"
								+"\n"
								+"Saludos \n"
								+ "\n"
								+"Atte.\n"
								+"Administrador del Sistema.";
						
						Email mail= new Email();
						mail.sendEmail(persona.getCorreo(),mess,sub);
						
						Vector<PersonaBean> personas=personaDao.listar();
						request.setAttribute("mensaje"," Usuario creado correctamente, se le envio un correo con la informacion  de su cuenta" );
						request.setAttribute("personas", personas);
						getServletContext().getRequestDispatcher("/usuario.jsp").forward(request, response);
					}
					
					
				}else{
					Vector<PersonaBean> personas=personaDao.listar();
					request.setAttribute("mensaje","Error! El usuario ya existe");
					request.setAttribute("personas", personas);
					getServletContext().getRequestDispatcher("/usuario.jsp").forward(request, response);
				}
				
			}catch(Exception e){
				System.out.print(e.getMessage());
				response.sendRedirect("PageError.jsp");
			}
		}
		
		
		
		//PERSONA ACTIVAR
		if(accion.equalsIgnoreCase("ifsCu")){

			int codigo=Integer.parseInt(request.getParameter("codigo"));
			
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Usuario usuariodao=dao.getUsuarioDAO();
		
			UsuarioBean usuario=usuariodao.buscarUsuario(codigo);
			
			I_Persona personadao=dao.getPersonaDAO();
			PersonaBean persona=personadao.buscarPersona(codigo);
			
			try{
						String sub="Restablecer Account - Sistema de Inventario de Fabrica de Software";
						String mess="Estimado(a) Sr.(a) "+persona.getNombre().toUpperCase()+" "+persona.getApellido().toUpperCase()+",\n"
								+ "\n"
								+"Se le envia los siguientes datos para la reactivacion de su cuenta:\n"
								+"\n"
								+"@correo : "+persona.getCorreo()+"\n"
								+"\n"
								+"puede restablecer su password mediante este link : \n"
								/*AZURE*/            
								//+"http://mocoso.azurewebsites.net/Sistema_de_Control_de_Inventario/UsuarioLogin?idpu="+usuario.getUsuario()+"&cod="+usuario.getPassword()+"\n"
								//JELASTIC		
								//+"http://pruebas.j.facilcloud.com/UsuarioLogin?idpu="+persona.getCorreo()+"&cod="+usuario.getPassword()+"\n"
								+"http://172.25.18.49:8080/Sistema_de_Control_de_Inventario_BETA_v4/UsuarioLogin?idpu="+persona.getCorreo()+"&cod="+usuario.getPassword()+"\n"+"\n"
								+"Saludos \n"
								+ "\n"
								+"Atte.\n"
								+"Administrador del Sistema.";
						
						Email mail= new Email();
						mail.sendEmail(persona.getCorreo(),mess,sub);
						log("Se envio el correo para reactivar el account");
						
						I_Persona personaDao=dao.getPersonaDAO();
						Vector<PersonaBean> personas=personaDao.listar();
						request.setAttribute("mensaje","Solicitud de activacion completa, el usuario recibira un correo con las indicaciones" );
						request.setAttribute("personas", personas);
						getServletContext().getRequestDispatcher("/usuario.jsp").forward(request, response);
					
	
			}catch(Exception e){
				System.out.print(e.getMessage());
				response.sendRedirect("PageError.jsp");
			}
			
		}
		
		
		//PERSONA DESACTIVAR
		
		if(accion.equalsIgnoreCase("ifsDu")){

			int codigo=Integer.parseInt(request.getParameter("codigo"));
			
			
			
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Usuario usuariodao=dao.getUsuarioDAO();
			I_Persona personadao = dao.getPersonaDAO();
			
			if(personadao.obtenerPerfil(codigo)==1){
				if(personadao.cantidadAdm()<=1){
					Vector<PersonaBean> personas=personadao.listar();
					request.setAttribute("mensaje","Error! No se puedo Desabilitar al usuario" );
					request.setAttribute("personas", personas);
					getServletContext().getRequestDispatcher("/usuario.jsp").forward(request, response);
				}else{
					if(usuariodao.desactivar(codigo)){
						
						
						if(personadao.eliminar(codigo)){
							
							
							Vector<PersonaBean> personas=personadao.listar();
							request.setAttribute("mensaje","Se desabilito el usuario correctamente" );
							request.setAttribute("personas", personas);
							getServletContext().getRequestDispatcher("/usuario.jsp").forward(request, response);
						
						}else{
							Vector<PersonaBean> personas=personadao.listar();
							request.setAttribute("mensaje","Error! No se puedo Desabilitar al usuario" );
							request.setAttribute("personas", personas);
							getServletContext().getRequestDispatcher("/usuario.jsp").forward(request, response);
						}
					}
				}
			}else{

				if(usuariodao.desactivar(codigo)){
					
					
					if(personadao.eliminar(codigo)){
						
						
						Vector<PersonaBean> personas=personadao.listar();
						request.setAttribute("mensaje","Se desabilito el usuario correctamente" );
						request.setAttribute("personas", personas);
						getServletContext().getRequestDispatcher("/usuario.jsp").forward(request, response);
					
					}else{
						Vector<PersonaBean> personas=personadao.listar();
						request.setAttribute("mensaje","Error! No se puedo Desabilitar al usuario" );
						request.setAttribute("personas", personas);
						getServletContext().getRequestDispatcher("/usuario.jsp").forward(request, response);
					}
				}
			}
			
			
		}
		
		
		//PERSONA MODIFICAR
		
		if(accion.equalsIgnoreCase("ifsMu")){
			
			int codigo=Integer.parseInt(request.getParameter("codigo"));
			String nombre=request.getParameter("nombre");
			String apellidoP=request.getParameter("apellidoP");
			String apellidoM=request.getParameter("apellidoM");
			String telefono=request.getParameter("celular");
			String correo=request.getParameter("correo");
			int permiso=Integer.parseInt(request.getParameter("permiso"));
			
			
			PersonaBean persona=new PersonaBean();
			persona.setId(codigo);
			persona.setNombre(nombre);
			persona.setApellido(apellidoP.trim()+" "+apellidoM.trim());
			persona.setCorreo(correo);
			persona.setTelefono(telefono);
			persona.setPerfilUsuario(permiso);
			
			
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Persona perdao=dao.getPersonaDAO();
			
			
			if(perdao.actualizarPersona(persona)){
				
				Vector<PersonaBean> personas=perdao.listar();
				request.setAttribute("mensaje","Se actualizo correctamente los datos del usuario" );
				request.setAttribute("personas", personas);
				getServletContext().getRequestDispatcher("/usuario.jsp").forward(request, response);
			}else{
				Vector<PersonaBean> personas=perdao.listar();
				request.setAttribute("mensaje","Error! no se pudo actualizar la informacion del usuario");
				request.setAttribute("personas", personas);
				getServletContext().getRequestDispatcher("/usuario.jsp").forward(request, response);
			
				
			}
			
		}
		
		//BUSCAR USUARIO
		if(accion.equalsIgnoreCase("buscar")){
			
			
			
			try{
				int codigo=Integer.parseInt(request.getParameter("codigo"));
				
				DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Persona pe = dao.getPersonaDAO();
				PersonaBean  pers = pe.buscarPersona(codigo);
				ResponseObject responseobj=null;
				if(pers!=null){
					responseobj=new ResponseObject();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					responseobj.setSuccess(true);
					responseobj.setObject(pers);
				}
				response.getWriter().write(new Gson().toJson(responseobj));
				 
				
				System.out.println("json" + new Gson().toJson(responseobj));
			}catch(Exception e){
				System.out.println(e.getMessage());
				response.sendRedirect("PageError.jsp");
			}
			
		}
		
		//CONFIGURAR USUARIO
		if(accion.equalsIgnoreCase("config")){
			
			int codigo=Integer.parseInt(request.getParameter("codU"));
			String nombre =request.getParameter("nombreUsu");
			String apellidoP=request.getParameter("apellidoPat");
			String apellidoM=request.getParameter("apellidoMat");
			String telefono=request.getParameter("telefonoUsu");
			String contraseña = request.getParameter("passUsu");
			
			PersonaBean persona = new PersonaBean();
			
			persona.setId(codigo);
			persona.setNombre(nombre);
			persona.setApellido(apellidoP+" "+apellidoM);
			persona.setTelefono(telefono);
			
			if(!(contraseña.trim().equals(""))){
				persona.setContraseña(DigestUtils.md5Hex(contraseña));
			}
			
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Persona per=dao.getPersonaDAO();
			I_Articulo articuloDao = dao.getArticuloDAO();
			
			if(per.configurarUsuario(persona)){
				
				Vector<ArticuloBean> articulos=articuloDao.listar();
				request.setAttribute("mensaje","Se actualizo correctamente sus datos" );
				request.setAttribute("articulos", articulos);
				getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
				
			}else{
				Vector<ArticuloBean> articulos=articuloDao.listar();
				request.setAttribute("mensaje","Error! ocurrio un inconveniente durante la operacion" );
				request.setAttribute("articulos", articulos);
				getServletContext().getRequestDispatcher("/inventario.jsp").forward(request, response);
				
			}
			
			
			
		}
		
	}

}
