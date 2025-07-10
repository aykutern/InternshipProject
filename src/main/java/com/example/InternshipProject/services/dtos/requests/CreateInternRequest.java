package com.example.InternshipProject.services.dtos.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateInternRequest {

        private String name;
        private String surname;
        private String email;
        private String phoneNumber;
        private String password;
        private LocalDate startDate;
        private LocalDate endDate;
        private String university;
        private String department;

}
