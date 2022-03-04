<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="booksList">
  <ol>
    <hr>
    <c:forEach var="book" items="${books}">
      <li>
        <h2>${book.title}</h2>
        <p>Author name: ${book.author.name}</p>
        <p>Author surname: ${book.author.surname}</p>
        <p>Stock: ${book.stock}</p>
        <p>Genre: ${book.genre.name}</p>

        <c:if test="${ userRole.roleName == 'reader' }">
          <form method="post" action="controller?command=borrow_book">
            <input type="hidden" name="userId" value="${userId}">
            <input type="hidden" name="bookId" value="${book.id}">
            <button type="submit">Borrow book</button>
          </form>
        </c:if>

        <c:if test="${ userRole.roleName == 'admin'}">
          <form method="post" action="controller?command=show_book_details">
            <input type="hidden" name="id" value="${book.id}">
            <button type="submit">Change book</button>
          </form>
        </c:if>

      </li>
      <hr>
    </c:forEach>
  </ol>
</div>