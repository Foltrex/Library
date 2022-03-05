<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="usersTable">
    <table>
      <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Phone Number</th>
        <th>Login</th>
        <th>Role</th>
      </tr>


      <c:forEach var="user" items="${users}">
          <tr>
              <td>${user.name}</td>
              <td>${user.surname}</td>
              <td>${user.phoneNumber}</td>
              <td>${user.login}</td>
              <td>${user.role}</td>
              <td>
                <form method="post" action="controller?command=change_user_blocking">
                    <input type="hidden" name="userId" value="${user.id}">
                    <input type="hidden" name="userBlocking" value="${ not user.banned }">
                    <input type="hidden" name="userRole" value="${user.role}">
                    <button type="submit">
                      <c:if test="${user.banned}">
                        <i class="fa fa-lock" style="font-size:24px"></i>
                      </c:if>
                      <c:if test="${not user.banned}">
                        <i class='fas fa-lock-open' style='font-size:24px'></i>
                      </c:if>
                    </button>
                </form>
              </td>
          </tr>
      </c:forEach>
    </table>
  </div>