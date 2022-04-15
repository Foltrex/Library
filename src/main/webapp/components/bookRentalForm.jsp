<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<div class="container">
    <form method="post" action="controller?command=save_rental">
      <input type="hidden" name="bookRentalId" value="${bookRental.id}">
      <input type="hidden" name="userId" value="${bookRental.user.id}">

      <div class="row">
        <div class="col-25">
          <label for="user"><fmt:message key="rental.details.form.label.user"/>:</label>
        </div>
        <div class="col-75">
          <input type="text" id="user-login" name="userLogin" value="${bookRental.user.login}" readonly>
        </div>
      </div>

      <input type="hidden" name="bookId" value="${bookRental.rentedBook.id}">

      <div class="row">
        <div class="col-25">
          <label for="user"><fmt:message key="rental.details.form.label.book"/>:</label>
        </div>
        <div class="col-75">
          <input type="text" id="user-title" name="bookTitle" value="${bookRental.rentedBook.title}" readonly>
        </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="borrowDate"><fmt:message key="rental.details.form.label.borrow.date"/>:</label>
        </div>
        <div class="col-75">
          <input type="date" id="rental-date-id" name="bookRentalBorrowDate" value="${bookRental.borrowDate}" required pattern="\d{4}-\d{2}-\d{2}">
        </div>
      </div>


      <div class="row">
          <div class="col-25">
            <label for="returnDate"><fmt:message key="rental.details.form.label.return.date"/>:</label>
          </div>
          <div class="col-75">
            <input type="date" id="return-date-id" name="bookRentalReturnDate" value="${bookRental.returnDate}" required pattern="\d{4}-\d{2}-\d{2}">
          </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="rentalStatus"><fmt:message key="rental.details.form.label.status"/>:</label>
        </div>
        <div class="col-75">
          <select id="rental-status-id" name="bookRentalRentalStatus">
              <c:forEach var="rentalStatus" items="${rentalStatuses}">
                  <c:if test="${rentalStatus.statusName == bookRental.rentalStatus.statusName}">
                    <option value="${rentalStatus.statusName}" selected>${rentalStatus.statusName}</option>
                  </c:if>
                  <c:if test="${ rentalStatus.statusName != bookRental.rentalStatus.statusName }">
                    <option value="${rentalStatus.statusName}">${rentalStatus.statusName}</option>
                  </c:if>
              </c:forEach>
          </select>
        </div>
      </div>
    
      <br>
      <div class="row">
        <input type="submit" value="<fmt:message key="rental.details.form.input.save"/>">
      </div>
    </form>

    <div class="row">
      <form method="post" action="controller?command=delete_rental">
        <input type="hidden" name="bookRentalId" value="${bookRental.id}">
        <input type="hidden" name="bookId" value="${bookRental.rentedBook.id}">
        <input type="hidden" name="bookRentalRentalStatus" value="${bookRental.rentalStatus.statusName}">
        <input type="submit" value="<fmt:message key="rental.details.form.input.delete"/>">
      </form>
    </div>
  </div>
  