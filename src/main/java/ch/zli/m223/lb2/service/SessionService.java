package ch.zli.m223.lb2.service;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import ch.zli.m223.lb2.model.Credential;

@ApplicationScoped
public class SessionService {
    public Response authenticate(Credential credential){
        return Response.status(Response.Status.OK).build();
    }
}
