package com.reviews.service.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shubham Sharma
 * @date 01/11/23
 */
@Entity
@Table(name = "REVIEW")
public class Review extends MendatoryFields implements Serializable {

  @Column(name = "CONTENT")
  private String content;

  @Column(name="USERNAME")
  private String username;

  @Column(name="RATING")
  private Long rating;

  @Column(name="COURSE_ID")
  private Long courseId;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "REVIEW_ID")
  private List<Comment> comments = new ArrayList<>();

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getRating() {
    return rating;
  }

  public void setRating(Long rating) {
    this.rating = rating;
  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
}
