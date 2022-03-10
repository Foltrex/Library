<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="en_US" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<div class="container">
    <form method="post" action="controller?command=save_borrow">
      <input type="hidden" name="bookBorrowId" value="${bookBorrow.id}">
      <input type="hidden" name="userId" value="${bookBorrow.user.id}">

      <div class="row">
        <div class="col-25">
          <label for="user"><fmt:message key="borrow.details.form.label.user"/>:</label>
        </div>
        <div class="col-75">
          <input type="text" id="user-login" name="userLogin" value="${bookBorrow.user.login}" readonly>
        </div>
      </div>

      <input type="hidden" name="bookId" value="${bookBorrow.rentedBook.id}">

      <div class="row">
        <div class="col-25">
          <label for="user"><fmt:message key="borrow.details.form.label.book"/>:</label>
        </div>
        <div class="col-75">
          <input type="text" id="user-title" name="bookTitle" value="${bookBorrow.rentedBook.title}" readonly>
        </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="borrowDate"><fmt:message key="borrow.details.form.label.borrow.date"/>:</label>
        </div>
        <div class="col-75">
          <input type="date" id="borrow-date-id" name="borrowDate" value="${bookBorrow.borrowDate}" required pattern="\d{4}-\d{2}-\d{2}">
        </div>
      </div>


      <div class="row">
          <div class="col-25">
            <label for="returnDate"><fmt:message key="borrow.details.form.label.return.date"/>:</label>
          </div>
          <div class="col-75">
            <input type="date" id="return-date-id" name="returnDate" value="${bookBorrow.returnDate}" required pattern="\d{4}-\d{2}-\d{2}">
          </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="borrowStatus"><fmt:message key="borrow.details.form.label.status"/>:</label>
        </div>
        <div class="col-75">
          <select id="borrow-status-id" name="borrowStatus">
              <c:forEach var="borrowStatus" items="${borrowStatuses}">
                  <c:if test="${borrowStatus.statusName == bookBorrow.borrowStatus.statusName}">
                    <option value="${borrowStatus.statusName}" selected>${borrowStatus.statusName}</option>
                  </c:if>
                  <c:if test="${ borrowStatus.statusName != bookBorrow.borrowStatus.statusName }">
                    <option value="${borrowStatus.statusName}">${borrowStatus.statusName}</option>
                  </c:if>
              </c:forEach>
          </select>
        </div>
      </div>
    
      <br>
      <div class="row">
        <input type="submit" value="<fmt:message key="borrow.details.form.input.save"/>">
      </div>
    </form>

    <div class="row">
      <form method="post" action="controller?command=delete_borrow">
        <input type="hidden" name="bookBorrowId" value="${bookBorrow.id}">
        <input type="submit" value="<fmt:message key="borrow.details.form.input.delete"/>">
      </form>
    </div>
  </div>
  