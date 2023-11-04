package com.course.application.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "pricing_strategy", indexes = @Index(columnList = "price_id"))
public class PricingStrategy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private com.course.application.models.enums.PricingStrategy type;
    
    @Column(name = "duration_in_months")
    private int durationInMonths;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id")
    @JsonBackReference("strategy")
    private Price price;
    
    @Override
    public String toString() {
        return "PricingStrategy{" + "id=" + id + ", type='" + type + '\'' + ", durationInMonths=" + durationInMonths
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PricingStrategy)) {
            return false;
        }
        PricingStrategy that = (PricingStrategy) o;
        return durationInMonths == that.durationInMonths && id.equals(that.id) && type.equals(that.type) && createdAt
                .equals(that.createdAt) && updatedAt.equals(that.updatedAt);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, type, durationInMonths, createdAt, updatedAt);
    }
}
