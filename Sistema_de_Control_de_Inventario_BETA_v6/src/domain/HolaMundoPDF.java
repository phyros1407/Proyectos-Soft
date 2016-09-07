package domain;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
public class HolaMundoPDF
{
   public  void pdf() throws FileNotFoundException, DocumentException
   {
      FileOutputStream archivo = new FileOutputStream("D:\\hola.pdf");
      Document documento = new Document();
      PdfWriter.getInstance(documento, archivo);
      documento.open();
      documento.add(new Paragraph("Hola Mundo!"));
      documento.add(new Paragraph("SoloInformaticaYAlgoMas.blogspot.com"));
      documento.add(new Paragraph("Schulerrrrrrr :D"));
      documento.close();
   }
}