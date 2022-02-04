<%@page language="java" contentType="text/html"%>
<html>
    <body>
        <jsp:include page="layout/header/header.html" />
        <link href="layout/header/header.css" rel="stylesheet" type="text/css"/>

        <div class="container">
            <form method="post" action="controller?command=login">
                <div>
                    <label for="login">Login</label>
                    <input type="text" name="login"/>
                </div>
                <div>
                    <label for="password">Password</label>
                    <input type="password" name="password"/>
                </div>
                <div class="button">
                    <button type="submit">Submit</button>
                </div>
                <div class="errorLoginMessage">${errorLoginPassMessage}</div>
            </form>

        </div>
        <link href="static/style.css" rel="stylesheet" type="text/css"/>

        <jsp:include page="layout/footer/footer.html" />
        <link href="layout/footer/footer.css" rel="stylesheet" type="text/css"/>
    </body>
</html>