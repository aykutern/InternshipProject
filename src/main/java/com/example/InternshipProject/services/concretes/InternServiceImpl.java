package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.services.abstracts.InternService;
import com.example.InternshipProject.services.dtos.requests.CreateInternRequest;
import org.springframework.stereotype.Service;
import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.repositories.InternRepository;

@Service
public class InternServiceImpl implements InternService {

    private final InternRepository internRepository;

    public InternServiceImpl(InternRepository internRepository) {
        this.internRepository = internRepository;
    }

    @Override
    public Intern getInternByID(int id) {
        return internRepository.findById(id).orElse(null);
    }
    @Override
    public void addIntern(CreateInternRequest request) {
        Intern intern = new Intern();

        intern.setName(request.getName());
        intern.setSurname(request.getSurname());
        intern.setEmail(request.getEmail());
        intern.setPhoneNumber(request.getPhoneNumber());
        intern.setPassword(request.getPassword());
        intern.setStartDate(request.getStartDate());
        intern.setEndDate(request.getEndDate());
        intern.setUniversity(request.getUniversity());
        intern.setDepartment(request.getDepartment());

        internRepository.save(intern);
    }

    @Override
    public Intern deleteInternByID(int id) {
        Intern intern = getInternByID(id);
        internRepository.deleteById(id);
        return intern;
    }



}
