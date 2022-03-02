<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div class="navbar">
    <div class="library">
        <a href="${pageContext.request.contextPath}/controller?command=show_books">Library</a>
    </div>
    <jsp:include page="./dropDown.jsp" />
</div> -->
<div class="top_navbar">
    <div class="logo">
        <a href="${pageContext.request.contextPath}/controller?command=show_books">Library</a>
    </div>

    <div class="menu">
        <div class="hamburger">
            <i class="fas fa-bars"></i>
        </div>
        
        <jsp:include page="./dropDown.jsp" />
    </div>
</div>
	