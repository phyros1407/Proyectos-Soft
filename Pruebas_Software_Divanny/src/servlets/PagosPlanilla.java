package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.EmpleadoBean;
import dao.factory.DAOFactory;
import dao.interfaces.I_Asistencias;
import dao.interfaces.I_Empleado;
import dao.interfaces.I_PagosPlanilla;

/**
 * Servlet implementation class PagoPlanilla
 */
@WebServlet("/PagosPlanilla")
public class PagosPlanilla extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagosPlanilla() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DAOFactory dao=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_PagosPlanilla pagosPlanilla=dao.getPagosPlanillaDAO();
		I_Asistencias asistencias=dao.getAsistenciasDAO();
		
		double sueldo,sueldoR,perfil,aumento=0,descuento,seguroV,seguroS;
		int dni=0;
		ArrayList<EmpleadoBean> empleado= asistencias.listarEmpleados(0);
		
		for(int i=0;i<empleado.size();i++){
		dni=empleado.get(i).getDni();
		System.out.println(dni);
		sueldo=pagosPlanilla.obtenerSueldo(dni);
		perfil=empleado.get(i).getPerfil();
		if(perfil==4){
			aumento=pagosPlanilla.aumentarHObreros(dni);
			System.out.println(aumento);
		}else{if(perfil==5){
			aumento=pagosPlanilla.aumentarCVendedor(dni);
			System.out.println(aumento);
		}
		sueldoR=sueldo+aumento;
		descuento=pagosPlanilla.aplicarDescuento(dni, sueldoR);
		System.out.println(descuento);
		sueldoR=sueldoR-descuento;
		System.out.println(sueldoR);
		seguroV=pagosPlanilla.descontarSeguroVida(dni, sueldoR);
		seguroS=pagosPlanilla.calcularSeguroSalud(dni, sueldoR);
		sueldoR=sueldoR-seguroV;
		System.out.println(sueldo+" "+perfil+" "+aumento+" "+descuento+" "+seguroV+" "+seguroS+" "+sueldoR);
		
		if(pagosPlanilla.registrarPlanilla(sueldo, sueldoR, dni, aumento, descuento, seguroV, seguroS)){
			System.out.println("Registro correcto");
		}else{
			System.out.println("No funciona");
		}
		
		}
		}
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
	}

}
