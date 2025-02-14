package org.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotBlank(message = "Category cannot be blank")
    @Size(max = 50, message = "Category must be less than 50 characters")
    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must be less than 100 characters")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull(message = "Size cannot be null")
    @Column(name = "size", nullable = false)
    private Integer size;

    @NotBlank(message = "Composition cannot be blank")
    @Size(max = 1000, message = "Composition must be less than 1000 characters")
    @Column(name = "composition", nullable = false, length = 1000)
    private String composition;

    @NotNull(message = "Price cannot be null")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotBlank(message = "Image path cannot be blank")
    @Column(name = "image", nullable = false)
    private String image;

    @NotNull(message = "Creation date cannot be null")
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @NotNull(message = "Update date cannot be null")
    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public Goods() {}

    public Goods(String category, String name, int size, String composition, BigDecimal price, String image) {
        this.id = UUID.randomUUID();
        this.category = category;
        this.name = name;
        this.size = size;
        this.composition = composition;
        this.price = price;
        this.image = image;
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
        this.isDeleted = false;
    }

    public UUID getId() { return id; }
    public String getCategory() { return category; }
    public String getName() { return name; }
    public int getSize() { return size; }
    public String getComposition() { return composition; }
    public BigDecimal getPrice() { return price; }
    public String getImage() { return image; }
    public LocalDate getCreationDate() { return creationDate; }
    public LocalDate getUpdateDate() { return updateDate; }
    public boolean isDeleted() { return isDeleted; }
    public void setId(UUID id) { this.id = id; }
    public void setCategory(String category) { this.category = category; }
    public void setName(String name) { this.name = name; }
    public void setSize(int size) { this.size = size; }
    public void setComposition(String composition) { this.composition = composition; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setImage(String image) { this.image = image; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    public void setUpdateDate(LocalDate updateDate) { this.updateDate = updateDate; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", composition='" + composition + '\'' +
                ", price=" + price +
                ", imagePath='" + image + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
