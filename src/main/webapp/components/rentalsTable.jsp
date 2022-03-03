<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="rentalsTable">
    <table>
      <tr>
        <th>User Login</th>
        <th>Rented Book</th>
        <th>Borrow Date</th>
        <th>Return Date</th>
        <th>Status</th>
      </tr>


      <c:forEach var="rental" items="${rentals}">
          <tr>
              <td>${rental.user.login}</td>
              <td>${rental.rentedBook.title}</td>
              <!-- add here book author maybe -->
              <td>${rental.borrowDate}</td>
              <td>${rental.returnDate}</td>
              <td>${rental.rentalStatus.statusName}</td>
              <td>
                <form method="post" action="controller?command=change_rental">
                    <input type="hidden" name="bookRentalId" value="${rental.id}">
                    <button type="submit">Cancel</button>
                </form>
              </td>
          </tr>
      </c:forEach>
    </table>
  </div>