package ch.zli.m223.lb2.service;

import ch.zli.m223.lb2.model.ApplicationUser;
import ch.zli.m223.lb2.model.exception.ConflicException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import java.security.spec.KeySpec;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ApplicationUserService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public ApplicationUser createUser(ApplicationUser user) throws ConflicException {
        //regex von https://stackoverflow.com/questions/19605150/regex-for-password-must-contain-at-least-eight-characters-at-least-one-number-a
        if(!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")){
            throw new BadRequestException("Use a password with minimum eight characters, at least one uppercase letter, one lowercase letter and one number");
        }

        if (findAll().stream().map(ApplicationUser::getEmail).toList().contains(user.getEmail())){
            throw new ConflicException("User with email " + user.getEmail() + " already exists");
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
    @Transactional
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
