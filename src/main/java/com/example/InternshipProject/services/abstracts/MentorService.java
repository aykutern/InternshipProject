package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.services.dtos.requests.CreateMentorRequest;

import java.util.List;

public interface MentorService {
    Mentor getMentorById(int id);
    void addMentor(CreateMentorRequest request);
    Mentor deleteMentorById(int id);

}
