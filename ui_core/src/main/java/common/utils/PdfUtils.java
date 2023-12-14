package common.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfUtils {

    public static String getTextFromPDF (String filePath) throws IOException {
        String text = null;
        PDDocument document = PDDocument.load(new File(filePath));
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            text = stripper.getText(document);
        }
        document.close();
        return text;
    }
}


