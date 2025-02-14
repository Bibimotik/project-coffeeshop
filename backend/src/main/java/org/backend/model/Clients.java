package org.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class Clients {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotNull(message = "Points cannot be null")
    @Column(name = "points", nullable = false, precision = 10, scale = 2)
    private BigDecimal points;

    @NotNull(message = "Creation date cannot be null")
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @NotNull(message = "Update date cannot be null")
    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public Clients() {}

    public Clients(String name, String email, LocalDate dateOfBirth) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.points = BigDecimal.ZERO;
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
        this.isDeleted = false;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public BigDecimal getPoints() { return points; }
    public LocalDate getCreationDate() { return creationDate; }
    public LocalDate getUpdateDate() { return updateDate; }
    public boolean isDeleted() { return isDeleted; }
    public void setId(UUID id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setPoints(BigDecimal points) { this.points = points; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    public void setUpdateDate(LocalDate updateDate) { this.updateDate = updateDate; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", points=" + points +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
