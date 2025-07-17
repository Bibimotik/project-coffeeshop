package org.backend.model.clients;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class Clients {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @Column(name = "email", unique = true, nullable = false)
  @NotNull
  @Email
  private String email;
  @Column(name = "phone", unique = true, nullable = false, length = 20)
  @NotNull
  @Size(min = 2, max = 20)
  private String phone;
  @Column(name = "full_name", nullable = false, length = 255)
  @NotNull
  @Size(max = 255)
  private String fullName;
  @Column(name = "bonus_points")
  @Min(0)
  private int bonusPoints = 0;
  @Column(name = "created_at", insertable = false, updatable = false)
  private Timestamp createdAt;

  public Clients() {}

  public Clients(String email, String phone, String full_name, int bonus_points) {
    this.email = email;
    this.phone = phone;
    this.fullName = full_name;
    this.bonusPoints = bonus_points;
  }

  public UUID getId() { return id; }
  public String getEmail() { return email; }
  public String getPhone() { return phone; }
  public String getFullName() { return fullName; }
  public int getBonusPoints() { return bonusPoints; }
  public Timestamp getCreatedAt() { return createdAt; }
  public void setId(UUID id) { this.id = id; }
  public void setEmail(String email) { this.email = email; }
  public void setPhone(String phone) { this.phone = phone; }
  public void setFullName(String fullName) { this.fullName = fullName; }
  public void setBonusPoints(int bonusPoints) { this.bonusPoints = bonusPoints; }
}
