package com.company;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class InsertingImage2 {
    public static void main(String args[]) throws Exception {
        //Loading an existing document
        File filePDF = new File("C:/PdfBox_Examples/my_doc.pdf");
        String stamp = "C:/PdfBox_Examples/copy_done.png";

        // Loader class not found in original sample
        try (PDDocument doc = PDDocument.load(filePDF)) {
            // Loop through each page to add text water mark on each pageд
            for (final PDPage page : doc.getPages()) {

                InsertingImageToPage(doc,page,stamp);
            }
            doc.save(filePDF);
        }


    }

    private static void InsertingImageToPage(final PDDocument doc, final PDPage page, final String wayToStamp) throws IOException {
        PDImageXObject pdImage = PDImageXObject.createFromFile(wayToStamp,doc);

        //creating the PDPageContentStream object

        PDPageContentStream contents = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true,
                true);

        //Drawing the image in the PDF document
        contents.drawImage(pdImage, 50, 150);

        //Closing the PDPageContentStream object
        contents.close();
    }
}
