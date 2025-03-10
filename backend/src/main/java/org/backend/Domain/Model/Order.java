package org.backend.Domain.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

//    @NotNull(message = "Client ID cannot be null")
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "clients_id", nullable = false)
//    private Client client;

    //TODO оставляем просто поле clientid
    @NotNull(message = "Client ID cannot be null")
    @Column(name = "clients_id", nullable = false)
    private UUID clientId; // Просто clientId, без связи @ManyToOne

    @NotNull(message = "Total amount cannot be null")
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @NotNull(message = "Creation date cannot be null")
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @NotNull(message = "Update date cannot be null")
    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public Order() {
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
    }

    public Order(UUID clientId, BigDecimal totalAmount) {
        this.clientId = clientId;
        this.totalAmount = totalAmount;
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
    }

    public UUID getId() { return id; }
//    public Client getClient() { return client; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public LocalDate getCreationDate() { return creationDate; }
    public LocalDate getUpdateDate() { return updateDate; }
    public boolean isDeleted() { return isDeleted; }
    public UUID getClientId() { return clientId; }

    public void setId(UUID id) { this.id = id; }
//    public void setClient(Client client) { this.client = client; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    public void setUpdateDate(LocalDate updateDate) { this.updateDate = updateDate; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
    public void setClientId(UUID clientId) { this.clientId = clientId; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
//                ", client=" + client +
                ", totalAmount=" + totalAmount +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
