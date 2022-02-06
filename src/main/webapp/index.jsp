<%@page language="java" contentType="text/html"%>
<html>
    <head>
        <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
    </head>
    <body>
        <jsp:include page="layout/header/header.html" />
        <link href="layout/header/header.css" rel="stylesheet" type="text/css"/>


        <form method="post" action="controller?command=login">
            <p class="field">
                <input type="text" name="login" placeholder="Login"/>
            </p>
            <p class="field">
                <input type="password" name="password" placeholder="Password"/>
            </p>
            <p class="g-recaptcha"
                data-sitekey="6LdNP18eAAAAAKI8QhlDTo39vBa1FULfmpT_Kef7">
            </p>
            <button type="submit">Submit</button>
            <p class="errorLoginMessage">${errorLoginPassMessage}</p>
        </form>
        <link href="static/style.css" rel="stylesheet" type="text/css"/>

        <jsp:include page="layout/footer/footer.html" />
        <link href="layout/footer/footer.css" rel="stylesheet" type="text/css"/>
    </body>
</html>