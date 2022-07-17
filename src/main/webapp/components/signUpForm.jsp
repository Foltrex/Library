<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<link href="CSS/signUpForm.css" rel="stylesheet" type="text/css"/>

<div id="id01" class="modal">
    <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
    <form class="modal-content" action="controller?command=save_user" method="post">
      <div class="sign-up-container">

        <h1><fmt:message key="signup.form.title"/></h1>
        <p><fmt:message key="signup.form.request"/></p>
        <hr>
        <label for="name"><b><fmt:message key="signup.form.label.name"/></b></label>
        <input type="text" placeholder="<fmt:message key="signup.form.input.placeholder.name"/>" name="userName" required>
  
        <label for="surname"><b><fmt:message key="signup.form.label.surname"/></b></label>
        <input type="text" placeholder="<fmt:message key="signup.form.input.placeholder.surname"/>" name="userSurname" required>
  
        <label for="phone_number"><b><fmt:message key="signup.form.label.phone.number"/></b></label>
        <input type="tel" pattern="+375([0-9]{2})[0-9]{3}-[0-9]{2}-[0-9]{2}" placeholder="+375(##)###-##-##" name="userPhoneNumber" required>

        <label for="login"><b><fmt:message key="signup.form.label.login"/></b></label>
        <input type="text" placeholder="<fmt:message key="signup.form.input.placeholder.login"/>" name="userLogin" required>
  
        <label for="password"><b><fmt:message key="signup.form.label.password"/></b></label>
        <input type="password" placeholder="<fmt:message key="signup.form.input.placeholder.password"/>" name="userPassword" id="pwd" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>


        <input type="checkbox" onclick="showPassword()"> <fmt:message key="signup.form.checkbox.show.password"/>

        <div class="clearfix">
          <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn"><fmt:message key="signup.form.button.cancel"/></button>
          <button type="submit" class="signupbtn"><fmt:message key="signup.form.button.signup"/></button>
        </div>
      </div>

      <div id="message">
        <h3><fmt:message key="signup.form.message.title"/></h3>
        <p id="letter" class="invalid"><fmt:message key="signup.form.message.letter.constraint"/></p>
        <p id="capital" class="invalid"><fmt:message key="signup.form.message.capital.letter.constraint"/></p>
        <p id="number" class="invalid"><fmt:message key="signup.form.message.number.constraint"/></p>
        <p id="length" class="invalid"><fmt:message key="signup.form.message.length.constraint"/></p>
      </div>
    </form>
</div>