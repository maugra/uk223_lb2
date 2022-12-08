package ch.zli.m223.lb2.model.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class ConflictExceptionMapper implements ExceptionMapper<ConflicException> {

	@Override
	public Response toResponse(ConflicException exception) {
		return Response.status(Status.CONFLICT).entity(exception.getMessage()).build();
	}
}
