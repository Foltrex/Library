<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<div class="booksList">
  <ol>
    <hr>
    <c:forEach var="book" items="${books}">
      <li>
        <h2 class="title">
          <c:if test="${ userRole.roleName == 'reader' && book.stock > 0 }">
            <form method="post" action="controller?command=borrow_book">
              <input type="hidden" name="userId" value="${userId}">
              <input type="hidden" name="bookId" value="${book.id}">
              <button class="icon" type="submit"><i style="font-size:24px" class="fa fa-plus" style="color:red"></i></button>
            </form>
          </c:if>
          <c:if test="${ userRole.roleName == 'admin'}">
            <form method="get" action="controller">
              <input type="hidden" name="command" value="show_book_details">
              <input type="hidden" name="bookId" value="${book.id}">
              <button class="icon" type="submit"><i style="font-size:24px" class="fa">&#9998;</i></button>
            </form>
          </c:if>
          
          ${book.title}
        </h2>
        <p><fmt:message key="books.table.book.author.name"/>: ${book.author.name}</p>
        <p><fmt:message key="books.table.book.author.surname"/>: ${book.author.surname}</p>
        <p><fmt:message key="books.table.book.stock"/>: ${book.stock}</p>
        <p><fmt:message key="books.table.book.genre"/>: ${book.genre.name}</p>
      </li>
      <hr>
    </c:forEach>
  </ol>
</div>