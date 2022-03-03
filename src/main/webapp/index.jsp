<%@page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="${pageContext.request.contextPath}/CSS/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/CSS/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/CSS/footer.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/CSS/dropDown.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/layout.js"></script>
        <script src="${pageContext.request.contextPath}/js/changeLang.js"></script>
    </head>
    <body>
        <jsp:include page="components/header.jsp" />

        <jsp:include page="components/loginForm.jsp" />
        
        <jsp:include page="components/footer.jsp" />

        
    </body>
</html>

