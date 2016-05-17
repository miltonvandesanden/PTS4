<%-- 
    Document   : Header
    Created on : 15-mrt-2016, 13:42:39
    Author     : Milton
--%>

<%@page import="BusinessLayer.Connection"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-Copy</title>
        <link rel="stylesheet" href="bootstrap.css"/>
    </head>
    <body>
        <div class="row">
            <nav class="navbar navbar-default navbar-fixed-top">
            <%--<form>
                <select id="language" name="language" onchange="submit()">
                    <option value="" ${language == '' ? 'selected' : ''}>English</option>
                    <option value="nl" ${language == 'nl' ? 'selected' : ''}>Nederlands</option>
                </select>
            </form>--%>
                <div class="language">
                    <a href="LogIn.jsp"> <!-- Dutch -->
                        <div id="lan_nl">                    
                        </div>                                          
                    </a>   
                    <a href="LogIn.jsp"> <!-- English -->
                        <div id="lan_eng">
                        </div>
                    </a>
                </div>
                <div id="Inlog">
                        <%
                            Cookie[] cookies = request.getCookies();
                            boolean loggedIn = false;
                            
                            if(cookies != null)
                            {
                                for(Cookie cookie : cookies)
                                {
                                    if(cookie.getName().equals("Email"))
                                    {
                                        //String email = cookie.getValue();
                                        loggedIn = true;
                                        %>
                                        <label><% out.print(cookie.getValue()); %></label>
                                        <%                                            
                                    }                                        
                                }
                            }

                            if(loggedIn)
                            {
                                %>
                                <a href="LogIn.jsp">Log out</a>
                                <%
                            }
                            else
                            {
                                %>
                                <label>niet ingelogd</label>
                                <a href="LogIn.jsp">Log In</a>
                                <%
                            }
                            %>
                </div>
                <div class="container row">
                    <div class="col-md-2">
                        <a href="index.jsp">
                            <img id="logo" src="images/logo.jpg"/>
                        </a>
                    </div>
                    <div id="wrapper" class="col-md-10">
                        <div class="collapse navbar-collapse">
                            <br/>
                            <ul class="nav navbar-nav">
                                <li>
                                    <a href="apply.jsp">Apply</a>
                                </li>
                                <li>
                                    <a href="projectoverview.jsp">Project Overview</a>
                                </li>
                                <li>
                                    <a href="Acceptapply.jsp">Pending Requests</a>
                                </li>
                                <li>
                                    <a href="PhotoProfile.jsp">Photo Profile</a>
                                </li>
                            </ul>
                        </div>                     
                    </div>
                </div>
                <div>

                </div>
            </nav>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
        </div>
    </body>
</html>
