<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/CSS/genresList.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/genres.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/header.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/dropDown.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/sidebar.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/layout.js"></script>
    <script src="${pageContext.request.contextPath}/js/changeLang.js"></script>
    <script src="${pageContext.request.contextPath}/js/sidebar.js"></script>
    <script src="${pageContext.request.contextPath}/js/genreFormPopup.js"></script>
</head>
  <body>
    <div class="wrapper hover_collapse">
      <!-- Header -->
      <jsp:include page="../components/header.jsp" />

      <!-- Sidebar -->
      <jsp:include page="../components/sidebar.jsp" />
    </div>

    <div class="genres">
      <h2>Genres</h2>
      <c:if test="${ userRole.roleName == 'admin' }">
        <button class="open-button" onclick="openForm()">Add Genre</button>

        <div class="form-popup" id="genreForm">
          <form action="controller?command=add_genre" method="post" class="form-container">
            <button type="button" class="btn cancel" onclick="closeForm()">Close</button>

            <label for="genre"><b>Name</b></label>
            <input type="text" placeholder="Enter Genre" name="genreName" required>

            <button type="submit" class="btn">Add</button>
          </form>
        </div>
      </c:if>
      <jsp:include page="../components/genresList.jsp" />
    </div>


    <!-- Footer -->
    <jsp:include page="../components/footer.jsp" />
  </body>
</html>
