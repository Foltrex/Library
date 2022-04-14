<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="authors-list">
  <ol>
    <hr>
    <c:forEach var="author" items="${authors}">
      <li>
        <p>
          <!-- ${pageContext.request.contextPath}/ -->
            <form method="post" action="controller">
                <input type="hidden" name="command" value="show_author_books">

                <input type="hidden" name="authorId" value="${author.id}" />
                <input type="hidden" name="authorName" value="${author.name}" />
                <input type="hidden" name="authorSurname" value="${author.surname}" />
                <button type="submit" class="link-button">${author.name} ${author.surname}</button>
            </form>
        </p>
      </li>
      <hr>
    </c:forEach>
  </ol>
</div>