package com.example.InternshipProject.controllers;

import com.example.InternshipProject.entities.concretes.Assignment;
import com.example.InternshipProject.services.abstracts.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final MentorService.AssignmentService assignmentService;

    @PostMapping
    public Assignment add(@RequestBody Assignment assignment) {
        return assignmentService.add(assignment);
    }

    @GetMapping
    public List<Assignment> getAll() {
        return assignmentService.getAll();
    }

    @GetMapping("/{id}")
    public Assignment getById(@PathVariable int id) {
        return assignmentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        assignmentService.delete(id);
    }
}

