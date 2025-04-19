package com.pruebas.service;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;


import java.io.IOException;

@Service
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    public void enviarCorreo(String destinatario, String asunto, String contenido) {
        Email from = new Email("alejo0609@hotmail.com"); // Cambia a tu correo verificado
        Email to = new Email(destinatario);
        Content content = new Content("text/plain", contenido);
        Mail mail = new Mail(from, asunto, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            System.out.println("✅ Email enviado. Status code: " + response.getStatusCode());

        } catch (IOException e) {
            System.err.println("❌ Error enviando el email: " + e.getMessage());
        }
    }

    public void enviarCorreoAgradecimiento(String destinatario, String nombreAnimal) {
        String asunto = "Gracias por postularte a la adopción 🐾";
        String cuerpo = "¡Gracias por postularte para adoptar a " + nombreAnimal + "!\n\n" + 
                        "Nos pondremos en contacto contigo pronto para informarte sobre los próximos pasos.";
        enviarCorreo(destinatario, asunto, cuerpo);
    }


    public void enviarCorreoATienda(String correoTienda, String nombreAnimal, String nombreAdoptante) {
        String asunto = "Nueva solicitud de adopción";
        String cuerpo = "Hola,\n\nSe ha recibido una solicitud de adopción para el animal: " + nombreAnimal +
                        " por parte de: " + nombreAdoptante + ".\n\nPor favor revisa la plataforma para más detalles.";
        enviarCorreo(correoTienda, asunto, cuerpo);
    }
}
