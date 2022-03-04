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
  
      <c:if test="${userRole.roleName == 'admin'}">
        <li>
          <a href="${pageContext.request.contextPath}/controller?command=show_readers">
            <span class="icon"><i class="fas fa-user-alt"></i></span>
            <span class="text">Readers</span>
          </a>
        </li>
    
        <li>
          <a href="${pageContext.request.contextPath}/controller?command=show_librarians">
            <span class="icon"><i class="fas fa-user-alt"></i></span>
            <span class="text">Librarians</span>
          </a>
        </li>
      </c:if>
  
      <li>
        <a href="${pageContext.request.contextPath}/controller?command=show_books">
          <span class="icon"><i class="fa fa-book"></i></span>
          <span class="text">Books</span>
        </a>
      </li>
  
      <li>
        <a href="#">
          <span class="icon"><i class="fa fa-question-circle"></i></span>
          <span class="text">About</span>
        </a>
      </li>
    </ul>
  </div>
</div>