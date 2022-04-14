<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="genres-list">
  <ol>
    <hr>
    <c:forEach var="genre" items="${genres}">
      <li>
        <p>
          <!-- ${pageContext.request.contextPath}/ -->
            <form method="get" action="controller">
                <input type="hidden" name="command" value="show_genre_books" />
                <input type="hidden" name="genreId" value="${genre.id}" />
                <input type="hidden" name="genreName" value="${genre.name}" />
                <button type="submit" class="link-button">${genre.name}</button>
            </form>
        </p>
      </li>
      <hr>
    </c:forEach>
  </ol>
</div>