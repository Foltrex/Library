<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.locale}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><fmt:message key="page.readers.title"/></title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon/favicon.ico">

    <link href="${pageContext.request.contextPath}/CSS/usersTable.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/table.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/header.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/dropDown.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/sidebar.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/layout.js"></script>
    <script src="${pageContext.request.contextPath}/js/changeLang.js"></script>
    <script src="${pageContext.request.contextPath}/js/sidebar.js"></script>
</head>
  <body>
    <div class="wrapper hover_collapse">
      <!-- Header -->
      <jsp:include page="../components/header.jsp" />

      <!-- Sidebar -->
      <jsp:include page="../components/sidebar.jsp" />
    </div>

    <div class="table-class">
      <h2><fmt:message key="readers.title"/></h2>
      <jsp:include page="../components/usersTable.jsp" />
    </div>


    <!-- Footer -->
    <jsp:include page="../components/footer.jsp" />
  </body>
</html>
