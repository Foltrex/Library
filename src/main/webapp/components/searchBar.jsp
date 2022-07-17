<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="jquery-3.6.0.js"></script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<form method="get" class="bookSearch" action="controller">
    <div class="autocomplete">
        <input onclick="autocomplete('${bookTitles}')" type="hidden" name="command" value="search_book">
    </div>
    <input id="searching_book" type="text" placeholder="<fmt:message key="books.searchbar"/>..." name="bookTitle">
    <button type="submit"><i class="fa fa-search"></i></button>
</form>