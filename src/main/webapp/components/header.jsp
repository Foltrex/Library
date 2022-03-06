<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/1f4939e33e.js" crossorigin="anonymous"></script>
<div class="top_navbar">
    <div class="logo">
        <a href="${pageContext.request.contextPath}/controller?command=show_books">Library</a>
    </div>

    <div class="menu">
        <div class="hamburger">
            <i class="fas fa-bars"></i>
        </div>
        
        <jsp:include page="./dropDown.jsp" />
        
        <!-- <c:if test="${ not empty userId }">
            <form action="controller?command=logout" method="post">
                <button>
                    <i class="fas fa-sign-out-alt"></i>
                </button>
            </form>
        </c:if> -->
    </div>
</div>
	