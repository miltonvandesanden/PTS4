/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Danny
 */
@WebServlet("/applyclass")
public class Apply extends HttpServlet {

    final String username = "Info.ECopy@gmail.com";
    final String password = "ECopyCactus";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("sendApplyForm") != null) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("info.ecopy@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("danny.janssen@student.fontys.nl"));
                message.setSubject("Testmail ECOPY");
                message.setText("Dear Sir/Madam, " + " \n\n This is a test e-mail sent by E-Copy Corporation. Please ignore this message."
                        + "\n\n Yours faithfully, \n\n E-Copy crew");

                Transport.send(message);
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Gelukt');");
                out.println("location='apply.jsp';");
                out.println("</script>");

            } catch (MessagingException e) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Gefaal grote nub');");
                out.println("location='apply.jsp';");
                out.println("</script>");
                throw new RuntimeException(e);
            }
        }

        JOptionPane.showMessageDialog(new JFrame(), "Your request has been send.");
        //request.getRequestDispatcher("LogIn.jsp").forward(request, response);
    }
}
