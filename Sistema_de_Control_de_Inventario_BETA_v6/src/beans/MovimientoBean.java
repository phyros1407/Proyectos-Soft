package beans;

public class MovimientoBean {
		
		private int id;
		private String fecha_mov;
		private int cantidad_retirada;
		private String descripcion;
		private int id_articulo;
		private String nombreArticulo;
		private String medida;
		private int id_usuario;
		private String nombrePersona;
		private String comientario;
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFecha_mov() {
			return fecha_mov;
		}
		public void setFecha_mov(String fecha_mov) {
			this.fecha_mov = fecha_mov;
		}
		public int getCantidad_retirada() {
			return cantidad_retirada;
		}
		public void setCantidad_retirada(int cantidad_retirada) {
			this.cantidad_retirada = cantidad_retirada;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public int getId_articulo() {
			return id_articulo;
		}
		public void setId_articulo(int id_articulo) {
			this.id_articulo = id_articulo;
		}
		public int getId_usuario() {
			return id_usuario;
		}
		public void setId_usuario(int id_usuario) {
			this.id_usuario = id_usuario;
		}
		public String getNombreArticulo() {
			return nombreArticulo;
		}
		public void setNombreArticulo(String nombreArticulo) {
			this.nombreArticulo = nombreArticulo;
		}
		public String getNombrePersona() {
			return nombrePersona;
		}
		public void setNombrePersona(String nombrePersona) {
			this.nombrePersona = nombrePersona;
		}
		public String getMedida() {
			return medida;
		}
		public void setMedida(String medida) {
			this.medida = medida;
		}
		public String getComientario() {
			return comientario;
		}
		public void setComientario(String comientario) {
			this.comientario = comientario;
		}
		
		
		
		
		
}
