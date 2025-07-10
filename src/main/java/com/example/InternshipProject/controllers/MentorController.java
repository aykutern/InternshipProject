package com.example.InternshipProject.controllers;
import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.services.abstracts.MentorService;
import com.example.InternshipProject.services.dtos.requests.CreateMentorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mentors")
public class MentorController {
    private final MentorService mentorService;
    @Autowired
    public MentorController (MentorService mentorService){
        this.mentorService=mentorService;
    }
    @GetMapping("/{id}")
    public Mentor getMentorById(@PathVariable int id){return mentorService.getMentorById(id);}
    @PostMapping
    public ResponseEntity<Void> addMentor(@RequestBody CreateMentorRequest request){
        mentorService.addMentor(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Mentor> deleteMentorById(@PathVariable int id){
        Mentor deletedMentor=mentorService.deleteMentorById(id);
        return ResponseEntity.ok(deletedMentor);
    }


}
