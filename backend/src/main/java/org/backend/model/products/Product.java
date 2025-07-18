package org.backend.model.products;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @ManyToOne
  @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_product_category"))
  private ProductCategory category;
  @Column(name = "name", length = 100, nullable = false)
  @NotBlank
  private String name;
  @Column(name = "image_url", length = 255, nullable = false)
  @NotBlank
  private String imageUrl;
  @Column(name = "description", columnDefinition = "TEXT")
  private String description;
  @Column(name = "volume", nullable = false, length = 20)
  @NotBlank
  private String volume;
  @Column(name = "price", precision = 10, scale = 2, nullable = false)
  @Positive
  @Digits(integer = 10, fraction = 2)
  private BigDecimal price;
  @Column(name = "is_active")
  private Boolean isActive = true;
  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;
  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Product() {}

  public Product(ProductCategory category, String name, String imageUrl, BigDecimal price, boolean isActive) {
    this.category = category;
    this.name = name;
    this.imageUrl = imageUrl;
    this.price = price;
    this.isActive = isActive;
  }

  public UUID getId() { return id; }
  public ProductCategory getProductCategory() { return category; }
  public String getName() { return name; }
  public String getImageUrl() { return imageUrl; }
  public String getDescription() { return description; }
  public String getVolume() { return volume; }
  public BigDecimal getPrice() { return price; }
  public Boolean getActive() { return isActive; }
  public LocalDateTime getCreatedAt() { return createdAt; }
  public LocalDateTime getUpdatedAt() { return updatedAt; }

  public void setProductCategory(ProductCategory category) { this.category = category; }
  public void setName(String name) { this.name = name; }
  public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
  public void setVolume(String volume) { this.volume = volume; }
  public void setPrice(BigDecimal price) { this.price = price; }
  public void setDescription(String description) { this.description = description; }
  public void setActive(Boolean active) { isActive = active; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
  public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
