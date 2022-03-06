<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pagination">
    <c:if test="${currentPage gt 1}">
        <a href="${pageContext.request.contextPath}/controller?command=show_books&currentPage=1">&laquo;</a>
    </c:if>
    
    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <a href="${pageContext.request.contextPath}/controller?command=show_books&currentPage=${i}" class="active">${i}</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/controller?command=show_books&currentPage=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    
    <c:if test="${currentPage lt numberOfPages}">
        <a href="${pageContext.request.contextPath}/controller?command=show_books&currentPage=${numberOfPages}">&raquo;</a>
    </c:if>    
</div>