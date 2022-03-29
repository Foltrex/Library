<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<div class="rentalsTable">
    <table>
      <tr>
        <th><fmt:message key="rentals.user.login"/></th>
        <th><fmt:message key="rentals.rented.book"/></th>
        <th><fmt:message key="rentals.borrow.date"/></th>
        <th><fmt:message key="rentals.return.date"/></th>
        <th><fmt:message key="rentals.status"/></th>
      </tr>


      <c:forEach var="rental" items="${rentals}">
          <tr>
              <td>${rental.user.login}</td>
              <td>${rental.rentedBook.title}</td>
              <td>${rental.borrowDate}</td>
              <td>${rental.returnDate}</td>
              <td>${rental.rentalStatus.statusName}</td>
              <td>
                <c:if test="${ userRole.roleName == 'librarian' && not empty rental.rentalStatus && rental.rentalStatus.statusName != 'returned' }">
                  <!-- rename and change method -->
                  <form method="post" action="controller?command=show_book_rental_details">
                      <input type="hidden" name="bookRentalId" value="${rental.id}">
                      <input type="hidden" name="bookId" value="${rental.rentedBook.id}">
                      <button type="submit"><i style="font-size:24px" class="fa">&#9998;</i></button>
                  </form>
                </c:if>
              </td>
          </tr>
      </c:forEach>
    </table>
  </div>