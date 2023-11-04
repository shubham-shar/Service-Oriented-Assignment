package com.course.application.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reviews {
    private Long id;

    private Date createdAt;

    public Date updatedAt;

    private String content;

    private String username;

    private Long rating;

    private Long courseId;

    private List<Comment> comments = new ArrayList<>();
}
