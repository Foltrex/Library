<c:if test="${currentPage != 1}">
    <td><a href="controller?page=${currentPage - 1}">Previous</a></td>
</c:if>

<c:if test="${currentPage lt noOfPages}">
    <td><a href="controller?page=${currentPage + 1}">Next</a></td>
</c:if>