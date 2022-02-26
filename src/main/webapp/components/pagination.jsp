<c:if test="${currentPage != 1}">
    <td><a href="employee.do?page=${currentPage - 1}">Previous</a></td>
</c:if>
 
 
<button class="dropbtn">
    <i class="fa fa-globe fa-lg"></i>
</button>

<c:if test="${currentPage lt noOfPages}">
    <td><a href="employee.do?page=${currentPage + 1}">Next</a></td>
</c:if>