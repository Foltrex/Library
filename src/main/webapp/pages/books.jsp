<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://kit.fontawesome.com/1f4939e33e.js" crossorigin="anonymous"></script>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="en_US" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.locale}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/CSS/searchBar.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/booksTable.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/books.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/header.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/dropDown.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/sidebar.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/pagination.css" rel="stylesheet" type="text/css"/>
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
    
    <div class="books">
      <h2><fmt:message key="books.title"/>
        <c:if test="${not empty author}">
          (${author.name} ${author.surname})
        </c:if>
        <c:if test="${not empty genre}">
          (${genre.name})
        </c:if>
      </h2>
      <div class="tool_panel">
        <jsp:include page="../components/searchBar.jsp" />
        <c:if test="${ userRole.roleName == 'admin' }">
          <!-- rename and change to get method -->
          <form method="post" action="controller?command=add_book">
            <button type="submit" class="add-book-button">
              <i class="fa fa-plus" style="font-size:36px"></i>
            </button>
          </form>
        </c:if>
      </div>

      <jsp:include page="../components/booksTable.jsp" />

      <jsp:include page="../components/pagination.jsp" />
    </div>

    <!-- Footer -->
    <jsp:include page="../components/footer.jsp" />
  </body>
</html>
