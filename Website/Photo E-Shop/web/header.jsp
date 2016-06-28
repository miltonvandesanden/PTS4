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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Languages.Language" />
<!DOCTYPE html>
<html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-Copy</title>
        <link rel="stylesheet" href="bootstrap.css"/>
    </head>
    <body>
        <div class="row">
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="language">
                    <form>
                        <select id="language" name="language" onchange="submit()">
                            <option value="en" ${language == 'en' ? 'selected' : ''} style="background:url(images/EN.png) no-repeat; padding-left: 35px;">English</option>
                            <option value="nl" ${language == 'nl' ? 'selected' : ''} style="background:url(images/NL.png) no-repeat; padding-left: 35px;">Nederlands</option>
                        </select>
                    </form>
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
                                        <label><fmt:message key="header.nav.loggedinas" />: <% out.print(cookie.getValue()); %></label>
                                    <%                                            
                                }                                        
                            }
                        }

                        if(loggedIn)
                        {
                            %>
                                <a href="LogIn.jsp"><fmt:message key="header.nav.logout" /></a>
                            <%
                        }
                        else
                        {
                            %>
                                <label><fmt:message key="header.nav.notloggedin" /></label>
                                <a href="LogIn.jsp"><fmt:message key="header.nav.login" /></a>
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
                    <div id="wrapperfix" class="fe">
                        <div class="collapse navbar-collapse">
                            <br/>
                            <ul class="nav navbar-nav">
                                <li>
                                    <a href="apply.jsp"><fmt:message key="header.link.apply" /></a>
                                </li>
                                <li>
                                    <a href="projectoverview.jsp"><fmt:message key="header.link.projectOverview" /></a>
                                </li>
                                <li>
                                    <a href="Acceptapply.jsp"><fmt:message key="header.link.pendingRequests" /></a>
                                </li>
                                <li>
                                    <a href="PhotoProfile.jsp"><fmt:message key="header.link.photoProfile" /></a>
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
