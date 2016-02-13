package ru.finnetrolle.xdgs.face.restful;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by finnetrolle on 13.02.2016.
 */

@Component
@Path("test")
public class TestResource {

    @GET
    public String test() {
        return "OK";
    }

}
