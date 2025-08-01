package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Announcement;
import com.example.InternshipProject.entities.concretes.InternMentorRelation;
import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.repositories.AnnouncementRepository;
import com.example.InternshipProject.repositories.InternMentorRelRepository;
import com.example.InternshipProject.repositories.MentorRepository;
import com.example.InternshipProject.services.abstracts.AnnouncementService;
import com.example.InternshipProject.services.dtos.requests.CreateAnnouncementRequest;
import com.example.InternshipProject.services.dtos.responses.AnnouncementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final MentorRepository mentorRepository;
    private final InternMentorRelRepository internMentorRelRepository;

    @Override
    public void createAnnouncement(CreateAnnouncementRequest request) {
        Mentor mentor = mentorRepository.findById(Math.toIntExact(request.getMentorId()))
                .orElseThrow(() -> new RuntimeException("Mentor not found"));

        Announcement announcement = new Announcement();
        announcement.setTitle(request.getTitle());
        announcement.setContent(request.getContent());
        announcement.setCreatedAt(LocalDateTime.now());
        announcement.setMentor(mentor); // bağla
        announcementRepository.save(announcement);
    }

    @Override
    public List<AnnouncementResponse> getRecentAnnouncements(String internEmail) {
        List<InternMentorRelation> relations = internMentorRelRepository
                .findByIntern_EmailIgnoreCase(internEmail);

        if (relations.isEmpty()) {
            throw new RuntimeException("Stajyere ait mentor ilişkisi bulunamadı: " + internEmail);
        }

        List<Long> mentorIds = relations.stream()
                .map(rel -> rel.getMentor().getId())
                .map(Long::valueOf)
                .toList();

        List<Announcement> announcements = announcementRepository.findByMentor_IdIn(mentorIds);

        return announcements.stream()
                .map(a -> new AnnouncementResponse(
                        a.getTitle(),
                        a.getContent(),
                        a.getCreatedAt(),
                        (long) a.getMentor().getId()
                ))
                .toList();
    }



}
