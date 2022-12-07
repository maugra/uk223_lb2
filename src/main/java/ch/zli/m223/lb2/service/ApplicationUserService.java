package ch.zli.m223.lb2.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.lb2.model.ApplicationUser;

@ApplicationScoped
public class ApplicationUserService {
  
    @Inject
    EntityManager entityManager;

    @Transactional
    public ApplicationUser createUser(ApplicationUser user) {
        return entityManager.merge(user);
    }

    public List<ApplicationUser> findAll() {
        var query = entityManager.createQuery("FROM ApplicationUser", ApplicationUser.class);
        return query.getResultList();    }

    public ApplicationUser getMember(Long id) {
        return entityManager.find(ApplicationUser.class, id);
    }

    public ApplicationUser updateMember(Long id, ApplicationUser applicationUser) {
        applicationUser.setId(id);
        return entityManager.merge(applicationUser);
    }

    public void deleteUser(Long id) {
        var applicationUser =entityManager.find(ApplicationUser.class, id);
        entityManager.remove(applicationUser);
    }
}
