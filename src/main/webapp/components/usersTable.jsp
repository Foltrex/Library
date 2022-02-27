<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="usersTable">
    <table>
      <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Phone Number</th>
        <th>Login</th>
        <th>Role</th>
        <th>Option</th>
      </tr>


      <c:forEach var="user" items="${users}">
          <tr>
              <td>${user.name}</td>
              <td>${user.surname}</td>
              <td>${user.phoneNumber}</td>
              <td>${user.login}</td>
              <td>${user.role}</td>
              <td>
                <form method="post" action="controller?command=show_user_details">
                    <input type="hidden" name="userName" value="${userName}">
                    <button type="submit">Option</button>
                </form>
              </td>
          </tr>
      </c:forEach>
    </table>
  </div>