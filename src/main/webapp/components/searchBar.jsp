<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="en_US" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<form method="post" class="bookSearch" action="controller?command=search_book">
    <input type="text" placeholder="<fmt:message key="books.searchbar"/>..." name="bookTitle">
    <button type="submit"><i class="fa fa-search"></i></button>
</form>