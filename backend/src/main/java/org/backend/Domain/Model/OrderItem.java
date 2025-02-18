package org.backend.Domain.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Order ID cannot be null")
    @ManyToOne(optional = false)
    @JoinColumn(name = "orders_id", nullable = false)
    private Order order;

    @NotNull(message = "Goods ID cannot be null")
    @ManyToOne(optional = false)
    @JoinColumn(name = "goods_id", nullable = false)
    private Goods goods;

    @NotNull(message = "Quantity cannot be null")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull(message = "Price cannot be null")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull(message = "Creation date cannot be null")
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @NotNull(message = "Update date cannot be null")
    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public OrderItem() {
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
    }

    public OrderItem(Order order, Goods goods, Integer quantity, BigDecimal price) {
        this.order = order;
        this.goods = goods;
        this.quantity = quantity;
        this.price = price;
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
    }

    public Long getId() { return id; }
    public Order getOrder() { return order; }
    public Goods getGoods() { return goods; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getPrice() { return price; }
    public LocalDate getCreationDate() { return creationDate; }
    public LocalDate getUpdateDate() { return updateDate; }
    public boolean isDeleted() { return isDeleted; }

    public void setId(Long id) { this.id = id; }
    public void setOrder(Order order) { this.order = order; }
    public void setGoods(Goods goods) { this.goods = goods; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    public void setUpdateDate(LocalDate updateDate) { this.updateDate = updateDate; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", goods=" + goods +
                ", quantity=" + quantity +
                ", price=" + price +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
