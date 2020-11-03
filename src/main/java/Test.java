
import com.itextpdf.layout.element.Text;

import java.util.*;

/** RUN => EDIT CONFIGURATIONS => VM OPTIONS insert " -ea  " */
/** NOW ASSERTS WILL WORK */

public class Test {
    public static void testing(){

    }
    public static void main(String args[]) throws Exception{
       try {
           MyParagraph p = new MyParagraph(List.of(new Text(".large"), new Text("ss")), 2);

           assert p.isLarge == false : "Error in MyParagraph";
           assert p.regular == true : "Error in MyParagraph";
           assert p.isFill == false : "Error in MyParagraph";
           assert p.indent == 2 : "Error in MyParagraph";
           assert p.font == null : "Error in MyParagraph";
           assert p.fontSize == 16 : "Error in MyParagraph";
           assert !p.text.isEmpty() : "Error in MyParagraph";
       }
       catch(Exception e){
           System.out.println("ERROR: ");
           System.out.println(e);
       }
    }
}
