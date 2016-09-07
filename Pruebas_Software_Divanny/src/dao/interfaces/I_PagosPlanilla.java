package dao.interfaces;

public interface I_PagosPlanilla {

	public double obtenerSueldo(int dni);
	public double aumentarHObreros(int dni);
	public double aumentarCVendedor(int dni);
	public double aplicarDescuento(int dni,double sueldo);
	public double descontarSeguroVida(int dni,double sueldoR);
	public double calcularSeguroSalud(int dni,double sueldoR);
	public boolean registrarPlanilla(double sueldo,double sueldoR,int dni,double aumento,double descuento,double seguroV,double seguroS);
	
}
