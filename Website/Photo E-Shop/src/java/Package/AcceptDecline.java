/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Danny
 */
@WebServlet("/acceptclass")
public class AcceptDecline extends HttpServlet{
    
    final String username = "Info.ECopy@gmail.com";
    final String password = "ECopyCactus";
    
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Connection with mail server
        String username = "Info.ECopy@gmail.com";
        String password = "ECopyCactus";
            if(request.getParameter("sendChoices") != null) {
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
                        
                    
            //Create Database
            Database db = null;try{db = new Database(); db.Connect();}catch(Exception ex){}
           
            //Handling for all chosen accepted requests
            String[] Acceptresults = request.getParameterValues("accept");
            if(Acceptresults != null)
            {
                for(String r : Acceptresults)
                {
                   //Change value in database
                        try {
                            String query = "UPDATE COMPANY SET IsAccepted = 1 WHERE USERID IN (SELECT \"User\".USERID FROM \"User\" WHERE \"EMAIL\" = '"+r+"')";
                            db.InsertQuery(query);
                            ResultSet passwordSet = db.GetQuery("SELECT * FROM \"User\" WHERE \"EMAIL\" = '"+r+"'");
                            if(!passwordSet.next())
                            {
                                System.out.println("Er is iets fout gegaan bij het genereren van het wachtwoord voor dit email.");
                            }
                            else{
                            //Send mail
                            try {
                                Message message = new MimeMessage(session);
                                message.setFrom(new InternetAddress("info.ecopy@gmail.com"));
                                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(r));
                                message.setSubject("Testmail ECOPY");
                                message.setText("Dear Sir/Madam, " + " \n\n Your request has been accepted."
                                        + "\n You can log in to our site by using your email and the password showed below."
                                        + "\n\n Email: '"+r+"'"
                                        + "\n Password: "+passwordSet.getString("Password")
                                        + "\n\n Yours faithfully, \n\n E-Copy crew");
                                
                                Transport.send(message);
                                
                            } 
                            catch (MessagingException e) {
                                throw new RuntimeException(e);
                            }
                            }
                        } 
                        catch (SQLException ex) {
                            Logger.getLogger(AcceptDecline.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            }
           //Handling declined requests
           String[] Declineresults = request.getParameterValues("decline");
           if(Declineresults != null)
            {
               for(String r : Declineresults)
                {
                   //Change value in database
                    try {
                        String query = "UPDATE COMPANY SET IsAccepted = 2 WHERE USERID IN (SELECT \"User\".USERID FROM \"User\" WHERE \"EMAIL\" = '"+r+"')";
                        db.InsertQuery(query);
                        //Send mail
                        try {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("info.ecopy@gmail.com"));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(r));
                            message.setSubject("Testmail ECOPY");
                            message.setText("Dear Sir/Madam, " + " \n\n Your request has not been accepted. Please contact us for further information."
                                    + "\n\n Yours faithfully, \n\n E-Copy crew");

                            Transport.send(message);

                        } 
                        catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }
                    }   
                    catch (SQLException ex) {
                        Logger.getLogger(AcceptDecline.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            response.sendRedirect("index.jsp");
        }
    }
}
