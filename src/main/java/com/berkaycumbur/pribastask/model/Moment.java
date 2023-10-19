package com.berkaycumbur.pribastask.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Shourcut for getter setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Timeline")
// Initialize all our variables in data in model same as timeline
public class Moment {
    private String title;
    private String description;
    private String momentDate;
    @CreatedDate // Use @CreatedDate to automatically populate creationDate
    private LocalDateTime creationDate;

}
