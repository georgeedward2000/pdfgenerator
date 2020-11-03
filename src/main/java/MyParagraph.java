import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import java.util.*;
import java.util.List;

public class MyParagraph {
    public enum fontsStyles {
        italic,
        bold
    }
    int fontSize;
    List<Text> text;
    fontsStyles font;
    boolean regular;
    boolean isFill;
    float indent;
    boolean isLarge;


    MyParagraph(List<Text> x, float y) {
        this.fontSize = 16;
        this.isLarge = false;
        this.indent = y;
        this.isFill = false;
        this.font = null;
        this.regular = true;
        this.text = x;
    }

    public Paragraph Execute() throws Exception{
        try {
            Paragraph p = new Paragraph();
            List<Text> actual = new ArrayList<>();
            p.setFirstLineIndent(25 * this.indent);
            if (this.text.isEmpty()) throw new Exception();
            for (Text i : this.text) {
                if (i.getText().charAt(0) == '.') {
                    if (this.regular == true && this.font == null) {
                        for (Text j : actual) {
                            if (this.isLarge) {
                                p.add(j.setFontSize(26));
                                this.isLarge = false;
                            } else p.add(j);
                        }
                        p.add(new Text(" "));
                        actual.clear();
                    }

                    // FONT FORMATOR
                    switch (i.getText()) {
                        case ".large": {
                            this.isLarge = true;
                        }
                        case ".italic": {
                            this.font = fontsStyles.italic;
                            this.regular = false;
                            break;
                        }
                        case ".italics": {
                            this.font = fontsStyles.italic;
                            this.regular = false;
                            break;
                        }
                        case ".bold": {
                            this.font = fontsStyles.bold;
                            this.regular = false;
                            break;
                        }
                        case ".regular": {
                            this.regular = true;
                            break;
                        }
                        case ".fill": {
                            p.setFirstLineIndent(0);
                            p.setMarginLeft(25 * this.indent);
                            p.setMarginRight(-15);
                            this.isFill = true;
                            break;
                        }
                        default:
                            break;
                    }

                    if (this.regular || i.getText().contains(".italic") || i.getText().contains(".bold")) {

                        if (this.font != null && !i.getText().contains(".italic") && !i.getText().contains(".bold")) {

                            if (this.font == fontsStyles.italic) {
                                for (Text j : actual) {
                                    if (this.isLarge) p.add(j.setItalic()).setFontSize(26);
                                    else p.add(j.setItalic());
                                }
                            } else if (this.font == fontsStyles.bold) {
                                for (Text j : actual) {

                                    if (this.isLarge) p.add(j.setBold()).setFontSize(26);
                                    else p.add(j.setBold());
                                }

                            }
                            this.font = null;
                        } else {
                            for (Text ij : actual) {
                                if (this.isLarge) p.add(ij.setFontSize(26));
                                else p.add(ij);
                            }
                        }
                        actual.clear();
                    }
                } else actual.add(i);
            }

            for (Text k : actual) {
                if (this.isLarge) p.add(k.setFontSize(26));
                else p.add(k);
            }
            this.isLarge = false;
            return p;
        }
        catch (Exception e){
            System.out.println("ERROR: ");
            System.out.println(e);
            return null;
        }
    }

}
