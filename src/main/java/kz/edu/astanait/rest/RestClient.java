package kz.edu.astanait.rest;

import com.google.gson.Gson;
import kz.edu.astanait.classes.Product;
import kz.edu.astanait.classes.User;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RestClient {
    private WebTarget getWebTarget() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        return client.target("http://localhost:8080/sdproject_war_exploded/api/users/all");
    }

    public List<User> getAllUsers() {
        WebTarget target = getWebTarget();
        String json = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);

        Gson gson = new Gson();
        User[] users = gson.fromJson(json, User[].class);
        return new LinkedList<>(Arrays.asList(users));
    }

}
