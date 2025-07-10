package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Assignment;
import com.example.InternshipProject.repositories.AssignmentRepository;
import com.example.InternshipProject.services.abstracts.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements MentorService.AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Override
    public Assignment add(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<Assignment> getAll() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment getById(int id) {
        return assignmentRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(int id) {
        assignmentRepository.deleteById(id);
    }
}

