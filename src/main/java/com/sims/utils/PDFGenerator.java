package com.sims.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.sims.model.Student;
import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFGenerator {

    @Value("${storage.pdf-dir}")
    private static String pdfStorageDir;

    public static String generatePDF(Student student, byte[] qrCodeImage) {
        try {
            // Generate dynamic PDF filename
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String pdfFilename  = "student_" + timestamp + ".pdf";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilename));
            document.open();

            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            document.add(new Paragraph("Student Information", font));
            document.add(new Paragraph("Name: " + student.getName(), font));
            document.add(new Paragraph("Age: " + student.getAge(), font));
            // Add more student information

            // Add QR code image to the PDF
            Image qrImage = Image.getInstance(qrCodeImage);
            document.add(qrImage);

            // Move the PDF file to the configured PDF storage directory
            FileUtil.moveFile(pdfFilename, pdfStorageDir);

            document.close();

            return "PDF generated and saved as: " + pdfStorageDir + "/" + pdfFilename;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
