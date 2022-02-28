<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><i class="fa fa-remove"></i></a>
  <a href="#">Loans</a>
  <a href="${pageContext.request.contextPath}/controller?command=show_books">Books</a>
  <c:if test="${userRole.roleName == 'admin'}">
    <a href="${pageContext.request.contextPath}/controller?command=show_readers">Readers</a>
    <a href="${pageContext.request.contextPath}/controller?command=show_librarians">Librarians</a>
  </c:if>
</div>


<div class="closeNav" onclick="closeNav()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>
