<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <form method="post" action="controller?command=save_book_to_database">

    <input type="hidden" name="bookId" value="${book.id}">

    <div class="row">
      <div class="col-25">
        <label for="bookTitle">Title:</label>
      </div>
      <div class="col-75">
        <input type="text" id="title" name="bookTitle" value="${book.title}" placeholder="Book title...">
      </div>
    </div>

    <div class="row">
      <div class="col-25">
        <label for="bookAuthor">Author:</label>
      </div>
      <div class="col-75">
        <select id="author" name="bookAuthor">
            <c:forEach var="author" items="${authors}">
              <c:choose>
                <c:when test="${ not empty book && not empty book.author && not empty book.author.id  && author.id == book.author.id }">
                  <option value="${author.id}" selected>${author.name} ${author.surname}</option>
                </c:when>
                <c:otherwise>
                  <option value="${author.id}">${author.name} ${author.surname}</option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
        </select>
      </div>
    </div>

    <div class="row">
      <div class="col-25">
        <label for="bookStock">Stock:</label>
      </div>
      <div class="col-75">
        <input type="text" id="stock" name="bookStock" value="${book.stock}" placeholder="Book stock..">
      </div>
    </div>

    <div class="row">
        <div class="col-25">
          <label for="bookGenre">Genre:</label>
        </div>
        <div class="col-75">
          <select id="genre" name="bookGenre">
              <c:forEach var="genre" items="${genres}">
                <c:choose>
                  <c:when test="${ not empty book && not empty book.genre && genre.id == book.genre.id}">
                    <option value="${genre.id}" selected>${genre.name}</option>
                  </c:when>
                  <c:otherwise>
                    <option value="${genre.id}">${genre.name}</option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
          </select>
        </div>
      </div>

    <br>
    <div class="row">
      <input type="submit" value="Save">
    </div>
    </form>
  </div>
  