package com.sims.controller;

import com.sims.model.Student;
import com.sims.service.StudentService;
import com.sims.utils.PDFGenerator;

import com.sims.utils.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/students", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);

        // Generate QR code
        byte[] qrCodeImage = QRCodeGenerator.generateQRCode(savedStudent, 200, 200);

        // Generate PDF
        String pdfDocument = PDFGenerator.generatePDF(savedStudent, qrCodeImage);




        // Save QR code and PDF to storage
        // You need to implement the storage part here

        return new ResponseEntity<>(pdfDocument,HttpStatus.OK);
    }
}
