package ch.zli.m223.lb2.service;

import java.security.spec.KeySpec;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ch.zli.m223.lb2.model.ApplicationUser;

@ApplicationScoped
public class ApplicationUserService {
  
    @Inject
    EntityManager entityManager;

    @Transactional
    public ApplicationUser createUser(ApplicationUser user) {
        //regex von https://stackoverflow.com/questions/19605150/regex-for-password-must-contain-at-least-eight-characters-at-least-one-number-a
        if(!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")){
            throw new WebApplicationException("Use a password with minimum eight characters, at least one uppercase letter, one lowercase letter and one number:", Response.status(Status.BAD_REQUEST).build());
        }
        user.setPassword(hashAndPepperPassword(user.getPassword()));

        if (user.getNickname() == null){
            user.setNickname(user.getFirstname());
        }
        return entityManager.merge(user);
    }

    public List<ApplicationUser> findAll() {
        var query = entityManager.createQuery("FROM ApplicationUser", ApplicationUser.class);
        return query.getResultList();    
    }

    public ApplicationUser getMember(Long id) {
        return entityManager.find(ApplicationUser.class, id);
    }

    public ApplicationUser updateMember(Long id, ApplicationUser applicationUser) {
        applicationUser.setId(id);
        return entityManager.merge(applicationUser);
    }

    public void deleteUser(Long id) {
        var applicationUser = entityManager.find(ApplicationUser.class, id);
        entityManager.remove(applicationUser);
    }

    public Optional<ApplicationUser> findByEmail(String email) {
        return entityManager
                .createNamedQuery("ApplicationUser.findByEmail", ApplicationUser.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    private String hashAndPepperPassword(String password){
        var pepper = "OoHIARxzqSBYmao0".getBytes(); 
        KeySpec spec = new PBEKeySpec(password.toCharArray(), pepper, 2000, 128);
        SecretKeyFactory factory;
        String hashedPassword;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            hashedPassword = hash.toString();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
        return hashedPassword;
    }
}
