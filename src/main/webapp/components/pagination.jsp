<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="customtags" prefix="page" %>

<div class="pagination">
    <c:choose>
        <c:when test="${not empty bookTitle}">
            <page:htmlPage pageNo="${pageNo}" url="/controller?command=search_book&bookTitle=${bookTitle}" totalSum="${totalSum}" showPage="6" pageSize="${recordsPerPage}"/>
        </c:when>
        <c:when test="${not empty genre}">
            <page:htmlPage pageNo="${pageNo}" url="/controller?command=show_genre_books&genreId=${genre.id}&genreName=${genre.name}" totalSum="${totalSum}" showPage="6" pageSize="${recordsPerPage}"/>
        </c:when>
        <c:when test="${not empty author}">
            <page:htmlPage pageNo="${pageNo}" url="/controller?command=show_author_books&authorId=${author.id}&authorName=${author.name}&authorSurname=${author.surname}" totalSum="${totalSum}" showPage="6" pageSize="${recordsPerPage}"/>
        </c:when>
        <c:otherwise>
            <page:htmlPage pageNo="${pageNo}" url="/controller?command=show_books" totalSum="${totalSum}" showPage="6" pageSize="${recordsPerPage}"/>
        </c:otherwise>
    </c:choose>
    <!-- <c:if test="${pageNo gt 1}">
        <a href="${pageContext.request.contextPath}/controller?command=show_books&pageNo=1">&laquo;</a>
    </c:if>
    
    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">`
                <a href="${pageContext.request.contextPath}/controller?command=show_books&pageNo=${i}" class="active">${i}</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/controller?command=show_books&pageNo=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    
    <c:if test="${pageNo lt numberOfPages}">
        <a href="${pageContext.request.contextPath}/controller?command=show_books&pageNo=${numberOfPages}">&raquo;</a>
    </c:if>     -->
</div>