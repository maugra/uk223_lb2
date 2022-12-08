package ch.zli.m223.lb2.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import javax.inject.Inject;

import ch.zli.m223.lb2.model.ApplicationUser;
import ch.zli.m223.lb2.model.Credential;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class SessionService {

  @Inject
  ApplicationUserService applicationUserService;
  // übernommen aus punchclock
  public Response authenticate(Credential credential) {
    Optional<ApplicationUser> principal = applicationUserService.findByEmail(credential.getEmail());

    try {
      if (principal.isPresent() && principal.get().getPassword().equals(credential.getPassword())) {
        String token = Jwt
            .issuer("https://zli.example.com/")
            .upn(credential.getEmail())
            .groups(new HashSet<>(Arrays.asList("Member", "Admin")))
            .expiresIn(Duration.ofHours(24))
            .sign();
        return Response
            .ok(principal.get())
            .header("Authorization", "Bearer " + token)
            .build();
      }
    } catch (Exception e) {
      System.err.println("Couldn't validate password.");
    }

    return Response.status(Response.Status.FORBIDDEN).build();
  }
}
