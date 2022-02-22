package com.epam.library.command;

import com.epam.library.exception.ServiceException;
import com.epam.library.entity.User;
import com.epam.library.service.UserServiceImpl;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String RECAPTCHA_SECRET_KEY = "6LdNP18eAAAAAKBbUyBYyocuoqhizVQlu8M4A55V";
    private static final String RECAPTCHA_SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    private static final String MAIN_PAGE = "controller?command=main";
    private static final String LOGIN_PAGE = "index.jsp";

    private UserServiceImpl service;

    public LoginCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String gReCaptchaResponse = request.getParameter("g-recaptcha-response");

        Optional<User> user = service.login(login, password);

        CommandResult result;
        if (user.isPresent() && verifyReCaptcha(gReCaptchaResponse)) {
            HttpSession httpSession = request.getSession();

            User registeredUser = user.get();
            httpSession.setAttribute("userId", registeredUser.getId());
            httpSession.setAttribute("userRole", registeredUser.getRole());
            result = CommandResult.redirect(MAIN_PAGE);
        } else {
            request.setAttribute("errorLoginPassMessage", "Invalid credentials or you're a robot");
            result = CommandResult.forward(LOGIN_PAGE);
        }

        return result;
    }


    private boolean verifyReCaptcha(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
            return false;
        }

        boolean result = false;
        JsonReader jsonReader = null;
        try {
            URL verifyUrl = new URL(RECAPTCHA_SITE_VERIFY_URL);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) verifyUrl.openConnection();

            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setRequestProperty("User-Agent", "Chrome");
            httpsURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            httpsURLConnection.setDoOutput(true);

            String postParams = String.format("%s%s%s%s", "secret=", RECAPTCHA_SECRET_KEY, "&response=", gRecaptchaResponse);

            OutputStream outStream = httpsURLConnection.getOutputStream();
            outStream.write(postParams.getBytes());

            outStream.flush();
            outStream.close();

            InputStream inputStream = httpsURLConnection.getInputStream();
            jsonReader = Json.createReader(inputStream);
            JsonObject jsonObject = jsonReader.readObject();

            result = jsonObject.getBoolean("success");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (jsonReader != null) {
                jsonReader.close();
            }
        }

        return result;
    }
}
