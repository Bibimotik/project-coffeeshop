package org.backend.model.clients;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(name = "email", unique = true, nullable = false)
  @NotNull
  @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
  private String email;
  @Column(name = "phone", unique = true, nullable = false, length = 20)
  @NotNull
  @Size(min = 2, max = 20)
  @Pattern(regexp = "^\\+?[0-9\\s-]+$")
  private String phone;
  @Column(name = "full_name", nullable = false, length = 255)
  @NotNull
  @Size(max = 255)
  private String fullName;
  @Column(name = "bonus_points")
  @PositiveOrZero
  private Integer bonusPoints = 0;
  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  public Client() {}

  public Client(String email, String phone, String fullName, int bonusPoints) {
    this.email = email;
    this.phone = phone;
    this.fullName = fullName;
    this.bonusPoints = bonusPoints;
  }

  public UUID getId() { return id; }
  public String getEmail() { return email; }
  public String getPhone() { return phone; }
  public String getFullName() { return fullName; }
  public int getBonusPoints() { return bonusPoints; }
  public LocalDateTime getCreatedAt() { return createdAt; }

  public void setEmail(String email) { this.email = email; }
  public void setPhone(String phone) { this.phone = phone; }
  public void setFullName(String fullName) { this.fullName = fullName; }
  public void setBonusPoints(Integer bonusPoints) { this.bonusPoints = bonusPoints; }
}
