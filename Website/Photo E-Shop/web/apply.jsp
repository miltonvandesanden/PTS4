<%-- 
    Document   : apply
    Created on : 29-mrt-2016, 10:13:11
    Author     : Danny
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
        <div id="content" class="col-md-12">
            <h1><fmt:message key="apply.label.head" /></h1>
            <form method="post" name="form1" action="${pageContext.request.contextPath}/applyclass">
                <div class="col-md-12">
                    <div class="col-md-1">
                        <fmt:message key="apply.label.companyname" />:
                    </div>
                    <div class="col-md-11">
                        <input type="text" name="fCompany">
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="col-md-1">
                        <fmt:message key="apply.label.email" />:
                    </div>
                    <div class="col-md-11">
                        <input type="text" name="fEmail">
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="col-md-1">
                        <fmt:message key="apply.label.city" />:
                    </div>
                    <div class="col-md-11">
                        <input type="text" name="fCity">
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="col-md-1">
                        <fmt:message key="apply.label.address" />:
                    </div>
                    <div class="col-md-11">
                        <input type="text" name="fAddress">
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="col-md-1">
                        <input type="submit" name="sendApplyForm" value=<fmt:message key="apply.button.sendapply" /> />
                    </div>
                </div>                
            </form>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
