import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Text;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class main {


    public static void main(String args[]) throws Exception {
        try {
            PdfWriter writer = new PdfWriter("out.pdf");

            float indentPrevious = 0;
            boolean isIndent = false;
            float indentActual = 0;

            //Initialize PDF document
            PdfDocument pdf = new PdfDocument(writer);
            pdf.addNewPage();

            // Initialize document
            Document document = new Document(pdf);

            File in = new File("input.txt");
            Scanner scanner = new Scanner(in);
            List<Text> textChunks = new ArrayList<Text>();

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine();
                if (!(data.equals(".paragraph") || data.contains(".indent"))) textChunks.add(new Text(data));

                if (data.contains(".indent") && !isIndent) {
                    indentPrevious += Integer.parseInt((data.split(" ")[1]));
                    isIndent = true;
                }
                if (data.contains(".indent") && isIndent) {
                    indentActual = indentPrevious + Integer.parseInt((data.split(" ")[1]));
                }
                if (data.contains(".paragraph")) indentActual = 0;

                if ((data.equals(".paragraph") || data.contains(".indent") || !scanner.hasNextLine()) && !textChunks.isEmpty()) {

                    MyParagraph p = new MyParagraph(textChunks, indentPrevious);
                    document.add(p.Execute());
                    if (!textChunks.isEmpty()) textChunks.clear();
                    indentPrevious = indentActual;
                    isIndent = false;
                }
            }

            document.close();
        }
        catch(Exception e){
            System.out.println("ERROR: ");
            System.out.println(e);
        }
    }


}
