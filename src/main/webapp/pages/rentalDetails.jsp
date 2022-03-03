
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/CSS/bookRentalForm.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/header.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/dropDown.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/sidebar.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/layout.js"></script>
    <script src="${pageContext.request.contextPath}/js/changeLang.js"></script>
    <script src="${pageContext.request.contextPath}/js/sidebar.js"></script>
</head>
  <body>
    <div class="wrapper hover_collapse">
        <!-- Header -->
        <jsp:include page="../components/header.jsp" />
  
        <!-- Sidebar -->
        <jsp:include page="../components/sidebar.jsp" />
    </div>
      
    <h2>Book</h2>
    <jsp:include page="../components/bookRentalForm.jsp" />

    <!-- Footer -->
    <jsp:include page="../components/footer.jsp" />
    
  </body>
</html>