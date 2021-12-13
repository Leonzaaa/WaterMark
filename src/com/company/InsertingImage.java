package com.company;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;

public class InsertingImage {
    public static void main(String args[]) throws Exception {
        //Loading an existing document
        File file = new File("C:/PdfBox_Examples/sample.pdf");
        PDDocument doc = PDDocument.load(file);

        //Retrieving the page
        PDPage page = doc.getPage(0);

        //Creating PDImageXObject object
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:/PdfBox_Examples/copy_done.png",doc);

        //creating the PDPageContentStream object

        PDPageContentStream contents = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true,
                true);

        //Drawing the image in the PDF document
        contents.drawImage(pdImage, 50, 150);

        System.out.println("Image inserted");

        //Closing the PDPageContentStream object
        contents.close();

        //Saving the document
        doc.save("C:/PdfBox_Examples/sample.pdf");

        //Closing the document
        doc.close();

    }

}
