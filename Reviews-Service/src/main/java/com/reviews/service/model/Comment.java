package com.reviews.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 * @author Shubham Sharma
 * @date 01/11/23
 */
@Entity
@Table(name = "COMMENT")
public class Comment extends MendatoryFields implements Serializable {

  @Column(name = "CONTENT")
  private String content;

  @Column(name = "USERNAME")
  private String username;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "REVIEW_ID", nullable = false)
  private Review review;

  public String getContent() {
    return content;
  }

  public void setContent( String content ) {
    this.content = content;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername( String username ) {
    this.username = username;
  }

  public Review getReview() {
    return review;
  }

  public void setReview( Review review ) {
    this.review = review;
  }
}
