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

import javax.inject.Inject;

import ch.zli.m223.lb2.model.Booking;
import ch.zli.m223.lb2.service.BookingService;

@Path("/bookings")
public class BookingController {
    @Inject
    BookingService bookingService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Creates a new booking.",
        description = "Creates a new booking and returns the newly added booking."
    )
    public Booking createBooking(Booking booking){
        return bookingService.createBooking(booking);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "returns all bookings",
        description = "returns all bookings"
    )
    public List<Booking> getBookings(){
        return bookingService.findAll();
    }

    @Path("/users/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "returns the bookings of a user",
        description = "returns the bookings of a user by the user's id"
    )
    public List<Booking> getBookingsByUser(@PathParam("id") Long userId){
        return bookingService.getBookingsOfUser(userId);
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "updates a booking",
        description = "updates a booking by its id"
    )
    public Booking updateBooking(@PathParam("id") Long id, Booking booking){
        return bookingService.updateBooking(id, booking);
    }

    @Path("/accept/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "accepts a booking",
        description = "accepts a booking by its id"
    )
    public Booking acceptBooking(@PathParam("id") Long id, Booking booking){
        return bookingService.updateBooking(id, booking);
    }

    @Path("/decline/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "declines a booking",
        description = "declines a booking by its id"
    )
    public Booking declineBooking(@PathParam("id") Long id, Booking booking){
        return bookingService.updateBooking(id, booking);
    }

    @Path("/cancel/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "cancels a booking",
        description = "cancels a booking by its id"
    )
    public Booking cancelBooking(@PathParam("id") Long id, Booking booking){
        return bookingService.updateBooking(id, booking);
    }

    @Path("/{id}")
    @DELETE
    @Operation(
        summary = "deletes a booking ",
        description = "deletes a booking by its id"
    )
    public void deleteBooking(@PathParam("id") Long id){
        bookingService.deleteBooking(id);
    }
    
}
