package beans;

import java.io.InputStream;

public class JustificacionBean {

	
		private int id;
		private int id_asis;
		private String dni;
		private String fechaJ;
		private String motivo;
		String comentario;
		InputStream input;
		
		
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getId_asis() {
			return id_asis;
		}
		public void setId_asis(int id_asis) {
			this.id_asis = id_asis;
		}
		public String getDni() {
			return dni;
		}
		public void setDni(String dni) {
			this.dni = dni;
		}
		public String getFechaJ() {
			return fechaJ;
		}
		public void setFechaJ(String fechaJ) {
			this.fechaJ = fechaJ;
		}
		public String getMotivo() {
			return motivo;
		}
		public void setMotivo(String motivo) {
			this.motivo = motivo;
		}
		public String getComentario() {
			return comentario;
		}
		public void setComentario(String comentario) {
			this.comentario = comentario;
		}
		public InputStream getInput() {
			return input;
		}
		public void setInput(InputStream input) {
			this.input = input;
		}
		
		
		
	
}
