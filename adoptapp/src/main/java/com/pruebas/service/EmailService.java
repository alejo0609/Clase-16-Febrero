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
        //Content content = new Content("text/plain", contenido);
        Content content = new Content("text/html", contenido);

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
    //Corre para la persona que se postula para una adopcion
    public void enviarCorreoAgradecimiento(String destinatario, String nombreAnimal) {
        String asunto = "Gracias por postularte a la adopción 🐾";
        String cuerpo = "<div style='font-family: Arial, sans-serif; font-size: 15px; color: #333;'>"
                + "<p><strong>¡Gracias por postularte para adoptar a " + nombreAnimal + "!</strong></p>"
                + "<p>Tu solicitud ha sido recibida y está siendo revisada. 📝</p>"
                + "<p>Nos pondremos en contacto contigo pronto. ¡Gracias por darle una oportunidad a un peludito! 🐾</p>"
                + "</div>";


        enviarCorreo(destinatario, asunto, cuerpo);
    }


    public void enviarCorreoATienda(String correoTienda, String nombreAnimal, String nombreAdoptante, 
                                    String telefonoAdoptante, String  emailAdoptante) {
        String asunto = "Nueva solicitud de adopción";
        String cuerpo = "<div style='font-family: Arial, sans-serif; font-size: 15px; color: #333;'>"
                + "<p>Hola,</p>"
                + "<p>Se ha recibido una solicitud de adopción para el animal:</p>"
                + "<p style='font-size: 16px; font-weight: bold;'>" + nombreAnimal + "</p>"
                //+ "<img src='" + fotoAnimal + "' alt=' Foto de " + nombreAnimal + "' style='max-width: 100%; height: auto; border-radius: 8px; margin-bottom: 20px;'><br>"
                //+ "<p><strong>Raza:</strong>"+ raza + "</p>> <br>"
                + "<hr style='border: none; border-top: 1px solid #ccc;'>"
                + "<p><strong>🧍 ADOPTANTE POSTULADO:</strong></p>"
                + "<p><strong>Nombre:</strong> " + nombreAdoptante + "<br>"
                + "<strong>Teléfono:</strong> " + telefonoAdoptante + "<br>"
                + "<strong>Email:</strong> " + emailAdoptante + "</p>"
                + "<hr style='border: none; border-top: 1px solid #ccc;'>"
                + "<p>Por favor revisa la plataforma para más detalles.</p>"
                + "</div>";


        enviarCorreo(correoTienda, asunto, cuerpo);
    }
}
