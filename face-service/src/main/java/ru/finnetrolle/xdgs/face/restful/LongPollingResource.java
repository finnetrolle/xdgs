package ru.finnetrolle.xdgs.face.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.finnetrolle.xdgs.face.dto.external.communication.LongPollingRequestDto;
import ru.finnetrolle.xdgs.face.dto.external.communication.LongPollingResponseDto;
import ru.finnetrolle.xdgs.face.dto.external.dictionary.Command;
import ru.finnetrolle.xdgs.face.model.UserSession;
import ru.finnetrolle.xdgs.face.service.UserSessionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by finnetrolle on 14.02.2016.
 */

@Component
@Path("ping")
public class LongPollingResource {

    @Autowired
    private UserSessionService userSessionService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping(LongPollingRequestDto request) {
        UserSession session = userSessionService.getSession(request.getSessionId()).get();
        if (session == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(new LongPollingResponseDto(session.getSessionId(), Command.IDLE)).build();
    }

}
