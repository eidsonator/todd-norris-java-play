package models;

import com.fasterxml.jackson.databind.JsonNode;
import jdk.nashorn.internal.runtime.options.Option;
import play.api.libs.json.JsValue;
import play.libs.Json;
import scala.util.parsing.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class Joke {

    private JsonNode val;

    public Joke() throws IOException {
        URL url = new URL("https://api.chucknorris.io/jokes/random");

        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

         this.val = Json.parse(content.toString());
    }

    public String getVal(Optional<String> firstName, Optional<String> lastName) {
        String fName = "";
        if (firstName.isPresent()) {
            fName = firstName.get().toString();
        } else {
            fName = "Todd";
        }

        String lName = "";
        if (lastName.isPresent()) {
            lName = lastName.get().toString();
        } else {
            lName = "Eidson";
        }


        String joke = this.val.get("value").toString();
        joke = joke
                .replaceAll("Chuck", fName)
                .replaceAll("Norris'", lName +"'s")
                .replaceAll("Norris", lName);
        return joke;
    }
}
