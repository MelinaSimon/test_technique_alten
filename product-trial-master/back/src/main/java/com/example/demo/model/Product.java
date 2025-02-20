package com.example.demo.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;
    private String name;
    
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String image;
    private String category;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    private Integer quantity;

    @Column(name = "internal_reference", length = 15)
    private String internalReference;

    @Column(name = "shell_id")
    private Integer shellId;

    @Enumerated(EnumType.STRING)
    @Column(name = "inventory_status")
    private InventoryStatus inventoryStatus;

    private Byte rating;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Integer getId() { 
        return id; 
    }
    
    public void setId(Integer id) { 
        this.id = id; 
    }

    public String getCode() { 
        return code; 
    }
    
    public void setCode(String code) { 
        this.code = code; 
    }

    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }

    public String getDescription() { 
        return description; 
    }
    
    public void setDescription(String description) { 
        this.description = description; 
    }

    public String getImage() { 
        return image; 
    }
    
    public void setImage(String image) { 
        this.image = image; 
    }

    public String getCategory() { 
        return category; 
    }
    
    public void setCategory(String category) { 
        this.category = category; 
    }

    public BigDecimal getPrice() { 
        return price; 
    }
    
    public void setPrice(BigDecimal price) { 
        this.price = price; 
    }

    public Integer getQuantity() { 
        return quantity; 
    }
    
    public void setQuantity(Integer quantity) { 
        this.quantity = quantity; 
    }

    public String getInternalReference() { 
        return internalReference; 
    }
    
    public void setInternalReference(String internalReference) { 
        this.internalReference = internalReference; 
    }

    public Integer getShellId() { 
        return shellId; 
    }
    
    public void setShellId(Integer shellId) { 
        this.shellId = shellId; 
    }

    public InventoryStatus getInventoryStatus() { 
        return inventoryStatus; 
    }
    
    public void setInventoryStatus(InventoryStatus inventoryStatus) { 
        this.inventoryStatus = inventoryStatus; 
    }

    public Byte getRating() { 
        return rating; 
    }
    
    public void setRating(Byte rating) { 
        this.rating = rating; 
    }

    public Long getCreatedAt() {
        return createdAt != null ? createdAt.atZone(ZoneOffset.UTC).toInstant().toEpochMilli() : null;
    }

    public void setCreatedAt(Long timestamp) {
        this.createdAt = timestamp != null ? Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.UTC).toLocalDateTime() : null;
    }

    public Long getUpdatedAt() {
        return updatedAt != null ? updatedAt.atZone(ZoneOffset.UTC).toInstant().toEpochMilli() : null;
    }

    public void setUpdatedAt(Long timestamp) {
        this.updatedAt = timestamp != null ? Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.UTC).toLocalDateTime() : null;
    }
}
