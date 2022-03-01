<%@page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <link href="CSS/style.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/header.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/footer.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/dropDown.css" rel="stylesheet" type="text/css"/>
        <script src="js/layout.js"></script>
    </head>
    <body>
        <jsp:include page="components/header.jsp" />

        <jsp:include page="components/loginForm.jsp" />
        
        <jsp:include page="components/footer.jsp" />

        
    </body>
</html>