<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="en_US" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<link href="CSS/loginForm.css" rel="stylesheet" type="text/css"/>
<script src='https://www.google.com/recaptcha/api.js?hl=en'></script>

<form action="controller?command=login" method="post">
    <div class="imgcontainer">
      <img src="images/avatars/man.png" alt="Avatar" class="avatar">
    </div>
  
    <div class="container">
      <label for="login"><b><fmt:message key="login.form.login.label"/></b></label>
      <input type="text" placeholder="<fmt:message key="login.form.login.placeholder"/>" name="login" required>
  
      <label for="password"><b><fmt:message key="login.form.password.label"/></b></label>
      <input type="password" placeholder="<fmt:message key="login.form.password.placeholder"/>" name="password" required>

      <p class="g-recaptcha" data-sitekey="6LdNP18eAAAAAKI8QhlDTo39vBa1FULfmpT_Kef7"></p>
    
      <button type="submit"><fmt:message key="login.form.button.login"/></button>

      <p class="errorLoginMessage">${errorLoginPassMessage}</p>
    </div>
  
  </form>