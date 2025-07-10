package com.example.InternshipProject.services.dtos.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateMentorRequest {
    private String name;
    private String surname;
    private String title;
    private String email;
    private String phoneNumber;
    private String password;

}
