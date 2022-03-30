<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.locale}">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title><fmt:message key="page.index.title"/></title>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon/favicon.ico">

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

