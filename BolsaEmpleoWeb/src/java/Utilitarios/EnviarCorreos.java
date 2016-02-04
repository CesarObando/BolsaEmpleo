package Utilitarios;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarCorreos {
    
    public String EnviarCorreo(String destinatario, String asunto, String cuerpo){
         try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "bolsaempleopublica@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("bolsaempleopublica@gmail.com"));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("bolsaempleopublica@gmail.com", "tcu-563paraiso");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
            return "Enviado";
        }
        catch (Exception e)
        {
            return "No enviado";
        }
    }
}
