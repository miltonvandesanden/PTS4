<%-- 
    Document   : apply
    Created on : 29-mrt-2016, 10:13:11
    Author     : Danny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-Copy</title>
        <link rel="stylesheet" href="bootstrap.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id="content" class="row col-md-12">
            <h1>Apply for account</h1>
            <form method="post" name="form1" action="${pageContext.request.contextPath}/applyclass">
                <div class="row col-md-12">
                    <div class="col-md-1">
                        Company name:
                    </div>
                    <div class="col-md-11">
                        <input type="text" name="fCompany">
                    </div>
                </div>
                <div class="row col-md-12">
                    <div class="col-md-1">
                        Email:
                    </div>
                    <div class="col-md-11">
                        <input type="text" name="fEmail">
                    </div>
                </div>
                <div class="row col-md-12">
                    <div class="col-md-1">
                        City:
                    </div>
                    <div class="col-md-11">
                        <input type="text" name="fCity">
                    </div>
                </div>
                <div class="row col-md-12">
                    <div class="col-md-1">
                        Address:
                    </div>
                    <div class="col-md-11">
                        <input type="text" name="fAddress">
                    </div>
                </div>
                <div class="row col-md-12">
                    <div class="col-md-1">
                        <input type="submit" name="sendApplyForm">
                    </div>
                </div>                
            </form>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
