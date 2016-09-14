    package servlets;

    import java.io.IOException;
    import java.io.InputStream;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.MultipartConfig;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

    import com.google.gson.Gson;

    import beans.AsistenciasBean;
    import beans.EmpleadoBean;
    import beans.JustificacionBean;
    import beans.ResponseObject;
    import dao.factory.DAOFactory;
    import dao.interfaces.I_Asistencias;
    import dao.interfaces.I_Empleado;
import dao.interfaces.I_Justificacion;

    /**
     * Servlet implementation class Justificacion
     */
    @WebServlet("/Justificacion")
    @MultipartConfig(maxFileSize = 161772155)   
    public class Justificacion extends HttpServlet {
    	private static final long serialVersionUID = 1L;
           
        /**
         * @see HttpServlet#HttpServlet()
         */
        public Justificacion() {
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
    		
    		HttpSession sesion=request.getSession();
    		if(sesion.getAttribute("sesion")==null){
    			response.sendRedirect("login.jsp");
    		}else{
    		
    		String accion=request.getParameter("accion");
    		
    		//PARA REGISTRAR UNA JUSTIFICACION
    		if(accion.equalsIgnoreCase("buscarE")){
    			System.out.println("Entro al buscar");
    			String dniJ=request.getParameter("dniJ");
    			
    			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    			I_Justificacion justi=dao.getJustificacionDAO();
    			EmpleadoBean empleadoB=justi.obtenerFechFaltas(Integer.parseInt(dniJ));
    			ResponseObject responseobj=null;
    			
    			if(empleadoB!=null){
    				responseobj=new ResponseObject();
    				response.setContentType("application/json");
    				response.setCharacterEncoding("UTF-8");
    				responseobj.setSuccess(true);
    				responseobj.setObject(empleadoB);
    			}
    			
    			response.getWriter().write(new Gson().toJson(responseobj));
    			System.out.println("json" + new Gson().toJson(responseobj));
    			
    			
    			
    		}else{
    		
    			
    			

    		System.out.println("Entro al justificar");
    		String dni=request.getParameter("dni_t");
    		System.out.println("1 "+ dni);
    		int id=Integer.parseInt(request.getParameter("asis"));
    		System.out.println("2 "+ id);
    		String motivo=request.getParameter("motivo");
    		String comentario=request.getParameter("comentario");
    		System.out.println("3 "+comentario);
    		
    		InputStream input=null;
    		
    		Part filePart=request.getPart("archivo");
    		
    		if(filePart!=null){
    			
    			 	System.out.println(filePart.getName());
    	            System.out.println(filePart.getSize());
    	            System.out.println(filePart.getContentType());
    	            
    	            input = filePart.getInputStream();
    			
    		}
    		
    		try{
    			
    			DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    			I_Justificacion justi=dao.getJustificacionDAO();
    			
    			
    			JustificacionBean just=new JustificacionBean();
    			just.setDni(dni);
    			just.setId_asis(id);;
    			just.setComentario(comentario);
    			just.setInput(input);
    			just.setMotivo(motivo);
    			
    			AsistenciasBean asiJ=new AsistenciasBean();
    			asiJ.setDni_trab(dni);
    			asiJ.setId(id);
    			
    			System.out.println("llegue hasta aca");
    			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    	        response.setDateHeader("Expires", 0); // Proxies.
    			if(justi.actualizarEstadoAsistencia(asiJ)){
    				if(justi.registrarJustificacion(just)){
    					
    					System.out.println("Se guardo todo");
    					request.setAttribute("mensaje", "Registro de Justificación Exitoso");
    					getServletContext().getRequestDispatcher("/justificacion.jsp").forward(request, response);
    					
    				}else{
    					System.out.println("No se guardo todo");
    					request.setAttribute("mensaje", "Fallo de registro de Justificación");
    					getServletContext().getRequestDispatcher("/justificacion.jsp").forward(request, response);
    					
    				}
    			}else{
    				System.out.println("no se actualizo nada");
    				request.setAttribute("mensaje", "Estado de asistencia no actualizada");
    				getServletContext().getRequestDispatcher("/justificacion.jsp").forward(request, response);
    				
    			}
    		}catch(Exception e){
    			System.out.println(e.getMessage());
    		}
    		
    		
    			
    		}
    		
    		}
    		
    	}

    }


