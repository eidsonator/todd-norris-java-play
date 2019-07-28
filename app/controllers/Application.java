package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Joke;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.io.IOException;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result help() {
        return ok(help.render("Your new application is ready."));
    }

    public Result joke() throws IOException {
        Joke joke = new Joke();
        joke.getVal();
        ObjectNode result = Json.newObject();
        result.put("joke", joke.getVal());
        return ok(result);
    }
}
