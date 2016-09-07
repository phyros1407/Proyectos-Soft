package domain;
import java.util.Properties;





import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


 
public class Email {
	private Properties properties = new Properties();
	
	
	//final String username ="fabrica_software_inventario@hotmail.com";
	/*final String username ="fabrica_software_adm@hotmail.com";
	final String password ="adm123456";*/
	
	final String username ="jean_barbieri@usmp.pe";
	final String password ="Jeanpier1996usmp";
	
	private Session session;
 
	
	
	
	private void init() {
 
		properties.put("mail.smtp.host", "outlook.office365.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.mail.sender", username);
		properties.put("mail.smtp,password",password);
		properties.put("mail.smtp.user", username);
		
		 
 
		session = Session.getDefaultInstance(properties);
	}
 
	public void sendEmail(String rec,String mess,String sub){
 
		init();
		try{
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(rec));
			message.setSubject(sub);
			message.setText(mess);
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"),password);
			t.sendMessage(message, message.getAllRecipients());
			System.out.println("SE ENVIO A SCHULERR!!!!");
			t.close();
		}catch (MessagingException me){
                        
			System.out.println("NO SE ENVIO A SCHULERRR!!!");
			System.out.print(me.getMessage());
			return;
		}
		
	}
	
	
	
	
 
}