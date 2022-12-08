package ch.zli.m223.lb2.service;

import java.time.LocalDate;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.lb2.model.ApplicationUser;
import ch.zli.m223.lb2.model.Booking;
import ch.zli.m223.lb2.model.Duration;
import ch.zli.m223.lb2.model.Role;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;


@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {

  @Inject
  EntityManager entityManager;

  void onStartup(@Observes StartupEvent event){
    generateTestData();
  }

  @Transactional
  public void generateTestData() {
    var userA = new ApplicationUser();
    userA.setFirstname("Flurin");
    userA.setLastname("Graf");
    userA.setNickname("Staeffel");
    userA.setPassword("Passw0rd");
    userA.setEmail("flurin@example.com");
    userA.setRole(Role.ADMIN);
    entityManager.persist(userA);

    var userB = new ApplicationUser();
    userB.setFirstname("Robin");
    userB.setLastname("Müller");
    userB.setNickname("Robinsky");
    userB.setPassword("Passw0rd");
    userB.setEmail("robinsky@example.com");
    userB.setRole(Role.MEMBER);
    entityManager.persist(userB);

    var admin = new ApplicationUser();
    admin.setFirstname("Maurus");
    admin.setLastname("Graf");
    admin.setPassword("AdminPassw0rd");
    admin.setEmail("maurus@example.com");
    admin.setRole(Role.ADMIN);
    entityManager.persist(admin);

    var bookingA = new Booking();
    bookingA.setApplicationUser(userA);
    bookingA.setDate(LocalDate.now().plusDays(1));
    bookingA.setWithLocker(true);
    bookingA.setDuration(Set.of(Duration.AFTERNOON));
    entityManager.persist(bookingA);

    var bookingB = new Booking();
    bookingB.setApplicationUser(userB);
    bookingB.setDate(LocalDate.now().plusDays(2));
    bookingB.setWithLocker(false);
    bookingB.setDuration(Set.of(Duration.MORNING, Duration.AFTERNOON));
    entityManager.persist(bookingB);
  }
}  