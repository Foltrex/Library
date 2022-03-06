<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="en_US" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.locale}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/CSS/authorsList.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/authors.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/header.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/dropDown.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/sidebar.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/layout.js"></script>
    <script src="${pageContext.request.contextPath}/js/changeLang.js"></script>
    <script src="${pageContext.request.contextPath}/js/sidebar.js"></script>
    <script src="${pageContext.request.contextPath}/js/authorFormPopup.js"></script>
</head>
  <body>
    <div class="wrapper hover_collapse">
      <!-- Header -->
      <jsp:include page="../components/header.jsp" />

      <!-- Sidebar -->
      <jsp:include page="../components/sidebar.jsp" />
    </div>

    <div class="authors">
      <h2><fmt:message key="authors.title"/></h2>
      <c:if test="${ userRole.roleName == 'admin' }">
        <button class="open-button" onclick="openForm()">
          <fmt:message key="authors.button.open"/>
        </button>

        <div class="form-popup" id="authorForm">
          <form action="controller?command=add_author" method="post" class="form-container">
            <button type="button" class="btn cancel" onclick="closeForm()">
              <fmt:message key="authors.button.close"/>
            </button>

            <label for="authorName"><b><fmt:message key="authors.label.name"/></b></label>
            <input type="text" placeholder="<fmt:message key="authors.input.placeholder.name"/>" name="authorName" required>
    
            <label for="authorSurname"><b><fmt:message key="authors.label.surname"/></b></label>
            <input type="text" placeholder="<fmt:message key="authors.input.placeholder.surname"/>" name="authorSurname" required>

            <button type="submit" class="btn"><fmt:message key="authors.button.add"/></button>
          </form>
        </div>
      </c:if>
      <jsp:include page="../components/authorsList.jsp" />
    </div>


    <!-- Footer -->
    <jsp:include page="../components/footer.jsp" />
  </body>
</html>
