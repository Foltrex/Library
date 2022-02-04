<%@page language="java" contentType="text/html"%>
<html>
    <header>
        <jsp:include page="layout/header/header.html" />
        <link href="layout/header/header.css" rel="stylesheet" type="text/css">
    <header/>
    <body>
        <div class="container">
            <form method="post" action="controller?command=login">
                        <label for="login">login</label>
                        <input type="text" name="login"/>
                        <label for="password">password</label>
                        <input type="password" name="password"/>
                        <input type="submit"/>
            </form>
        </div>
        <link href="static/style.css" rel="stylesheet" type="text/css"/>
    </body>
    <footer>
        <jsp:include page="layout/footer/footer.html" />
        <link href="layout/footer/footer.css" rel="stylesheet" type="text/css">
    <footer/>
</html>