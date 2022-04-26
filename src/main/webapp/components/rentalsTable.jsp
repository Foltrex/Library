<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<div class="rentalsTable">
    <table>
      <tr>
        <c:if test="${ userRole.roleName != 'reader' }">
          <th><fmt:message key="rentals.user.login"/></th>
        </c:if>
        <th><fmt:message key="rentals.rented.book"/></th>
        <th><fmt:message key="rentals.borrow.date"/></th>
        <th><fmt:message key="rentals.return.date"/></th>
        <th><fmt:message key="rentals.status"/></th>
      </tr>


      <c:forEach var="rental" items="${rentals}">
          <tr>
              <c:if test="${ userRole.roleName != 'reader' }">
                <td>${rental.user.login}</td>
              </c:if>
              <td>${rental.rentedBook.title}</td>
              <td><fmt:formatDate value="${rental.borrowDate}" /></td>
              <td><fmt:formatDate value="${rental.returnDate}" /></td>
              <td><fmt:message key="${rental.rentalStatus.localizedStatusName}"/></td>
              <td>
                <c:if test="${ userRole.roleName == 'librarian' }">
                  <form method="get" action="controller">
                      <input type="hidden" name="command" value="show_book_rental_details">
                      <input type="hidden" name="bookRentalId" value="${rental.id}">
                      <input type="hidden" name="bookId" value="${rental.rentedBook.id}">
                      <button type="submit" class="rental-button"><i style="font-size:24px" class="fa">&#9998;</i></button>
                  </form>
                </c:if>
              </td>
          </tr>
      </c:forEach>
    </table>
  </div>