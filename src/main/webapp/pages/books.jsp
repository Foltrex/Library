<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="customtags" prefix="ctg" %>
<script src="https://kit.fontawesome.com/1f4939e33e.js" crossorigin="anonymous"></script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.locale}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><fmt:message key="page.books.title"/></title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon/favicon.ico">

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
    <script src="${pageContext.request.contextPath}/js/searchBar.js"></script>
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

      <div class="pagination">
        <c:choose>
            <c:when test="${not empty bookTitle}">
                <ctg:page pageNo="${pageNo}" url="/controller?command=search_book&bookTitle=${bookTitle}" totalSum="${totalSum}" showPage="6" pageSize="${recordsPerPage}"/>
            </c:when>
            <c:when test="${not empty genre}">
                <ctg:page pageNo="${pageNo}" url="/controller?command=show_genre_books&genreId=${genre.id}&genreName=${genre.name}" totalSum="${totalSum}" showPage="6" pageSize="${recordsPerPage}"/>
            </c:when>
            <c:when test="${not empty author}">
                <ctg:page pageNo="${pageNo}" url="/controller?command=show_author_books&authorId=${author.id}&authorName=${author.name}&authorSurname=${author.surname}" totalSum="${totalSum}" showPage="6" pageSize="${recordsPerPage}"/>
            </c:when>
            <c:otherwise>
                <ctg:page pageNo="${pageNo}" url="/controller?command=show_books" totalSum="${totalSum}" showPage="6" pageSize="${recordsPerPage}"/>
            </c:otherwise>
        </c:choose>
      </div>
    </div>

    <!-- Footer -->
    <jsp:include page="../components/footer.jsp" />
  </body>
</html>
