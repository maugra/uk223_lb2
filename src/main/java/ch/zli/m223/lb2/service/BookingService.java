package ch.zli.m223.lb2.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

import javax.inject.Inject;
import javax.transaction.Transactional;

import ch.zli.m223.lb2.model.Booking;

@ApplicationScoped
public class BookingService {
    @Inject
    EntityManager entityManager;
    @Inject
    ApplicationUserService applicationUserService;
    @Transactional
    public Booking createBooking(Booking booking) {
        return entityManager.merge(booking);
    }

    public List<Booking> findAll() {
        var query = entityManager.createQuery("FROM Booking", Booking.class);
        return query.getResultList();    
    }

    public Booking updateBooking(Long id, Booking booking) {
        booking.setId(id);
        return entityManager.merge(booking);
    }
    public void deleteBooking(Long id) {
        var booking = entityManager.find(Booking.class, id);
        entityManager.remove(booking);
    }

    public List<Booking> getBookingsOfUser(Long userId) {
        var query = entityManager.createQuery("SELECT b FROM booking b WHERE b.applicationuser_id = :id", Booking.class);
        query.setParameter("id", userId);
        return query.getResultList();
    } 
}
