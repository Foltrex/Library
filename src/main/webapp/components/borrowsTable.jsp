<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="en_US" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<div class="borrowsTable">
    <table>
      <tr>
        <th><fmt:message key="borrows.user.login"/></th>
        <th><fmt:message key="borrows.borrowed.book"/></th>
        <th><fmt:message key="borrows.borrow.date"/></th>
        <th><fmt:message key="borrows.return.date"/></th>
        <th><fmt:message key="borrows.status"/></th>
      </tr>


      <c:forEach var="borrow" items="${borrows}">
          <tr>
              <td>${borrow.user.login}</td>
              <td>${borrow.rentedBook.title}</td>
              <td>${borrow.borrowDate}</td>
              <td>${borrow.returnDate}</td>
              <td>${borrow.borrowStatus.statusName}</td>
              <td>
                <c:if test="${ userRole.roleName == 'librarian' }">
                  <!-- rename and change method -->
                  <form method="post" action="controller?command=change_borrow">
                      <input type="hidden" name="bookBorrowId" value="${borrow.id}">
                      <button type="submit"><i style="font-size:24px" class="fa">&#9998;</i></button>
                  </form>
                </c:if>
              </td>
          </tr>
      </c:forEach>
    </table>
  </div>