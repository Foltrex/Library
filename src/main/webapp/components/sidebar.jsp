<!-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><i class="fa fa-remove"></i></a>
  <a href="${pageContext.request.contextPath}/controller?command=show_rentals">Loans</a>
  <a href="${pageContext.request.contextPath}/controller?command=show_books">Books</a>
  <c:if test="${userRole.roleName == 'admin'}">
    <a href="${pageContext.request.contextPath}/controller?command=show_readers">Readers</a>
    <a href="${pageContext.request.contextPath}/controller?command=show_librarians">Librarians</a>
  </c:if>
</div>


<div class="closeNav" onclick="closeNav()" style="cursor:pointer" title="close side menu" id="myOverlay"></div> -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar">
  <div class="sidebar_inner">
    <ul>
      <li>
        <a href="${pageContext.request.contextPath}/controller?command=show_borrows">
          <span class="icon"><i class="fa fa-qrcode"></i></span>
          <span class="text">Borrows</span>
        </a>
      </li>
  
      <li>
        <a href="${pageContext.request.contextPath}/controller?command=show_books">
          <span class="icon"><i class="fa fa-book"></i></span>
          <span class="text">Books</span>
        </a>
      </li>
  
      <c:if test="${userRole.roleName == 'admin'}">
        <li>
          <button class="dropdown-btn">
            <span class="icon"><i class="fas fa-user-alt"></i></span>
            <span class="text">Users</span>
            <span class="icon arrow"><i class="fa fa-caret-down"></i></span>
          </button>
          <div class="dropdown-container">
            <a href="${pageContext.request.contextPath}/controller?command=show_librarians">
              <span class="sub-element">Librarians</span>
            </a>
            <a href="${pageContext.request.contextPath}/controller?command=show_readers">
              <span class="sub-element">Readers</span>
            </a>
          </div>
        </li>
      </c:if>
    </ul>
  </div>
</div>