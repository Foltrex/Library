<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://kit.fontawesome.com/1f4939e33e.js" crossorigin="anonymous"></script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.locale}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><fmt:message key="page.errorPage.title"/></title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon/favicon.ico">

    <link href="${pageContext.request.contextPath}/CSS/errorPage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/fonts.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/errorInputPage.js" defer></script>
</head>
<body>
    <main>
        <div class="container">
          <div class="row">
            <div class="col-md-6 align-self-center">
              <img src = "images/svg/gears.svg"/>
            </div>
            <div class="col-md-6 align-self-center">
              <h1>406</h1>
              <h2><fmt:message key="invalid.input.page.title" />.</h2>
              <p>${errorMessage}</p>
              <form method="post" 
                <c:if test="${ not empty userId }">
                    action="controller?command=show_books">
                </c:if>
                <c:if test="${ empty userId }">
                    action="index.jsp">
                </c:if>
                <button class="btn green" type="submit">
                    <fmt:message key="error.page.button" />
                  </button>
              </form>
            </div>
          </div>
        </div>
      </main>
</body>