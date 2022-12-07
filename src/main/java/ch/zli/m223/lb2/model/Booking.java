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
}
