package com.course.application.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
@Data
@Entity
@Table(name = "tax_component", indexes = @Index(columnList = "price_id"))
public class TaxComponent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    @Column(name = "gst_amount")
    private Double gstAmount;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id")
    @JsonBackReference("taxes")
    private Price price;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    
    @Override
    public String toString() {
        return "TaxComponent{" + "id=" + id + ", gstAmount=" + gstAmount + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaxComponent)) {
            return false;
        }
        TaxComponent that = (TaxComponent) o;
        return id.equals(that.id) && gstAmount.equals(that.gstAmount) && createdAt
                .equals(that.createdAt) && updatedAt.equals(that.updatedAt);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, gstAmount, createdAt, updatedAt);
    }
}
