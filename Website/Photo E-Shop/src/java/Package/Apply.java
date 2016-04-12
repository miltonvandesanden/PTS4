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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        try {
            String newEmail;

            //IsNewEmail returns a String
            if ((newEmail = IsNewEmail(request.getParameter("fEmail"))) == null) {
                //Applied returns a boolean
                if (Applied(request.getParameter("fCompany"), request.getParameter("fEmail"), request.getParameter("fCity"), request.getParameter("fAddress"))) {
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
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("info.ecopy@gmail.com"));
                            message.setSubject("Testmail ECOPY");
                            message.setText("Dear Sir/Madam, " + " \n\n This is a test e-mail sent by E-Copy Corporation. Please ignore this message."
                                    + "\n\n Yours faithfully, \n\n E-Copy crew");

                            Transport.send(message);

                            JOptionPane.showMessageDialog(new JFrame(), "Your request has been send.");
                            request.getRequestDispatcher("index.jsp").forward(request, response);

                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Unfortunately we weren't able to add you to our registry, try again later.");
                    request.getRequestDispatcher("apply.jsp").forward(request, response);
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), newEmail);
                request.getRequestDispatcher("apply.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Apply.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Apply.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String IsNewEmail(String email) throws SQLException, ClassNotFoundException {
        Database database = new Database();
        if (database.Connect()) {
            if (database.GetQuery("Select * From User Where Email = '" + email + "';") != null) {
                return "Your email has already been used.";
            } else {
                return null;
            }
        }
        return "Unfortunately we weren't able to add you to our registry, try again later.";
    }

    public boolean Applied(String CName, String Email, String City, String Address) throws SQLException, ClassNotFoundException {
        Database database = new Database();
        Integer userID = 0;

        if (database.Connect()) {
            if (database.InsertQuery("Insert Into User (Email, IsAdmin) Values ('" + Email + "', false);")) {
                ResultSet user = database.GetQuery("Select UserId From User Where Email = " + Email + ";");
                while (user.next()) {
                    userID = user.getInt("UserId");
                }
            }
            if (userID != 0) {
                if (database.InsertQuery("Insert Into Company (UserID, Name, Place, Adress, Password, Accepted) Values (" + userID.toString() + ", " + CName + ", " + City + ", " + Address + ", " + RandomGenerator() + ", false);")) {
                    return true;
                }
            }
        }
        return false;
    }

    public String RandomGenerator() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
}
