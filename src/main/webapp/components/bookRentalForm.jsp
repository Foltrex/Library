<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <form method="post" action="controller?command=save_rental">

    <input type="hidden" name="bookRentalId" value="${bookRental.id}">

    <div class="row">
      <div class="col-25">
        <label for="user">User:</label>
      </div>
      <div class="col-75">
        <input type="text" id="user-id" name="user" value="${bookRental.user.login}" readonly>
      </div>
    </div>

    

    <div class="row">
      <div class="col-25">
        <label for="borrowDate">Borrow Date:</label>
      </div>
      <div class="col-75">
        <input type="date" id="borrow-date-id" name="borrowDate" value="${bookRental.borrowDate}" required pattern="\d{4}-\d{2}-\d{2}">
      </div>
    </div>


    <div class="row">
        <div class="col-25">
          <label for="returnDate">Return Date:</label>
        </div>
        <div class="col-75">
          <input type="date" id="return-date-id" name="returnDate" value="${bookRental.returnDate}" required pattern="\d{4}-\d{2}-\d{2}">
        </div>
    </div>

    <div class="row">
      <div class="col-25">
        <label for="rentalStatus">Status:</label>
      </div>
      <div class="col-75">
        <select id="rental-status-id" name="rentalStatus">
          <option value="${bookRental.rentalStatus.statusName}">${author.name} ${author.surname}</option>
        </select>
      </div>
    </div>

    <br>
    <div class="row">
      <input type="submit" value="Save">
    </div>
    </form>

    <div class="row">
      <form method="post" action="controller?command=delete_rental">
        <input type="hidden" name="bookRentalId" value="${bookRental.id}">
        <input type="submit" value="Delete">
    </form>
    </div>
  </div>
  