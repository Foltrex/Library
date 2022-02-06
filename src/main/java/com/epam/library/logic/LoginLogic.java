package com.epam.library.logic;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LoginLogic {
    private final static String ADMIN_LOGIN = "admin";
    private final static String ADMIN_PASS = "admin";

    private static final String SECRET_KEY = "6LdNP18eAAAAAKBbUyBYyocuoqhizVQlu8M4A55V";
    private static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";


    public boolean checkLogin(String enterLogin, String enterPass, String gRecaptchaResponse) {
        return ADMIN_LOGIN.equals(enterLogin) && ADMIN_PASS.equals(enterPass) && verify(gRecaptchaResponse);
    }

    private boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }

        boolean result = false;
        try {
            URL verifyUrl = new URL(SITE_VERIFY_URL);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) verifyUrl.openConnection();

            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setRequestProperty("User-Agent", "Chrome");
            httpsURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            httpsURLConnection.setDoOutput(true);

            String postParams = "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;


            OutputStream outStream = httpsURLConnection.getOutputStream();
            outStream.write(postParams.getBytes());

            outStream.flush();
            outStream.close();

            InputStream inputStream = httpsURLConnection.getInputStream();
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject jsonObject = jsonReader.readObject();

            result = jsonObject.getBoolean("success");

            jsonReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
