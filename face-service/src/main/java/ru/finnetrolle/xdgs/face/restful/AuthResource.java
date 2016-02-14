package ru.finnetrolle.xdgs.face.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.finnetrolle.xdgs.face.dto.external.communication.LongPollingResponseDto;
import ru.finnetrolle.xdgs.face.dto.external.communication.SignInRequestDto;
import ru.finnetrolle.xdgs.face.dto.external.dictionary.Command;
import ru.finnetrolle.xdgs.face.exception.LoginFailedException;
import ru.finnetrolle.xdgs.face.exception.UserAlreadyExistsException;
import ru.finnetrolle.xdgs.face.service.AuthService;
import ru.finnetrolle.xdgs.face.service.UserSessionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by finnetrolle on 13.02.2016.
 */

@Component
@Path("au")
public class AuthResource {

    private static final Logger log = LoggerFactory.getLogger(AuthResource.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private UserSessionService userSessionService;

    @GET
    public String test() {
        return "OK";
    }

    @POST
    @Path("ok")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response test2(SignInRequestDto data) {
        return Response.ok(new okok(data.getUsername(), data.getPassword().length())).build();
    }

    public static class okok {
        public String wr;
        public Integer ll;

        public okok(String wr, Integer ll) {
            this.wr = wr;
            this.ll = ll;
        }
    }

    @POST
    @Path("signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(SignInRequestDto data) {
        try {
            authService.addUser(data.getUsername(), data.getPassword());
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            handle(e);
            return null;
        }
    }

    @POST
    @Path("signin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(SignInRequestDto data){
        try {
            if (authService.checkAuth(data.getUsername(), data.getPassword())) {
                return Response.ok(new LongPollingResponseDto(userSessionService.addSession(data.getUsername()), Command.IDLE)).build();
            }
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            handle(e);
            return null;
        }
    }

    private void handle(Exception e){
        try {
            throw e;
        } catch (UserAlreadyExistsException ex) {
            throw new WebApplicationException(Response.Status.CONFLICT);
        } catch (LoginFailedException ex) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        } catch (Exception ex) {
            throw new InternalServerErrorException("Server fails");
        }
    }

}
