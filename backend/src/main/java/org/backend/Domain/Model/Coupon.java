package org.backend.Domain.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @NotNull(message = "Client cannot be null")
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NotNull(message = "Goods cannot be null")
    @ManyToOne(optional = false)
    @JoinColumn(name = "goods_id", nullable = false)
    private Goods goods;

    @NotNull(message = "Percent cannot be null")
    @Min(value = 0, message = "Percent must be at least 0")
    @Max(value = 100, message = "Percent must be at most 100")
    @Column(name = "percent", nullable = false)
    private Integer percent;

    @NotNull(message = "Creation date cannot be null")
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @NotNull(message = "Update date cannot be null")
    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public Coupon() {}

    public Coupon(Client client, Goods goods, Integer percent) {
        this.client = client;
        this.goods = goods;
        this.percent = percent;
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
        this.isDeleted = false;
    }

    public UUID getId() { return id; }
    public Client getClient() { return client; }
    public Goods getGoods() { return goods; }
    public int getPercent() { return percent; }
    public LocalDate getCreationDate() { return creationDate; }
    public LocalDate getUpdateDate() { return updateDate; }
    public boolean isDeleted() { return isDeleted; }

    public void setId(UUID id) { this.id = id; }
    public void setClient(Client client) { this.client = client; }
    public void setGoods(Goods goods) { this.goods = goods; }
    public void setPercent(int percent) { this.percent = percent; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    public void setUpdateDate(LocalDate updateDate) { this.updateDate = updateDate; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", client=" + client +
                ", goods=" + goods +
                ", percent=" + percent +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
