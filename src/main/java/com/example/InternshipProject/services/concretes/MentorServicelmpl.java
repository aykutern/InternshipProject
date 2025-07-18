package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.repositories.MentorRepository;
import com.example.InternshipProject.services.abstracts.MentorService;
import com.example.InternshipProject.services.dtos.requests.CreateMentorRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServicelmpl implements MentorService {
    private final MentorRepository mentorRepository;

    public MentorServicelmpl(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;}

        @Override
        public Mentor getMentorById (int id){
            return mentorRepository.findById(id).orElse(null);
        }
        @Override
        public void addMentor (CreateMentorRequest request){
        Mentor mentor=new Mentor();
        mentor.setName(request.getName());
        mentor.setSurname(request.getSurname());
        mentor.setEmail(request.getEmail());
        mentor.setPhoneNumber(request.getPhoneNumber());
        mentor.setPassword(request.getPassword());
        mentor.setTitle(request.getTitle());

        mentorRepository.save(mentor);

        }
        @Override
        public Mentor deleteMentorById(int id){
        Mentor mentor=getMentorById(id);
        mentorRepository.deleteById(id);
        return mentor;
        }
        @Override
        public List<Mentor> getAllMentors(){
        return this.mentorRepository.findAll();
        }
        @Override
        public Mentor updateMentorById(int id, CreateMentorRequest request) {
        Mentor mentor = getMentorById(id);

        mentor.setName(request.getName());
        mentor.setSurname(request.getSurname());
        mentor.setEmail(request.getEmail());
        mentor.setPhoneNumber(request.getPhoneNumber());
        mentor.setPassword(request.getPassword());
        mentor.setTitle(request.getTitle());

        return mentorRepository.save(mentor);
    }
    @Override
    public boolean existsByEmail(String email) {
        return mentorRepository.existsByEmail(email);
    }


}
