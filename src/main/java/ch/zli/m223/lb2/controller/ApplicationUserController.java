package ch.zli.m223.lb2.controller;

import javax.ws.rs.Produces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.oracle.svm.core.annotate.Inject;

import ch.zli.m223.lb2.model.ApplicationUser;
import ch.zli.m223.lb2.service.ApplicationUserService;

@Path("/members")
public class ApplicationUserController {

    @Inject
    ApplicationUserService applicationUserService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new user. Also known as registration.", description = "Creates a new user and returns the newly added user.")
    public ApplicationUser createUser(ApplicationUser applicationUser) {
        return applicationUserService.createUser(applicationUser);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "returns all users", description = "all existing users are returned")
    public List<ApplicationUser> getMembers() {
        return applicationUserService.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "returns the specified member", description = "returns the member with the provided id")
    public ApplicationUser getMember(@PathParam("id") Long id) {
        return applicationUserService.getMember(id);
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an user.", description = "Updates an User by its id")
    public ApplicationUser update(@PathParam("id") Long id, ApplicationUser applicationUser) {
        return applicationUserService.updateMember(id, applicationUser);
    }

    @Path("/{id}")
    @DELETE
    @Operation(summary = "Deletes an user.", description = "Deletes an user by its id.")
    public void delete(@PathParam("id") Long id) {
        applicationUserService.deleteUser(id);
    }

}
