package domain;


public class Metodos {

	
	public String generarUsuario(String nombre,String apellidoP,String apellidoM){
			
		String aux1=nombre.trim().substring(0,1);
		String aux2=apellidoM.trim().substring(0,1).toLowerCase();
					
		System.out.print(aux1+apellidoP.trim()+aux2);
		
		return aux1+apellidoP+aux2;
	}
	
	
	public static String generarPassword(){
		String [] abecedario = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m","n","o","p","q","r","s","t","u","v","w", "x","y","z" };
		
		int numRandon =0 ;

		String password="";
		
		for(int i=0;i<8;i++){
			String auxi="";
			numRandon = (int) Math.round(Math.random() * 26 ) ;
			auxi=abecedario[numRandon];
			
			
			password=password+auxi;
		}
		
		return password;
		
		
	}
	
	
	 public  static String toHexadecimal(byte[] digest){
		 
		 	        String hash = "";
		
		 	        for(byte aux : digest) {
		 
		 	            int b = aux & 0xff;
		 	            if (Integer.toHexString(b).length() == 1) hash += "0";
		 	            hash += Integer.toHexString(b);
		 
		 	        }
		 
		 	        return hash;
		 
	 }
	
	
	
	/*public static void main(String[]args){
		System.out.print(generarPassword());
		
	}*/
}
