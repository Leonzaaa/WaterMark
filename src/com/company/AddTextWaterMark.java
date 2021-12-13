package com.company;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;



public class AddTextWaterMark {

    //final static int WIDTH = 50; // Отступ  по оси X (слева)
    //final static int HEIGHT = 200; // Отсутп по оси Y (справа)

    public static void main(final String[] args) throws IOException {

            final File srcFile = new File("C:/PdfBox_Examples/sample.pdf");
            final File dstFile = new File("C:/PdfBox_Examples/sample1.pdf");


            final String textFIO = "ФИО: ";
            final String textDate = "Дата: ";
            final String textNumOreder = "№ накладной: ";
            final String Autograph = "Подпись:____________";


        String[] arrayS = new String[4];
        arrayS[0] = "ФИО: " + "Николай";
        arrayS[1] = "Дата: " + "08.12.2021";
        arrayS[2] = "Номер накладной: " + "13220";
        arrayS[3] = "Подпись__________: ";

        String pathToFontRU = "C:/Windows/Fonts/arial.ttf";

        // Loader class not found in original sample
            try (PDDocument doc = PDDocument.load(srcFile)) {
                // Loop through each page to add text water mark on each pageд
                for (final PDPage page : doc.getPages()) {
                    final PDFont font = PDType0Font.load(doc,new File(pathToFontRU));
                    //HELVE
                    addWatermarkText(doc, page, font,arrayS);
                }
                doc.save(dstFile);
            }

    }

    private static void addWatermarkText(final PDDocument doc, final PDPage page, final PDFont font, final String[] arrayS)
            throws IOException {
        try (PDPageContentStream cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true,
                true)) {
            final float fontHeight = 16; // arbitrary for short text
            final float x = page.getMediaBox().getWidth()-(page.getMediaBox().getWidth()-50);    // "horizontal" position in rotated world
            final float y = page.getMediaBox().getHeight()-(page.getMediaBox().getHeight()-200); // 4 is a trial-and-error thing, this lowers the text a bit
            cs.setFont(font, fontHeight);
           // cs.setRenderingMode(RenderingMode.STROKE); // for "hollow" effect


            // Set color
            cs.setNonStrokingColor(Color.blue);
            cs.setStrokingColor(Color.BLUE);

            // Printing text
            for (int i = 0; i < arrayS.length; i++) {
                cs.beginText();
                cs.newLineAtOffset(x, y-(fontHeight*(i+1)));
                cs.showText(arrayS[i]);
                cs.endText();
            }

        }
    }


}
