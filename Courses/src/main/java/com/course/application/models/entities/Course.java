package com.course.application.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @author shubham sharma
 *         <p>
 *         01/11/23
 */
@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    
    @Lob
    private String description;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "prices")
    private Set<Price> prices;
    
    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\''
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", prices=" + prices + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return id.equals(course.id) && name.equals(course.name) && description.equals(course.description) && createdAt
                .equals(course.createdAt) && updatedAt.equals(course.updatedAt);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdAt, updatedAt);
    }
}
