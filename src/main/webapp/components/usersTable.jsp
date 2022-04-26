<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<div class="usersTable">
    <table>
      <tr>
        <th><fmt:message key="users.table.user.name"/></th>
        <th><fmt:message key="users.table.user.surname"/></th>
        <th><fmt:message key="users.table.user.phone.number"/></th>
        <th><fmt:message key="users.table.user.login"/></th>
        <th><fmt:message key="users.table.user.role"/></th>
      </tr>


      <c:forEach var="user" items="${users}">
          <tr>
              <td>${user.name}</td>
              <td>${user.surname}</td>
              <td>${user.phoneNumber}</td>
              <td>${user.login}</td>
              <td><fmt:message key="${user.role.localizedStatusName}"/></td>
              <c:if test="${ userRole.roleName == 'admin'}">
                <td>
                  <form method="post" action="controller?command=change_user_blocking">
                      <input type="hidden" name="userId" value="${user.id}">
                      <input type="hidden" name="userIsBanned" value="${ not user.banned }">
                      <input type="hidden" name="userRole" value="${user.role.roleName}">
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
              </c:if>
          </tr>
      </c:forEach>
    </table>
  </div>