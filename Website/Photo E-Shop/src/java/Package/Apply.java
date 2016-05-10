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
import java.sql.Connection;
import java.sql.PreparedStatement;
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
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.getParameter("fEmail")));
                            message.setSubject("Applience received by ECOPY");
                            message.setText("Dear Sir/Madam from " + request.getParameter("fCompany") + ", " + " \n\n We have received you applience and you will receive an email when we accept or decline your request."
                                    + "\n\n Yours faithfully, \n\n E-Copy crew");

                            Transport.send(message);

                            JOptionPane.showMessageDialog(new JFrame(), "Your request has been send.");
                            response.sendRedirect("index.jsp");

                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Unfortunately we weren't able to add you to our registry, try again later.");
                    response.sendRedirect("apply.jsp");
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), newEmail);
                response.sendRedirect("apply.jsp");
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
            Connection myConn = database.myConn;
            PreparedStatement PS = myConn.prepareStatement("Select Email From \"User\" Where Email = '" + email + "'");
            //PS.setString(1, email);
            ResultSet Email = database.GetQuery(PS);
            if (Email.next()) {
                return "Your email has already been used.";
            } else {
                System.out.println("Email reeds niet in gebruik");
                return null;
            }
        }
        return "Unfortunately we weren't able to add you to our registry, try again later.";
    }

    public boolean Applied(String CName, String Email, String City, String Address) throws SQLException, ClassNotFoundException {
        Database database = new Database();
        Integer userID = 0;

        if (database.Connect()) {
            Connection myConn = database.myConn;
            PreparedStatement PIS1 = myConn.prepareStatement("Insert Into \"User\" (USERID, Email, \"Password\", IsCompany) Values (UserSequence.nextval, '" + Email + "', '" + RandomGenerator() + "', 1)");
            /*PIS1.setString(1, Email);
            PIS1.setString(2, RandomGenerator());*/
            if (database.InsertQuery(PIS1)) {
            PreparedStatement PS = myConn.prepareStatement("Select * From \"User\" Where Email = '" + Email + "'");
            //PS.setString(1, Email);
            ResultSet user = database.GetQuery(PS);
                while (user.next()) {
                    userID = user.getInt("USERID");
                }
            }
            if (userID != 0) {
            PreparedStatement PIS2 = myConn.prepareStatement("Insert Into Company (COMPANYID, USERID, \"Name\", Place, Address, IsAccepted) Values (CompanySequence.nextval, " + userID + ", '" + CName + "', '" + City + "', '" + Address + "', 0)");
            /*PIS2.setString(1, userID.toString());
            PIS2.setString(2, CName);
            PIS2.setString(3, City);
            PIS2.setString(4, Address);*/
                if (database.InsertQuery(PIS2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String RandomGenerator() {
        
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        
        for (int i = 0; i < 5; i++)
        {
            Random rnd = new Random();
            while (salt.length() < 6) {
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
        }
        return salt.toString();
    }
}
