<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="en_US" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<script src="https://kit.fontawesome.com/1f4939e33e.js" crossorigin="anonymous"></script>

<div class="sidebar">
  <div class="sidebar_inner">
    <ul>
      <li>
        <a href="${pageContext.request.contextPath}/controller?command=show_borrows">
          <span class="icon"><i class="fa fa-qrcode"></i></span>
          <span class="text"><fmt:message key="sidebar.book.rental"/></span>
        </a>
      </li>
  
      <li>
        <a href="${pageContext.request.contextPath}/controller?command=show_authors">
          <span class="icon"><i class="fa-solid fa-feather-pointed"></i></i></span>
          <span class="text"><fmt:message key="sidebar.authors"/></span>
        </a>
      </li>

      <li>
        <a href="${pageContext.request.contextPath}/controller?command=show_genres">
          <span class="icon"><i class='fas fa-layer-group'></i></span>
          <span class="text"><fmt:message key="sidebar.genres"/></span>
        </a>
      </li>

      <li>
        <a href="${pageContext.request.contextPath}/controller?command=show_books">
          <span class="icon"><i class="fa fa-book"></i></span>
          <span class="text"><fmt:message key="sidebar.books"/></span>
        </a>
      </li>
  
      <c:if test="${userRole.roleName == 'admin'}">
        <li>
          <button class="dropdown-btn">
            <span class="icon"><i class="fas fa-user-alt"></i></span>
            <span class="text"><fmt:message key="sidebar.users"/></span>
            <span class="icon arrow"><i class="fa fa-caret-down"></i></span>
          </button>
          <div class="dropdown-container">
            <a href="${pageContext.request.contextPath}/controller?command=show_librarians">
              <span class="sub-element"><fmt:message key="sidebar.librarians"/></span>
            </a>
            <a href="${pageContext.request.contextPath}/controller?command=show_readers">
              <span class="sub-element"><fmt:message key="sidebar.readers"/></span>
            </a>
          </div>
        </li>
      </c:if>
    </ul>
  </div>
</div>