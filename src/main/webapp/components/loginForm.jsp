<link href="CSS/loginForm.css" rel="stylesheet" type="text/css"/>
<script src='https://www.google.com/recaptcha/api.js?hl=en'></script>

<form action="controller?command=login" method="post">
    <div class="imgcontainer">
      <img src="images/avatars/man.png" alt="Avatar" class="avatar">
    </div>
  
    <div class="container">
      <label for="login"><b>Login</b></label>
      <input type="text" placeholder="Enter Username" name="login" required>
  
      <label for="password"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="password" required>

      <p class="g-recaptcha" data-sitekey="6LdNP18eAAAAAKI8QhlDTo39vBa1FULfmpT_Kef7"></p>
    
      <button type="submit">Login</button>

      <p class="errorLoginMessage">${errorLoginPassMessage}</p>
    </div>
  
  </form>