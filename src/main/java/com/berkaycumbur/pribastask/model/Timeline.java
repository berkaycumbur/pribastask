package com.berkaycumbur.pribastask.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//Shourcut for getter setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Timeline")
// Initialize all our variables in data in model
public class Timeline {

    @Transient
    public static final String SEQUENCE_NAME = "timeline_sequence";

    @Id private long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private List<String> tags;
    private List<Moment> moments;

}
