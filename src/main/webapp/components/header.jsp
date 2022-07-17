<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/1f4939e33e.js" crossorigin="anonymous"></script>
<div class="top_navbar">
    <c:if test="${ not empty userId }">
        <div class="hamburger">
            <i class="fas fa-bars"></i>
        </div>
    </c:if>

    <div class="logo">
        <c:if test="${ not empty userId }">
            <a href="${pageContext.request.contextPath}/controller?command=show_books">Library</a>
        </c:if>
        <c:if test="${ empty userId }">
            <span>Library</span>
        </c:if>
    </div>


    <div class="menu">
        <c:if test="${ not empty userId }">
            <form  method="get" action="controller" class="logout-form">
                <input type="hidden" name="command" value="logout"/>
                <button class="logout-button">
                    <i class="fas fa-sign-out-alt"></i>
                </button>
            </form>
        </c:if>
        <jsp:include page="./dropDown.jsp" />
    </div>
</div>
	