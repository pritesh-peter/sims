package com.sims.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.sims.model.Student;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PDFGenerator {
    public static byte[] generatePDF(Student student, byte[] qrCodeImage) {
        try {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            document.add(new Paragraph("Student Information", font));
            document.add(new Paragraph("Name: " + student.getName(), font));
            document.add(new Paragraph("Age: " + student.getAge(), font));
            // Add more student information

            // Add QR code image to the PDF
            Image qrImage = Image.getInstance(qrCodeImage);
            document.add(qrImage);

            document.close();

            return baos.toByteArray();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
