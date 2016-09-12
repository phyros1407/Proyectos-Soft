package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ContactoBean;
import beans.CorreoBean;
import beans.DetalleTrabajadorBean;
import beans.EmpleadoBean;
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
		String accion = request.getParameter("accion");
		
		if(accion.equalsIgnoreCase("listar")){
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Empleado empleadodao=dao.getEmpleadoDAO();
			
			ArrayList<EmpleadoBean> empleados=empleadodao.listar();
			
			request.setAttribute("empleados", empleados);
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	        response.setDateHeader("Expires", 0); // Proxies.
			getServletContext().getRequestDispatcher("/empleados.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		
		
		if(accion.equalsIgnoreCase("registrar")){
			//REGISTRAR
			System.out.println("SE ESTA REGISTRANDO------------------------");
			String perfil=request.getParameter("perfil");
			System.out.println(perfil);
			if(perfil.equalsIgnoreCase("5")){
				int dni=Integer.parseInt(request.getParameter("dni"));
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

				DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Empleado empleadodao=dao.getEmpleadoDAO();
				
				EmpleadoBean empleado=new EmpleadoBean();
				empleado.setDni(dni);
				empleado.setNombre(nombre);
				empleado.setApellido(apellidos);
				empleado.setResidencia(direccion);
				empleado.setSexo(sexo);
				empleado.setPerfil(Integer.parseInt(perfil));
				
				
				if(empleadodao.registrar(empleado)){
					
					ContactoBean contac= new ContactoBean();
					contac.setDni_trab(dni);
					for(int i=0;i<contacto.length;i++){
						contac.setTelefono(contacto[i]);
						empleadodao.guardarCon(contac);
					}
					
					CorreoBean corr = new CorreoBean();
					corr.setDni_trab(dni);
					corr.setCorreo(correo);
					
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
							request.setAttribute("empleados", empleadodao.listar());
							request.setAttribute("mensaje", "Empleado creado correctamente");
							getServletContext().getRequestDispatcher("/empleados.jsp").forward(request,response);
						}else{
							request.setAttribute("empleados", empleadodao.listar());
							request.setAttribute("mensaje", "Empleado creado No correctamente");
							getServletContext().getRequestDispatcher("/empleados.jsp").forward(request,response);
						}
					}
					
				}
			}else{
				int dni=Integer.parseInt(request.getParameter("dni"));
				String nombre=request.getParameter("nombre");
				String apellidos=request.getParameter("apellidos");
				String direccion=request.getParameter("direccion");
				String[] contacto=request.getParameterValues("contacto");
				int seguro=Integer.parseInt(request.getParameter("seguro"));
				int medico=Integer.parseInt(request.getParameter("medico"));
				String correo=request.getParameter("email");
				double sueldo=Double.parseDouble(request.getParameter("sueldo"));
				String sexo=request.getParameter("sexo");

				DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Empleado empleadodao=dao.getEmpleadoDAO();
				
				EmpleadoBean empleado=new EmpleadoBean();
				empleado.setDni(dni);
				empleado.setNombre(nombre);
				empleado.setApellido(apellidos);
				empleado.setResidencia(direccion);
				empleado.setSexo(sexo);
				empleado.setPerfil(Integer.parseInt(perfil));
				
				
				if(empleadodao.registrar(empleado)){
					
					ContactoBean contac= new ContactoBean();
					contac.setDni_trab(dni);
					for(int i=0;i<contacto.length;i++){
						contac.setTelefono(contacto[i]);
						empleadodao.guardarCon(contac);
					}
					
					CorreoBean corr = new CorreoBean();
					corr.setDni_trab(dni);
					corr.setCorreo(correo);
					
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
							request.setAttribute("empleados", empleadodao.listar());
							request.setAttribute("mensaje", "Empleado creado correctamente");
							getServletContext().getRequestDispatcher("/empleados.jsp").forward(request,response);
						}else {
							if(empleadodao.generarUsuario(nombre, apellidos, dni)){
								System.out.println("YA GRABO EN T_USUARIO");
								request.setAttribute("empleados", empleadodao.listar());
								request.setAttribute("mensaje", "Empleado creado correctamente");
								getServletContext().getRequestDispatcher("/empleados.jsp").forward(request,response);
							}else{
								request.setAttribute("empleados", empleadodao.listar());
								request.setAttribute("mensaje", "Empleado creado No correctamente");
								getServletContext().getRequestDispatcher("/empleados.jsp").forward(request,response);
							}
						}
					}
				}
			}	
		}
		if(accion.equalsIgnoreCase("buscar")){
			String dni=request.getParameter("dni");
			
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Empleado emp=dao.getEmpleadoDAO();
			
			
		}
		if(accion.equalsIgnoreCase("eliminar")){
			String dni=request.getParameter("dni");
			
			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			I_Empleado emp=dao.getEmpleadoDAO();
			
			if(emp.eliminarEmpleado(dni)){
				request.setAttribute("empleados", emp.listar());
				request.setAttribute("mensaje", "Empleado eliminado correctamente");
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		        response.setDateHeader("Expires", 0); // Proxies.
				getServletContext().getRequestDispatcher("/empleados.jsp").forward(request,response);
			}
		}
		
	}

}
