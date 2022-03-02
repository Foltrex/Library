<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="en_US" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html lang="${sessionScope.locale}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/CSS/booksTable.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/books.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/header.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/footer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/dropDown.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/CSS/sidebar.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/layout.js"></script>
    <script src="${pageContext.request.contextPath}/js/changeLang.js"></script>
</head>
  <body>
    <div class="wrapper hover_collapse">
        <!-- Header -->
        <jsp:include page="../components/header.jsp" />

        <!-- Sidebar -->
        <jsp:include page="../components/sidebar.jsp" />
    </div>
    
    <div class="books">
      <h2><fmt:message key="books.title" /></h2>
      <jsp:include page="../components/booksTable.jsp" />
    </div>

    <!-- Footer -->
    <jsp:include page="../components/footer.jsp" />


    <script type="text/javascript">
      //I have determined the constant of some class function
       var li_items = document.querySelectorAll(".sidebar ul li");
      var hamburger = document.querySelector(".hamburger");
      var wrapper = document.querySelector(".wrapper");
      
      //What will change if you move the mouse over the sidebar
      
      li_items.forEach((li_item)=>{
        li_item.addEventListener("mouseenter", ()=>{
      
      
            li_item.closest(".wrapper").classList.remove("hover_collapse");
            //I have already added style information about hover_collapse
      
        })
        //In general, hover_collapse will be applied on the sidebar.
      
      //Hover_collapse will be removed from the sidebar when the mouse is moved
      })
      
      li_items.forEach((li_item)=>{
        li_item.addEventListener("mouseleave", ()=>{
      
            li_item.closest(".wrapper").classList.add("hover_collapse");
            //Hover Collapse will be applied again when the mouse is removed
      
        })
      })
      
      
      //Now I will execute the menu button
      
      //I have instructed here that hover_collapse will be applied and removed when the menu button is clicked.
      
      //This means that the first click will be applied and the second click will be removed
      hamburger.addEventListener("click", () => {
      
        hamburger.closest(".wrapper").classList.toggle("hover_collapse");
      })
      </script>
  </body>
</html>
