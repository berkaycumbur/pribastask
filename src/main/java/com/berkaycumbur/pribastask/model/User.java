package com.berkaycumbur.pribastask.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//Shourcut for getter setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User {

        @Transient //For auto increment ID
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id private long id;
    private String username;
    private String email;
    private String password;

}
