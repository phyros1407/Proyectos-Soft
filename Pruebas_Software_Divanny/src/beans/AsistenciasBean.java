package beans;

public class AsistenciasBean {

	private int id;
	private int dni_trab;
	private String fecha;
	private String estadoH;
	private String horaEnt;
	private String horaRef;
	private String horaSref;
	private String horaSal;
	private String estadoD;
	
	
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEstadoH() {
		return estadoH;
	}
	public void setEstadoH(String estadoH) {
		this.estadoH = estadoH;
	}
	public String getEstadoD() {
		return estadoD;
	}
	public void setEstadoD(String estadoD) {
		this.estadoD = estadoD;
	}
	public String getHoraEnt() {
		return horaEnt;
	}
	public void setHoraEnt(String horaEnt) {
		this.horaEnt = horaEnt;
	}
	public String getHoraRef() {
		return horaRef;
	}
	public void setHoraRef(String horaRef) {
		this.horaRef = horaRef;
	}
	public String getHoraSref() {
		return horaSref;
	}
	public void setHoraSref(String horaSref) {
		this.horaSref = horaSref;
	}
	public String getHoraSal() {
		return horaSal;
	}
	public void setHoraSal(String horaSal) {
		this.horaSal = horaSal;
	}
	public int getDni_trab() {
		return dni_trab;
	}
	public void setDni_trab(int dni_trab) {
		this.dni_trab = dni_trab;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}	
	
}
