<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="borrowsTable">
    <table>
      <tr>
        <th>User Login</th>
        <th>Borrowed Book</th>
        <th>Borrow Date</th>
        <th>Return Date</th>
        <th>Status</th>
      </tr>


      <c:forEach var="borrow" items="${borrows}">
          <tr>
              <td>${borrow.user.login}</td>
              <td>${borrow.rentedBook.title}</td>
              <!-- add here book author maybe -->
              <td>${borrow.borrowDate}</td>
              <td>${borrow.returnDate}</td>
              <td>${borrow.borrowStatus.statusName}</td>
              <td>
                <c:if test="${ userRole.roleName == 'admin' }">
                  <form method="post" action="controller?command=change_borrow">
                      <input type="hidden" name="bookBorrowId" value="${borrow.id}">
                      <button type="submit">Change</button>
                  </form>
                </c:if>
              </td>
          </tr>
      </c:forEach>
    </table>
  </div>