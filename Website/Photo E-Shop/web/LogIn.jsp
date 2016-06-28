<%-- 
    Document   : LogIn
    Created on : 22-mrt-2016, 9:07:35
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
        <jsp:include page="header.jsp"/>
        
        <%
        Cookie[] cookies = request.getCookies();
        boolean loggedIn = false;

        if(cookies != null)
        {
            for(Cookie cookie : cookies)
            {
                if(cookie.getName().equals("Email"))
                {
                    
                    loggedIn = true;
                    
                    Cookie killMyCookie = cookie;
                    killMyCookie.setMaxAge(0);
                    //killMyCookie.setPath("/");
                    response.addCookie(killMyCookie);
                    
                    response.sendRedirect("index.jsp");
                }                    
            }
        }
        
        if(!loggedIn)
        {
        %>
        <div id="content" class="row col-md-12">
            <h1><fmt:message key="login.label.head" /></h1>
            <form method="post" name="form1" action="${pageContext.request.contextPath}/MyServlet">
                <div class="break">
                    <div class="col-md-1">
                        <fmt:message key="login.label.email" />:
                    </div>
                    <div class="col-md-11">
                        <input type="text" name="fEmail">
                    </div>
                </div>
                <div class="break">
                    <div class="col-md-1">
                        <fmt:message key="login.label.password" />:
                    </div>
                    <div class="col-md-11">
                        <input type="password" name="fPassword">
                    </div>
                </div>
                <div class="break">
                    <div class="col-md-1">
                        <input type="submit" value="<fmt:message key="login.button.submit" />">
                    </div>
                </div>                
            </form>
        </div>   
        <%
        }
        %>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
