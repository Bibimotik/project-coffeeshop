package org.backend.model.products;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product_categories")
public class ProductCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "name", length = 50, unique = true, nullable = false)
  @NotBlank
  @Size(max = 50)
  private String name;
  @Column(name = "display_order", nullable = false)
  @PositiveOrZero
  private Short displayOrder = 0;

  public ProductCategory() {}

  public ProductCategory(String name, Short displayOrder) {
    this.name = name;
    this.displayOrder = displayOrder;
  }

  public Integer getId() { return id; }
  public String getName() { return name; }
  public Short getDisplayOrder() { return displayOrder; }

  public void setName(String name) { this.name = name; }
  public void setDisplayOrder(Short displayOrder) { this.displayOrder = displayOrder; }
}
