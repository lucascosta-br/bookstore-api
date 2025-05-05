package br.com.sousinhacode.controller;

import br.com.sousinhacode.controller.docs.EmailControllerDocs;
import br.com.sousinhacode.dto.request.EmailRequestDTO;
import br.com.sousinhacode.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/email/v1")
public class EmailController implements EmailControllerDocs {

    @Autowired
    private EmailService emailService;

    @PostMapping
    @Override
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO emailRequest) {
        emailService.sendSimpleEmail(emailRequest);
        return new ResponseEntity<>("Email sent with success!", HttpStatus.OK);
    }

    @PostMapping("/withAttachment")
    @Override
    public ResponseEntity<String> sendEmailWithAttachment(@RequestParam("emailRequest") String emailRequest,
                                                          @RequestParam("attachment") MultipartFile attachment) {
        emailService.setEmailWithAttachment(emailRequest, attachment);
        return new ResponseEntity<>("Email with attachment sent successfully!", HttpStatus.OK);
    }
}
