package ch.zli.m223.lb2.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @ManyToOne(optional = false)
    @Fetch(FetchMode.JOIN)
    private ApplicationUser applicationUser;

    @Column(nullable = false)
    private LocalDate date;

    @Column()
    @ElementCollection(targetClass = Duration.class)
    private Set<Duration> duration;

    @Column
    private boolean withLocker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Duration> getDuration() {
        return duration;
    }

    public void setDuration(Set<Duration> duration) {
        this.duration = duration;
    }

    public boolean isWithLocker() {
        return withLocker;
    }

    public void setWithLocker(boolean withLocker) {
        this.withLocker = withLocker;
    }

    //getters and setters
    
}
