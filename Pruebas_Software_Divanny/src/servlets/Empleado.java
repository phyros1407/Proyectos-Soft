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

import com.google.gson.Gson;

import beans.ContactoBean;
import beans.CorreoBean;
import beans.DetalleTrabajadorBean;
import beans.EmpleadoBean;
import beans.ResponseObject;
import beans.VendedorBean;
import dao.factory.DAOFactory;
import dao.interfaces.I_Empleado;

/**
 * Servlet implementation class Empleado
 */
@WebServlet("/Empleado")
public class Empleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Empleado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Empleado empleadodao=dao.getEmpleadoDAO();
		
		
		
		HttpSession sesion=request.getSession();
		if(sesion.getAttribute("sesion")==null){
			response.sendRedirect("login.jsp");
		}else{
			
			if(request.getParameter("accion")==null){
				
				
				ArrayList<EmpleadoBean> empleados=empleadodao.listar();
				
				request.setAttribute("empleados", empleados);
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		        response.setDateHeader("Expires", 0); // Proxies.
				getServletContext().getRequestDispatcher("/empleados.jsp").forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Empleado empleadodao=dao.getEmpleadoDAO();
		
		String accion = request.getParameter("accion");
		HttpSession sesion=request.getSession();
		if(sesion.getAttribute("sesion")==null){
			response.sendRedirect("login.jsp");
		}else{
		
			if(accion.equalsIgnoreCase("registrar")){
				//REGISTRAR
				System.out.println("SE ESTA REGISTRANDO------------------------");
				String perfil=request.getParameter("perfil");
				System.out.println(perfil);
				
				
				
				if(perfil.equalsIgnoreCase("5")){
					
					String dni=request.getParameter("dni");
					String nombre=request.getParameter("nombre");
					String apellidos=request.getParameter("apellidos");
					int comision=Integer.parseInt(request.getParameter("comision"));
					String direccion=request.getParameter("direccion");
					String[] contacto=request.getParameterValues("contacto");
					int seguro=Integer.parseInt(request.getParameter("seguro"));
					double medico=Double.parseDouble(request.getParameter("medico"));
					String correo=request.getParameter("email");
					double sueldo=Double.parseDouble(request.getParameter("sueldo"));
					String sexo=request.getParameter("sexo");
	
					
					
					EmpleadoBean empleado=new EmpleadoBean();
					empleado.setDni(dni);
					empleado.setNombre(nombre.trim());
					empleado.setApellido(apellidos.trim());
					empleado.setResidencia(direccion.trim());
					empleado.setSexo(sexo);
					empleado.setPerfil(Integer.parseInt(perfil));
					
					
					if(empleadodao.registrar(empleado)){
						
						ContactoBean contac= new ContactoBean();
						contac.setDni_trab(dni);
						for(int i=0;i<contacto.length;i++){
							contac.setTelefono(contacto[i]);
							if(contacto[i].length()==9){
								contac.setDescripcion("CELULAR");
							}else{
								contac.setDescripcion("FIJO");
							}
							empleadodao.guardarCon(contac);
						}
						
						CorreoBean corr = new CorreoBean();
						corr.setDni_trab(dni);
						corr.setCorreo(correo.trim());
						
						empleadodao.guardarCorreo(corr);
						
						System.out.println("YA GRABO EN T_TRABAJADOR");
						DetalleTrabajadorBean det =new DetalleTrabajadorBean();
						det.setDni_trab(dni);
						det.setSueldo(sueldo);
						det.setSeg_med(medico);
						det.setAfp(seguro);
						
						if(empleadodao.registrarDet(det)){
							System.out.println("YA GRABO EN T_DETALLE_PAGO");
							VendedorBean vendedor=new VendedorBean();
							vendedor.setDni_trab(dni);
							vendedor.setPorcentaje(comision);
							if(empleadodao.registrarVen(vendedor)){
								System.out.println("YA GRABO EN T_VENDEDOR");
								/*request.setAttribute("empleados", empleadodao.listar());
								request.setAttribute("mensaje", "Empleado creado correctamente");
								getServletContext().getRequestDispatcher("/empleados.jsp").forward(request,response);*/
								
								out.println("<script type=\"text/javascript\">");
								out.println("alert('Se registro al empleado');");
								out.println("location='Empleado'");
								out.println("</script>");
								
							}else{
								out.println("<script type=\"text/javascript\">");
								out.println("alert('Ocurrio un error, intentelo nuevamente!');");
								out.println("location='Empleado'");
								out.println("</script>");
							}
						}
						
					}
				}else{
					String dni=request.getParameter("dni");
					String nombre=request.getParameter("nombre");
					String apellidos=request.getParameter("apellidos");
					String direccion=request.getParameter("direccion");
					String[] contacto=request.getParameterValues("contacto");
					int seguro=Integer.parseInt(request.getParameter("seguro"));
					int medico=Integer.parseInt(request.getParameter("medico"));
					String correo=request.getParameter("email");
					double sueldo=Double.parseDouble(request.getParameter("sueldo"));
					String sexo=request.getParameter("sexo");
	
					
					
					EmpleadoBean empleado=new EmpleadoBean();
					empleado.setDni(dni);
					empleado.setNombre(nombre.trim());
					empleado.setApellido(apellidos.trim());
					empleado.setResidencia(direccion.trim());
					empleado.setSexo(sexo);
					empleado.setPerfil(Integer.parseInt(perfil));
					
					
					if(empleadodao.registrar(empleado)){
						
						ContactoBean contac= new ContactoBean();
						contac.setDni_trab(dni);
						for(int i=0;i<contacto.length;i++){
							contac.setTelefono(contacto[i]);
							if(contacto[i].length()==9){
								contac.setDescripcion("CELULAR");
							}else{
								contac.setDescripcion("FIJO");
							}
							empleadodao.guardarCon(contac);
						}
						
						CorreoBean corr = new CorreoBean();
						corr.setDni_trab(dni);
						corr.setCorreo(correo.trim());
						
						empleadodao.guardarCorreo(corr);
						
						System.out.println("YA GRABO EN T_TRABAJADOR");
						DetalleTrabajadorBean det =new DetalleTrabajadorBean();
						det.setDni_trab(dni);
						det.setSueldo(sueldo);
						det.setSeg_med(medico);
						det.setAfp(seguro);
						response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
				        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				        response.setDateHeader("Expires", 0); // Proxies.
						if(empleadodao.registrarDet(det)){
							System.out.println("YA GRABO EN T_DETALLE_PAGO");
							if(perfil.equals("4")){
								out.println("<script type=\"text/javascript\">");
								out.println("alert('Se registro al empleado');");
								out.println("location='Empleado'");
								out.println("</script>");
							}else {
								if(empleadodao.generarUsuario(nombre, apellidos, dni)){
									out.println("<script type=\"text/javascript\">");
									out.println("alert('Se registro al empleado');");
									out.println("location='Empleado'");
									out.println("</script>");
								}else{
									out.println("<script type=\"text/javascript\">");
									out.println("alert('Ocurrio un error, intentelo nuevamente!');");
									out.println("location='Empleado'");
									out.println("</script>");
								}
							}
						}
					}
				}	
			}
			
			
			if(accion.equalsIgnoreCase("buscar")){
				String dni=request.getParameter("dni");
				System.out.println(dni);
				boolean empleadoB = empleadodao.comprobarExistencia(dni);
				
				ResponseObject responseobj=null;
				
				
					responseobj=new ResponseObject();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					responseobj.setSuccess(true);
					responseobj.setObject(empleadoB);
				
				
				response.getWriter().write(new Gson().toJson(responseobj));
				System.out.println("json" + new Gson().toJson(responseobj));
				
				
			}
			
			
			//ELIMINAR
			if(accion.equalsIgnoreCase("eliminar")){
				String dni=request.getParameter("dni");
				
				
				I_Empleado emp=dao.getEmpleadoDAO();
				
				if(emp.eliminarEmpleado(dni)){
					/*request.setAttribute("empleados", emp.listar());
					request.setAttribute("mensaje", "Empleado eliminado correctamente");
					response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			        response.setDateHeader("Expires", 0); // Proxies.
					getServletContext().getRequestDispatcher("/empleados.jsp").forward(request,response);*/
					
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Se elimino al empleado');");
					out.println("location='Empleado'");
					out.println("</script>");
				}
			}
			
			//RECUPERAR
			if(accion.equalsIgnoreCase("recuperar")){
				String dni=request.getParameter("dniRec");
				
				
				I_Empleado emp=dao.getEmpleadoDAO();
				
				if(emp.rccuperarEmpleado(dni)){
					/*request.setAttribute("empleados", emp.listar());
					request.setAttribute("mensaje", "Empleado eliminado correctamente");
					response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			        response.setDateHeader("Expires", 0); // Proxies.
					getServletContext().getRequestDispatcher("/empleados.jsp").forward(request,response);*/
					
					System.out.println("se llego hasta aqui");
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Se recupero al empleado');");
					out.println("location='Empleado'");
					out.println("</script>");
				}
			}	
			
			
			if(accion.equalsIgnoreCase("obtenerDatos")){
				
				String dni = request.getParameter("dniObt");
				
				if(dni.length()==6){
					dni = "0"+dni;
				}
				System.out.println(dni);
				EmpleadoBean empleado=empleadodao.buscarEmpleado(dni);
				
				ResponseObject responseobj=null;
				
				if(empleado!=null){
					responseobj=new ResponseObject();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					responseobj.setSuccess(true);
					responseobj.setObject(empleado);
				}
				
				response.getWriter().write(new Gson().toJson(responseobj));
				System.out.println("json" + new Gson().toJson(responseobj));
				
			}	
		}
	}
}
