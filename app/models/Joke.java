package models;

import com.fasterxml.jackson.databind.JsonNode;
import play.api.libs.json.JsValue;
import play.libs.Json;
import scala.util.parsing.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public String getVal() {
        String joke = this.val.get("value").toString();
        joke = joke
                .replaceAll("Chuck", "Todd")
                .replaceAll("Norris'", "Eidson's")
                .replaceAll("Norris", "Eidson");
        return joke;
    }
}
