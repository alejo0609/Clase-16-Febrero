/** 
 * @author Alejandro Perez CC 8.029.742
 * @author Julian David Giraldo Murillo CC 1.007.240.094
 * @author Andres Escobar Vasquez CC 1.038.096.962
 * @author Jorge Andres Restrepo Cataño CC 98.648.720
 */

package com.pruebas.adoptapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pruebas.service.EmailService;
import org.springframework.context.ConfigurableApplicationContext;




@SpringBootApplication(scanBasePackages = "com.pruebas")
@EntityScan(basePackages = "com.pruebas.model")
@EnableJpaRepositories(basePackages = "com.pruebas.repository")
public class AdoptappApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AdoptappApplication.class, args);

        // Solo para probar envío de correo:
        EmailService emailService = context.getBean(EmailService.class);
        emailService.enviarCorreo(
            "alejandroperezramirez0609@gmail.com", // destinatario
            "Prueba de correo desde AdoptApp 🚀",   // asunto
            "¡Hola! Este es un correo de prueba enviado con SendGrid desde Spring Boot 🐾." // contenido
        );
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
