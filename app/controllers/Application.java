package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Joke;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Application extends Controller {

    private Http.Request request;

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result about() { return ok(about.render()); }

    public Result joke(Http.Request request) throws IOException {
        Optional<String> firstName = request.session().getOptional("firstName");
        Optional<String> lastName = request.session().getOptional("lastName");
        Joke joke = new Joke();
        ObjectNode result = Json.newObject();
        result.put("joke", joke.getVal(firstName, lastName));
        return ok(result);
    }

    public Result post_name(Http.Request request) {
        this.request = request;
//        JsonNode json = request().body().asJson();
        String firstName = request().getQueryString("firstName");
        String lastName = request.getQueryString("lastName");
        request.session().adding("firstName", firstName).adding("lastName", lastName);

        Map<String, String> sessionValues = new HashMap<String, String>();
        sessionValues.put("firstName", firstName);
        sessionValues.put("lastName", lastName);

        return ok("Got name: " + firstName).addingToSession(request, sessionValues);
    }
}
