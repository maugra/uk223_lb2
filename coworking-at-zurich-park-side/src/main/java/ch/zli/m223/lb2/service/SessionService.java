package ch.zli.m223.lb2.service;

import javax.ws.rs.core.Response;

import ch.zli.m223.lb2.model.Credential;

public class SessionService {
    public Response authenticate(Credential credential){
        return Response.status(Response.Status.OK).build();
    }
}
