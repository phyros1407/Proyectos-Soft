package dao.interfaces;

import java.util.ArrayList;
import java.util.Vector;

import beans.PlanillaBean;

public interface I_PagosPlanilla {

	public double obtenerSueldo(String dni);
	public double aumentarHObreros(String dni);
	public double aumentarCVendedor(String dni);
	public double aplicarDescuento(String dni,double sueldo);
	public double descontarSeguroVida(String dni,double sueldoR);
	public double calcularSeguroSalud(String dni,double sueldoR);
	public boolean registrarPlanilla(double sueldo,double sueldoR,String dni,double aumento,double descuento,double seguroV,double seguroS);
	public ArrayList<PlanillaBean> listarPlanilla(int mes, int ano);
	
	
}
