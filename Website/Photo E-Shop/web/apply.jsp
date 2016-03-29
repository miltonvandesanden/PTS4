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
        <div class="row">
            <jsp:include page="header.jsp"/>
        </div>
        <div id="content" class="row col-md-12">
            <h1>Apply for account</h1>
            <form method="post" name="form1" action="apply.jsp">
                <div class="row col-md-12">
                    <div class="col-md-1">
                        Company:
                    </div>
                    <div class="col-md-11">
                        <input type="text" name="fCompany">
                    </div>
                </div>
                <div class="row col-md-12">
                    <div class="col-md-1">
                        Place:
                    </div>
                    <div class="col-md-11">
                        <input type="text" name="fPlace">
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
                        Password:
                    </div>
                    <div class="col-md-11">
                        <input type="password" name="fPassword">
                    </div>
                </div>
                <div class="row col-md-12">
                    <div class="col-md-1">
                        <input type="submit">
                    </div>
                </div>                
            </form>
        </div>
        <div class="row">
            <jsp:include page="footer.jsp"/>
        </div> 
    </body>
</html>
