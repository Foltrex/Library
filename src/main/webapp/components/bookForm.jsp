<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<div class="container">
    <form method="post" action="controller?command=save_book">

    <input type="hidden" name="bookId" value="${book.id}">

    <div class="row">
      <div class="col-25">
        <label for="bookTitle">
          <fmt:message key="book.details.form.label.title"/>:
        </label>
      </div>
      <div class="col-75">
        <input type="text" id="title" name="bookTitle" value="${book.title}" 
              placeholder="<fmt:message key="book.details.form.input.title.placeholder"/>...">
      </div>
    </div>

    <div class="row">
      <div class="col-25">
        <label for="authorId"><fmt:message key="book.details.form.label.author"/>:</label>
      </div>
      <div class="col-75">
        <select id="author" name="authorId">
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
        <label for="bookStock"><fmt:message key="book.details.form.label.stock"/>:</label>
      </div>
      <div class="col-75">
        <input type="text" id="stock" name="bookStock" value="${book.stock}" 
              placeholder="<fmt:message key="book.details.form.input.stock.placeholder"/>...">
      </div>
    </div>

    <div class="row">
        <div class="col-25">
          <label for="genreId"><fmt:message key="book.details.form.label.genre"/>:</label>
        </div>
        <div class="col-75">
          <select id="genre" name="genreId">
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
      <input type="submit" value="<fmt:message key="book.details.form.submit"/>">
    </div>
    </form>
  </div>
  