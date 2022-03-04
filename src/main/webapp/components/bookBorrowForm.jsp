<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <form method="post" action="controller?command=save_borrow">
      <input type="hidden" name="bookBorrowId" value="${bookBorrow.id}">
      <input type="hidden" name="userId" value="${bookBorrow.user.id}">

      <div class="row">
        <div class="col-25">
          <label for="user">User:</label>
        </div>
        <div class="col-75">
          <input type="text" id="user-login" name="userLogin" value="${bookBorrow.user.login}" readonly>
        </div>
      </div>

      <input type="hidden" name="bookId" value="${bookBorrow.rentedBook.id}">

      <div class="row">
        <div class="col-25">
          <label for="user">Book:</label>
        </div>
        <div class="col-75">
          <input type="text" id="user-title" name="bookTitle" value="${bookBorrow.rentedBook.title}" readonly>
        </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="borrowDate">Borrow Date:</label>
        </div>
        <div class="col-75">
          <input type="date" id="borrow-date-id" name="borrowDate" value="${bookBorrow.borrowDate}" required pattern="\d{4}-\d{2}-\d{2}">
        </div>
      </div>


      <div class="row">
          <div class="col-25">
            <label for="returnDate">Return Date:</label>
          </div>
          <div class="col-75">
            <input type="date" id="return-date-id" name="returnDate" value="${bookBorrow.returnDate}" required pattern="\d{4}-\d{2}-\d{2}">
          </div>
      </div>

      <div class="row">
        <div class="col-25">
          <label for="borrowStatus">Status:</label>
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
        <input type="submit" value="Save">
      </div>
    </form>

    <div class="row">
      <form method="post" action="controller?command=delete_borrow">
        <input type="hidden" name="bookBorrowId" value="${bookBorrow.id}">
        <input type="submit" value="Delete">
      </form>
    </div>
  </div>
  