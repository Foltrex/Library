<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="booksTable">
    <table>
      <tr>
        <th>Title</th>
        <th>AuthorId</th>
        <th>Stock</th>
        <th>GenreId</th>
      </tr>


      <c:forEach var="book" items="${books}">
          <tr>
              <td>${book.title}</td>
              <td>${book.authorId}</td>
              <td>${book.stock}</td>
              <td>${book.genreId}</td>
              <td>
                <form method="post" action="controller?command=show_book_details">
                    <input type="hidden" name="id" value="${book.id}">
                    <button type="submit">Option</button>
                </form>
              </td>
          </tr>
      </c:forEach>
    </table>
  </div>